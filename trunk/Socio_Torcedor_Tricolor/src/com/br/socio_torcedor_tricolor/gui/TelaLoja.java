package com.br.socio_torcedor_tricolor.gui;

import com.br.socio_torcedor_tricolor.R;
import com.br.socio_torcedor_tricolor.R.layout;
import com.br.socio_torcedor_tricolor.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TelaLoja extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_loja);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_loja, menu);
		return true;
	}

}
