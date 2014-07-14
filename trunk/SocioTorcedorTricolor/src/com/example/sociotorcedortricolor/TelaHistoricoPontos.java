package com.example.sociotorcedortricolor;

import java.util.ArrayList;

import negocio.CustomAdapter;
import negocio.HistoricoPontosAdapter;
import dominio.Produto;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class TelaHistoricoPontos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_historico_pontos);
		
		ArrayList<Produto> listaHistorico=new ArrayList<Produto>();
		bd.Banco banco=new bd.Banco(this);
		listaHistorico=banco.retorneListaHistorico(Socio.getSocioLogado());
		ArrayAdapter ad = new HistoricoPontosAdapter(this, R.layout.item_lista_produtos, listaHistorico);
	    ListView lv = (ListView) findViewById(R.id.lvHistorico);
	    lv.setAdapter(ad);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_historico_pontos, menu);
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
