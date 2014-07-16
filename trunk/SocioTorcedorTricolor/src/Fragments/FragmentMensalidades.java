package Fragments;

import java.util.ArrayList;

import negocio.CustomAdapter;
import negocio.MensalidadeAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sociotorcedortricolor.R;

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
		
		ArrayList<Mensalidade> listaMensalidade=new ArrayList<Mensalidade>();
		bd.MensalidadesDAO mDAO=new bd.MensalidadesDAO(getActivity());
		listaMensalidade=mDAO.retorneListaMensalidadesSocio(Socio.getSocioLogado());

		 ArrayAdapter ad = new MensalidadeAdapter(getActivity(), R.layout.item_lista_mensalidades, listaMensalidade);
	     ListView lv = (ListView) rootView.findViewById(R.id.lvListaMensalidades);
	     lv.setAdapter(ad);
		
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
	}

}
