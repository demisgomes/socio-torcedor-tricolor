package com.example.sociotorcedortricolor;

import bd.CartaoDAO;
import dominio.Cartao;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class TelaConfirmarCompra extends Activity implements OnClickListener {

	EditText etNumeroCartao, etCodSeguranca, etNomeTitular, etCPFTitular, etDataVencimento;
	String nomeTitular, numeroCartao, codSeguranca, cpfTitular, dataVencimento;
	Button btnConfirmarCompra;
	float preco;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_confirmar_compra);
		
		etNomeTitular=(EditText) findViewById(R.id.etNomeTitular);

		etNumeroCartao=(EditText) findViewById(R.id.etNumCartao);

		etCodSeguranca=(EditText) findViewById(R.id.etCodCartao);

		etCPFTitular=(EditText) findViewById(R.id.etCPFtitular);

		etDataVencimento=(EditText) findViewById(R.id.etDataVencimento);
		btnConfirmarCompra=(Button) findViewById(R.id.btnConfirmarCompra);
		btnConfirmarCompra.setOnClickListener(this);
		
		Intent intent = getIntent();
	    preco = intent.getFloatExtra("precoMensalidade", 0);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_confirmar_compra, menu);
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
		if(v.getId()==R.id.btnConfirmarCompra){
			nomeTitular=etNomeTitular.getText().toString();
			numeroCartao=etNumeroCartao.getText().toString();
			codSeguranca=etCodSeguranca.getText().toString();
			cpfTitular=etCPFTitular.getText().toString();
			dataVencimento=etDataVencimento.getText().toString();
			
			Cartao cartao=new Cartao(numeroCartao, codSeguranca, nomeTitular, dataVencimento, cpfTitular);
			CartaoDAO cDAO=new CartaoDAO(this);
			Cartao cartaoValidado= cDAO.validarCartao(cartao);
			
			if(cartaoValidado!=null && cartaoValidado.getLimite()>preco){
				cartaoValidado.setLimite(cartaoValidado.getLimite()-preco);
				//cDAO.updateLimite()
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
			    builder.setMessage("Pagamento de Boleto Aprovado")
			       .setTitle("Validado");
			    builder.create().show();
			    
			    Intent intent = new Intent(this, TelaInicial.class);
			    startActivity(intent);
			}
		}
		
	}

	
}
