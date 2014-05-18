package com.br.socio_torcedor_tricolor.gui;

import bd.Banco;

import com.br.socio_torcedor_tricolor.R;

import dominio.Produto;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TelaEnviarCodigo extends Activity implements OnClickListener {

	Button btnEnviarCodigo;
	EditText txtFCodigo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_enviar_codigo);
		btnEnviarCodigo=(Button)findViewById(R.id.btnEnviarCodigo);
		btnEnviarCodigo.setOnClickListener(this);
		txtFCodigo=(EditText) findViewById(R.id.txtFCodigo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_enviar_codigo, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnEnviarCodigo){
			Banco banco=new Banco(TelaEnviarCodigo.this);
			String codigo=txtFCodigo.getText().toString();
			System.out.println(codigo + " Código");
			Produto p=banco.retorneProduto(codigo);
			if(p!=null){
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(" Parabéns! Você ganhou pontos!")
				       .setTitle("Parabéns!");
				AlertDialog dialog = builder.create();
				dialog.show();
				
			}
			else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(" Código Inválido ou já utilizado")
				       .setTitle("Falha");
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		}
		
	}


}
