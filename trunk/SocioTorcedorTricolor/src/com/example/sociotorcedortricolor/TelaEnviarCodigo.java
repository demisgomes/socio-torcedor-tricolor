package com.example.sociotorcedortricolor;


import bd.Banco;


import dominio.Produto;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
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

public class TelaEnviarCodigo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_enviar_codigo);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_enviar_codigo, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {

		EditText txtFCodigo;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_tela_enviar_codigo, container, false);
			Button btnEnviarCodigo;
			
			btnEnviarCodigo=(Button)rootView.findViewById(R.id.btnEnviarCodigo);
			btnEnviarCodigo.setOnClickListener(this);
			txtFCodigo=(EditText) rootView.findViewById(R.id.txtFCodigo);
			
			return rootView;
		}

		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.btnEnviarCodigo){
				
				Banco banco=new Banco(getActivity());
				String codigo=txtFCodigo.getText().toString();
				System.out.println(codigo + " Código");
				Produto p=banco.retorneProduto(codigo);
				if(p!=null){
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setMessage(" Parabéns! Você ganhou pontos!")
					       .setTitle("Parabéns!");
					AlertDialog dialog = builder.create();
					dialog.show();
					
				}
				else{
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setMessage(" Código Inválido ou já utilizado")
					       .setTitle("Falha");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		}
	}

}
