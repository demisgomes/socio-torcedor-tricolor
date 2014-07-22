package com.example.sociotorcedortricolor;

import java.text.SimpleDateFormat;
import java.util.Date;

import bd.Banco;
import bd.SocioDAO;
import dominio.Compra;
import dominio.Mensalidade;
import dominio.Socio;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class TelaConfirmarEndereco extends Activity implements OnClickListener {

	Button btnConfirmarEndereco;
	EditText etRua, etNumeroCasa, etBairro, etCidade;
	public static String tipoTela;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_confirmar_endereco);
		etRua= (EditText) findViewById(R.id.etRua);
		etNumeroCasa= (EditText) findViewById(R.id.etNumeroCasa);
		etBairro=(EditText) findViewById(R.id.etBairro);
		etCidade= (EditText) findViewById(R.id.etCidade);
		btnConfirmarEndereco=(Button) findViewById(R.id.btnConfirmarEndereco);
		btnConfirmarEndereco.setOnClickListener(this);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_confirmar_endereco, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnConfirmarEndereco){
			
			android.content.DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(TelaConfirmarEndereco.this, TelaInicial.class);
					startActivity(intent);
				}
			};
			
			
			if(tipoTela.equals("pagamentoDinheiro")){
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
			    builder.setMessage("Endereço confirmado!")
			       .setTitle("Confirmado").setPositiveButton("OK", null);
			    builder.create().show();
				Intent intent=new Intent(TelaConfirmarEndereco.this, TelaConfirmarCompra.class);
				TelaConfirmarCompra.tipoTela="pagamentoCompra";
				startActivity(intent);
			}
			
			if(tipoTela.equals("pagamentoPontos")){
				
				Banco banco=new Banco(this);
				Date date=new Date();
				SimpleDateFormat formatar= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String data=formatar.format(date);
				SocioDAO banco2=new SocioDAO (this);
				banco2.updatePontosSocio(Socio.getSocioLogado(), -(Compra.getCompraAtual().getQtdProdutos()*Compra.getCompraAtual().getProduto().getPontos()));
				
				banco.inserirCompraHistorico(Compra.getCompraAtual().getProduto().getNomeProduto(), Compra.getCompraAtual().getQtdProdutos(), Socio.getSocioLogado(), data);
				
				if(Compra.getCompraAtual().getProduto().getNomeProduto().substring(0, 8).equals("Ingresso")){
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Compra Confirmada com sucesso! Vá até o Arruda no dia do jogo e retire seu ingresso com seu cartão de sócio!")
				       .setTitle("Compra").setPositiveButton("OK", listener);
				    builder.create().show();
				}
				else{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Compra Confirmada com sucesso! A compra chegará em torno de 5 dias!")
				       .setTitle("Compra").setPositiveButton("OK", listener);
				    builder.create().show();
				}
				/*Intent intent=new Intent(TelaConfirmarEndereco.this, TelaConfirmarCompra.class);
				TelaConfirmarCompra.tipoTela="pagamentoCompra";
				startActivity(intent);*/
			}
			
		}
		
	}


}
