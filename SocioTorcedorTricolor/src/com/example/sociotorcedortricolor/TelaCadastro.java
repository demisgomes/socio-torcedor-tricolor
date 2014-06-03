package com.example.sociotorcedortricolor;


import bd.Banco;
import dominio.Socio;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Build;

public class TelaCadastro extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_cadastro);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_cadastro, menu);
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
	public static class PlaceholderFragment extends Fragment implements OnClickListener{
		
		EditText etNome, etEmail, etSenha, etConfSenha, etCPF, etTelefone;
		Spinner spTipoSocio, spSexo;
		Button btnConfirmar;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tela_cadastro,
					container, false);
			
			
			
			etNome=(EditText) rootView.findViewById(R.id.etNomeCompleto);
			etEmail=(EditText) rootView.findViewById(R.id.etEmail);
			etSenha=(EditText) rootView.findViewById(R.id.etSenha);
			etConfSenha=(EditText) rootView.findViewById(R.id.etConfSenha);
			etCPF=(EditText) rootView.findViewById(R.id.etCPF);
			etTelefone=(EditText) rootView.findViewById(R.id.etTelefone);
			
			
			spTipoSocio = (Spinner) rootView.findViewById(R.id.spTipoSocio);
			ArrayAdapter<CharSequence> ar = ArrayAdapter.createFromResource(getActivity(),R.array.tipo_socio,android.R.layout.simple_list_item_1);
			ar.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			spTipoSocio.setAdapter(ar);
			
			spSexo = (Spinner) rootView.findViewById(R.id.spSexo);
			ArrayAdapter<CharSequence> ar2 = ArrayAdapter.createFromResource(getActivity(),R.array.sexo,android.R.layout.simple_list_item_1);
			ar.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			spSexo.setAdapter(ar2);
			
			btnConfirmar= (Button) rootView.findViewById(R.id.btnConfirmar);
			btnConfirmar.setOnClickListener(this);
			
			return rootView;
		}

		@Override
		public void onClick(View v) {
			
			if(v.getId()==R.id.btnConfirmar){
				
				String nome,email,senha,confSenha,cpf,telefone,tipoSocio,sexo;
				
				nome=etNome.getText().toString();
				email=etEmail.getText().toString();
				senha=etSenha.getText().toString();
				confSenha=etConfSenha.getText().toString();
				cpf=etCPF.getText().toString();
				telefone=etTelefone.getText().toString();
				tipoSocio=spTipoSocio.getSelectedItem().toString();
				sexo=spSexo.getSelectedItem().toString();
				
				Socio socio=new Socio(nome, email, senha, confSenha, cpf, telefone, tipoSocio, sexo);
				Banco banco=new Banco(getActivity());
				banco.cadastrarSocio(socio);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(" Parabéns! Você se associou!")
				       .setTitle("Parabéns");
				AlertDialog dialog = builder.create();
				dialog.show();
				
				Intent intent=new Intent(getActivity(), TelaLogin.class);
				startActivity(intent);
			}
			
		}
	}

}
