package com.example.sociotorcedortricolor;

import dominio.Socio;
import bd.Banco;
import Fragments.FragmentCodigo;
import Fragments.FragmentLoja;
import Fragments.FragmentMensalidades;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TelaInicial extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ActionBar.TabListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment; 

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
     // for each of the sections in the app, add a tab to the action bar.
        actionBar.addTab(actionBar.newTab().setText("Loja")
            .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Código")
            .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Mensalidade")
            .setTabListener(this));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();

        // Set up the drawer.
       mNavigationDrawerFragment.setUp(
               R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction()
                //.//replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                //.commit();
    }

    
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.tela_inicial, menu);
            restoreActionBar();*/
    		getMenuInflater().inflate(R.menu.global, menu);
       
        
    		return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tela_inicial, container, false);
            Button btnCodigo, btnBoleto;
            
            btnCodigo= (Button) rootView.findViewById(R.id.btnCodigo);
            btnBoleto= (Button) rootView.findViewById(R.id.btnBoleto);
            
            btnCodigo.setOnClickListener(this);
            btnBoleto.setOnClickListener(this);
            
            
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            //((TelaInicial) activity).onSectionAttached(
                    //getArguments().getInt(ARG_SECTION_NUMBER));
        }

		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.btnCodigo){
				Intent intent=new Intent(getActivity(), TelaEnviarCodigo.class);
				startActivity(intent);
			}
			
			if(v.getId()==R.id.btnBoleto){
				Intent intent=new Intent(getActivity(), TelaBoleto.class);
				startActivity(intent);
			}
		}
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      // Restore the previously serialized current tab position.
      if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
        getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
      }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
      // Serialize the current tab position.
      outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
          .getSelectedNavigationIndex());
    }

    
    @Override
    public void onTabSelected(ActionBar.Tab tab,
        FragmentTransaction fragmentTransaction) {
      // When the given tab is selected, show the tab contents in the
      // container view.
    	Fragment fragment=new PlaceholderFragment();
    	if(tab.getPosition()==0){
			fragment= new FragmentLoja();
		}
		if(tab.getPosition()==1){
			fragment= new FragmentCodigo();
		}
			
		if(tab.getPosition()==2){
    	  fragment= new FragmentMensalidades();
		}
      
      
      getFragmentManager().beginTransaction()
          .replace(R.id.container, fragment).commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,
        FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
        FragmentTransaction fragmentTransaction) {
    }

}
