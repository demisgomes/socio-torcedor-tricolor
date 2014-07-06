package com.example.sociotorcedortricolor;

import dominio.Socio;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class TelaPerfil extends Activity {
	
	private TextView tvNomePerfil,tvTipoSocio,tvEmail, tvPontos;
	private Button btnHistorico;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_perfil);
		
		tvNomePerfil= (TextView) findViewById(R.id.tvNomePerfil);
		tvTipoSocio= (TextView) findViewById(R.id.tvTipoSocio);
		tvEmail= (TextView) findViewById(R.id.tvEmail);
		tvPontos= (TextView) findViewById(R.id.tvPontos);
		btnHistorico= (Button) findViewById(R.id.btnHistorico);
		
		Socio socio= Socio.getSocioLogado();
		
		tvNomePerfil.setText(socio.getNome());
		tvTipoSocio.setText(socio.getTipoSocio());
		tvEmail.setText(socio.getEmail());
		tvPontos.setText("Pontos "+ Integer.toString(socio.getPontos()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_perfil, menu);
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


}
