package Fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import negocio.CalculoPontos;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import bd.Banco;

import com.example.sociotorcedortricolor.R;
import com.example.sociotorcedortricolor.TelaInicial;

import dominio.Produto;
import dominio.Socio;

public class FragmentCodigo extends Fragment implements OnClickListener {

	EditText txtFCodigo;
	public  FragmentCodigo() {
		// TODO Auto-generated constructor stub
	
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
			Produto p=banco.retorneProduto(codigo, 1);
			Date date=new Date();
			DateFormat formatar= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String data=formatar.format(date);
			if(p!=null){
				//CalculoPontos calculo =new CalculoPontos(Socio.getSocioLogado(), p.getPreco());
				banco.updatePontosSocio(Socio.getSocioLogado(), 1000);
				banco.inserirCompraHistorico(p, Socio.getSocioLogado(), data, 1000);
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(" Parab�ns! Voc� ganhou 1000 pontos! Agora voc� possui "+Socio.getSocioLogado().getPontos()+"pontos")
				       .setTitle("Parab�ns!");
				AlertDialog dialog = builder.create();
				dialog.show();
				
				Intent intent = new Intent(getActivity(), TelaInicial.class);
				startActivity(intent);
				
			}
			else{
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(" C�digo Inv�lido ou j� utilizado")
				       .setTitle("Falha");
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		}
	}
}

