package Fragments;

import negocio.CalculoPontos;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import bd.Banco;

import com.example.sociotorcedortricolor.R;

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
			System.out.println(codigo + " Código");
			Produto p=banco.retorneProduto(codigo);
			if(p!=null){
				CalculoPontos calculo =new CalculoPontos(getActivity(), Socio.getSocioLogado(), p.getPreco());
				banco.updatePontosSocio(Socio.getSocioLogado(), calculo.getPontos());
				banco.deletarProduto(p);
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(" Parabéns! Você ganhou "+calculo.getPontos()+" pontos! Agora você possui "+Socio.getSocioLogado().getPontos()+"pontos")
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

