package com.example.sociotorcedortricolor;

import dominio.Socio;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class TelaPagamentoTaxa extends Activity implements OnClickListener {
	Button btnTaxa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_pagamento_taxa);
		btnTaxa=(Button) findViewById(R.id.btnPagarTaxa);
		btnTaxa.setText("Pagar Taxa (R$ "+Socio.taxaAdesao+"0)");
		btnTaxa.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_pagamento_taxa, menu);
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
		// TODO Auto-generated method stub
		Intent intent=new Intent(TelaPagamentoTaxa.this, TelaConfirmarCompra.class);
		TelaConfirmarCompra.tipoTela= "pagamentoTaxa";
		startActivity(intent);
		
	}

}
