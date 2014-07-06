package Fragments;

import java.util.ArrayList;

import negocio.CustomAdapter;



import com.example.sociotorcedortricolor.R;

import dominio.Produto;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
		//faz o listView ter os nomes de times
		 ArrayAdapter ad = new CustomAdapter(getActivity(), R.layout.item_lista_produtos, listaProdutos);
	     ListView lv = (ListView) rootView.findViewById(R.id.lvProdutos);
	     lv.setAdapter(ad);
		
	     TextView tvSociosAptos= (TextView) rootView.findViewById(R.id.tvSociosAptos);
	     tvSociosAptos.setText("Sócios Adimplentes: "+ banco.conteSocios());
		return rootView;
	}
}
