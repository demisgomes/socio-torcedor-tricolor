package Fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import negocio.CalculoPontos;
import negocio.CustomAdapter;










import bd.Banco;

import com.example.sociotorcedortricolor.R;
import com.example.sociotorcedortricolor.TelaInicial;

import dominio.Produto;
import dominio.Socio;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentLoja extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static FragmentLoja newInstance(int sectionNumber) {
		FragmentLoja fragment = new FragmentLoja();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public FragmentLoja() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_loja,
				container, false);
		ArrayList<Produto> listaProdutos=new ArrayList<Produto>();
		bd.Banco banco=new bd.Banco(getActivity());
		listaProdutos=banco.retorneListaProdutosSeparados();
		
		//for(Produto p: listaProdutos){
			//System.out.println(p.getNomeProduto());
		//}
		//faz o listView ter os nomes de times
		 ArrayAdapter ad = new CustomAdapter(getActivity(), R.layout.item_lista_produtos, listaProdutos);
	     ListView lv = (ListView) rootView.findViewById(R.id.lvProdutos);
	     lv.setAdapter(ad);
		
	     TextView tvSociosAptos= (TextView) rootView.findViewById(R.id.tvSociosAptos);
	     tvSociosAptos.setText("Sócios Adimplentes: "+ banco.conteSocios());
	     
	    lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				
				
				Banco banco=new Banco(getActivity());
				
				int qtd=banco.getCountProduto(Produto.getListaProdutosSeparados().get(position).getNomeProduto());
				
				String array_spinner[];
				array_spinner=new String[qtd];
				
				for (int i=0;i<array_spinner.length;i++){
					array_spinner[i]=Integer.toString(i+1);
				}
								
				final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),
			            android.R.layout.simple_spinner_item, array_spinner);

			    final Spinner sp = new Spinner(getActivity());
			    sp.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			    sp.setAdapter(adp);
			    
			    
			    DialogInterface.OnClickListener listener= new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int qtdDesejada=sp.getSelectedItemPosition()+1;
						int pontosCompra= Produto.getListaProdutosSeparados().get(position).getPontos()*qtdDesejada;
						int pontosSocio= Socio.getSocioLogado().getPontos();
						String mensagem;
						if(pontosSocio>=pontosCompra){
							 mensagem="Compra efetuada com sucesso!";
							Banco banco=new Banco(getActivity());
							Date date=new Date();
							DateFormat formatar= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							String data=formatar.format(date);
							banco.inserirCompraHistorico(Produto.getListaProdutosSeparados().get(position).getNomeProduto(), qtdDesejada, Socio.getSocioLogado(), data);
							Banco banco2=new Banco (getActivity());
							banco2.updatePontosSocio(Socio.getSocioLogado(), -(qtdDesejada*Produto.getListaProdutosSeparados().get(position).getPontos()));
							//Banco banco3= new Banco(getActivity());
							//CalculoPontos calculo=new CalculoPontos(null, Socio.getSocioLogado(), Produto.getListaProdutosSeparados().get(position).getPreco());
							//banco3.updatePontosSocio(Socio.getSocioLogado(), (calculo.getPontos()*qtdDesejada));
							
							Intent intent=new Intent(getActivity(), TelaInicial.class);
							startActivity(intent);
						}
						else{
							mensagem="Você não tem pontos suficientes";
							
						}
						
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					    builder.setMessage(mensagem)
					       .setTitle("Compra");
					    builder.create().show();
						
					}
				};

			    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			    builder.setView(sp);
			    builder.setMessage(" Escolha a quantidade de produtos")
			       .setTitle("Compra").setPositiveButton("OK", listener);
			    builder.create().show();
				
			    
				/*Context mContext = getActivity().getApplicationContext();
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View layout = inflater.inflate(R.layout.spinner,null);

				

				Spinner s = (Spinner) layout.findViewById(R.id.Spinner01);

				ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, array_spinner);

				s.setAdapter(adapter);

				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setView(layout);
				//AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(" Escolha a quantidade de prodtuos")
				       .setTitle("Compra");
				
				AlertDialog dialog = builder.create();
				dialog.show();*/
			}
	    	 
	     
	    });
		return rootView;
	}
}
