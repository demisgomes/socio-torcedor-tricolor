package com.br.socio_torcedor_tricolor;

import com.br.socio_torcedor_tricolor.gui.TelaEnviarCodigo;

import bd.Banco;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TelaInicial extends Activity implements OnClickListener {

	Button btnTelaEnviarCodigo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_inicial);
		Banco banco=new Banco (TelaInicial.this);
		banco.criarBanco();
		btnTelaEnviarCodigo=(Button) findViewById(R.id.btnTelaEnviarCodigo);
		btnTelaEnviarCodigo.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_inicial, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		if (v.getId()==R.id.btnTelaEnviarCodigo){
			Intent intent=new Intent(TelaInicial.this, TelaEnviarCodigo.class);
			startActivity(intent);
		}
		
	}

}
