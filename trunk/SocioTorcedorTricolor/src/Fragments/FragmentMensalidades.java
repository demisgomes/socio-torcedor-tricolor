package Fragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import negocio.CustomAdapter;
import negocio.MensalidadeAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sociotorcedortricolor.R;
import com.example.sociotorcedortricolor.TelaConfirmarCompra;

import dominio.Mensalidade;
import dominio.Produto;
import dominio.Socio;

public class FragmentMensalidades extends Fragment implements OnClickListener{
	EditText txtFCodigo;
	public FragmentMensalidades() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_mensalidades, container, false);
		
		

		
		final ArrayList<Mensalidade> listaMensalidade;
		bd.MensalidadesDAO mDAO=new bd.MensalidadesDAO(getActivity());
		listaMensalidade=mDAO.retorneListaMensalidadesSocio(Socio.getSocioLogado());

		 ArrayAdapter ad = new MensalidadeAdapter(getActivity(), R.layout.item_lista_mensalidades, listaMensalidade);
	     ListView lv = (ListView) rootView.findViewById(R.id.lvListaMensalidades);
	     lv.setAdapter(ad);
		
	     lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Date data= new Date();
				DateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
				Date dataComparada=null;
				
				Intent intent = new Intent(getActivity(), TelaConfirmarCompra.class);
				intent.putExtra("precoMensalidade", listaMensalidade.get(position).getPreco());
				startActivity(intent);

				try {
					dataComparada = formato.parse(listaMensalidade.get(position).getDataVencimento());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(data.after(dataComparada));
				
			}
		});
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
	}

}
