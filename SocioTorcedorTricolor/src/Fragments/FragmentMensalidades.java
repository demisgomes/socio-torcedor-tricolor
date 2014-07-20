package Fragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import negocio.CustomAdapter;
import negocio.MensalidadeAdapter;
import android.app.AlertDialog;
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
		
		 ArrayAdapter ad = new MensalidadeAdapter(getActivity(), R.layout.item_lista_mensalidades, Mensalidade.getListaMensalidades());
	     ListView lv = (ListView) rootView.findViewById(R.id.lvListaMensalidades);
	     lv.setAdapter(ad);
		
	     lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				if(Mensalidade.getListaMensalidades().get(position).getEmDia()==0){
					Intent intent = new Intent(getActivity(), TelaConfirmarCompra.class);
					TelaConfirmarCompra.tipoTela="pagamentoMensalidade";
					intent.putExtra("posicao", position);
					startActivity(intent);

				}
				
				if(Mensalidade.getListaMensalidades().get(position).getEmDia()==1){
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				    builder.setMessage("Pagamento de mensalidade realizado em "+Mensalidade.getListaMensalidades().get(position).getDataPagamento()+", adquirindo  "+Mensalidade.getListaMensalidades().get(position).getPontosAdquiridos()+" pontos já que pagou em dia")
				       .setTitle("Pagamento já efetuado").setPositiveButton("OK", null);
				    builder.create().show();

				}
				
				if(Mensalidade.getListaMensalidades().get(position).getEmDia()==2){
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				    builder.setMessage("Pagamento de mensalidade realizado em "+Mensalidade.getListaMensalidades().get(position).getDataPagamento()+", adquirindo  "+Mensalidade.getListaMensalidades().get(position).getPontosAdquiridos()+" pontos já que pagou depois do vencimento")
				       .setTitle("Pagamento já efetuado").setPositiveButton("OK", null);
				    builder.create().show();
				}
				
				
				
			}
		});
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
	}

}
