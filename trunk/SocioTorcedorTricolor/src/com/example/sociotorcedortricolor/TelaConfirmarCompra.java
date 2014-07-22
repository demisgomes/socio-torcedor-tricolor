package com.example.sociotorcedortricolor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import negocio.CalculoPontos;
import bd.Banco;
import bd.CartaoDAO;
import bd.MensalidadesDAO;
import bd.SocioDAO;
import dominio.Cartao;
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
	int posicaoMensalidade;
	
	public static String tipoTela;
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
	    posicaoMensalidade = intent.getIntExtra("posicao", 0);
		
	    if(Socio.getSocioLogado().getCartao()!=null){
	    	etNomeTitular.setText(Socio.getSocioLogado().getCartao().getTitular());
			etNumeroCartao.setText(Socio.getSocioLogado().getCartao().getNumero());
			etCodSeguranca.setText(Socio.getSocioLogado().getCartao().getCodSeguranca());
			etCPFTitular.setText(Socio.getSocioLogado().getCartao().getCpfTitular());
			etDataVencimento.setText(Socio.getSocioLogado().getCartao().getVencimento());
			
	    }
		
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
		
		android.content.DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TelaConfirmarCompra.this, TelaInicial.class);
				startActivity(intent);
			}
		};
		
		
		
		if(v.getId()==R.id.btnConfirmarCompra){
			nomeTitular=etNomeTitular.getText().toString();
			numeroCartao=etNumeroCartao.getText().toString();
			codSeguranca=etCodSeguranca.getText().toString();
			cpfTitular=etCPFTitular.getText().toString();
			dataVencimento=etDataVencimento.getText().toString();
			
			Cartao cartao=new Cartao(numeroCartao, codSeguranca, nomeTitular, dataVencimento, cpfTitular);
			CartaoDAO cDAO=new CartaoDAO(this);
			Cartao cartaoValidado= cDAO.validarCartao(cartao);
			
			//---------------------------------
			//SE FOR TELA DE PAGAMENTO DA TAXA
			//---------------------------------
			if(tipoTela.equals("pagamentoTaxa")){
				
				if(cartaoValidado!=null && cartaoValidado.getLimite()>Socio.taxaAdesao){
					cartaoValidado.setLimite(cartaoValidado.getLimite()-Socio.taxaAdesao);
					
					cDAO.updateLimite(cartaoValidado, cartaoValidado.getLimite());
					SocioDAO sDAO=new SocioDAO(this);
					sDAO.updateSituacao(Socio.getSocioLogado(), 1);
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Pagamento da Taxa Aprovado")
				       .setTitle("Validado").setPositiveButton("OK", listener);
				    builder.create().show();
				    
				}
			}
			
			
			//---------------------------------
			//SE FOR TELA DE PAGAMENTO DA MENSALIDADE
			//---------------------------------
			if(tipoTela.equals("pagamentoMensalidade")){
				Mensalidade mensalidade=Mensalidade.getListaMensalidades().get(posicaoMensalidade);
				
				if(cartaoValidado!=null && cartaoValidado.getLimite()>mensalidade.getPreco()){
					
					Date data= new Date();
					DateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
					Date dataComparada=null;
					
					try {
						dataComparada = formato.parse(mensalidade.getDataVencimento());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(data.after(dataComparada));
					
					cartaoValidado.setLimite(cartaoValidado.getLimite()-mensalidade.getPreco());
					
					cDAO.updateLimite(cartaoValidado, cartaoValidado.getLimite());
					
					MensalidadesDAO mDAO=new MensalidadesDAO(this);
					int emDia;
					if(data.after(dataComparada)){
						emDia=2;
					}
					else{
						emDia=1;
						SocioDAO sDAO= new SocioDAO(this);
						CalculoPontos calculo= new CalculoPontos(Socio.getSocioLogado(), mensalidade);
						sDAO.updatePontosSocio(Socio.getSocioLogado(), calculo.getPontos());
						mDAO.updateMensalidade(mensalidade, calculo.getPontos());
						
					}
					
					mensalidade.setEmDia(emDia);
					mensalidade.setDataPagamento(formato.format(data));
					
					mDAO.updateMensalidade(mensalidade);
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Pagamento de mensalidade Aprovado")
				       .setTitle("Validado").setPositiveButton("OK", listener );
				    builder.create().show();
				    
				    
				}
				
				else{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Pagamento recusado")
				       .setTitle("Recusado").setPositiveButton("OK", null);
				    builder.create().show();
				}
			}
			
			
			if(tipoTela.equals("pagamentoCompra")){
				if(cartaoValidado!=null && cartaoValidado.getLimite()>(Compra.getCompraAtual().getProduto().getPreco()*Compra.getCompraAtual().getQtdProdutos()+5)){
					Banco banco=new Banco(this);
					Date date=new Date();
					SimpleDateFormat formatar= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					String data=formatar.format(date);
					SocioDAO banco2=new SocioDAO (this);
					cartaoValidado.setLimite(cartaoValidado.getLimite()-(Compra.getCompraAtual().getProduto().getPreco()*Compra.getCompraAtual().getQtdProdutos()+5));
					
					cDAO.updateLimite(cartaoValidado, cartaoValidado.getLimite());
					//CalculoPontos calculo=new CalculoPontos(Socio.getSocioLogado(), )
					//banco2.updatePontosSocio(Socio.getSocioLogado(), -(Compra.getCompraAtual().getQtdProdutos()*Compra.getCompraAtual().getProduto().getPontos()));
					
					banco.inserirCompraHistoricoDinheiro(Compra.getCompraAtual().getProduto().getNomeProduto(), Compra.getCompraAtual().getQtdProdutos(), Socio.getSocioLogado(), data);
					
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Compra Confirmada com sucesso! A compra chegará em torno de 5 dias!")
				       .setTitle("Compra").setPositiveButton("OK", listener);
				    builder.create().show();
				}
				else{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setMessage("Pagamento recusado")
				       .setTitle("Recusado").setPositiveButton("OK", null);
				    builder.create().show();
				}
			}
			
		}
		
	}

	
}
