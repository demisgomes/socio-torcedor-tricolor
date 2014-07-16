package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sociotorcedortricolor.R;

public class FragmentMensalidades extends Fragment implements OnClickListener{
	EditText txtFCodigo;
	public FragmentMensalidades() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_mensalidades, container, false);
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		
	}

}
