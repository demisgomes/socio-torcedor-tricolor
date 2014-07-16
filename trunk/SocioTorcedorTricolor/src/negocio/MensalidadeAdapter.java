package negocio;

import java.util.List;






import bd.Banco;

import com.example.sociotorcedortricolor.R;

import dominio.Mensalidade;
import dominio.Produto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
 
public class MensalidadeAdapter extends ArrayAdapter<Mensalidade> {
 
    /*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;
 
    /*
     * each list item layout ID
     */
    private final int resourceId;
 
    public MensalidadeAdapter(Context context, int resource, List<Mensalidade> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
       
 
        // get a new View no matter recycling or ViewHolder FIXME
        convertView = inflater.inflate(resourceId, parent, false);
 
        //get all object from view
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView mes = (TextView) convertView.findViewById(R.id.mes);
        TextView preco = (TextView) convertView.findViewById(R.id.preco);
        TextView data = (TextView) convertView.findViewById(R.id.dataVencimento);
        
        Mensalidade mensalidade=getItem(position);
        
        //fill the view objects according values from Evento object
        if(mensalidade.getEmDia()==1){
        	image.setBackgroundResource(R.drawable.sucesso);
        }
        String mes1= mensalidade.getDataVencimento().substring(3, 5);
        String mesFinal="";
        if(mes1.equals("12")){
        	mesFinal="Dezembro";
        }
        
        if(mes1.equals("01")){
        	mesFinal="Janeiro";
        }
        if(mes1.equals("02")){
        	mesFinal="Fevereiro";
        }
        if(mes1.equals("03")){
        	mesFinal="Março";
        }
        if(mes1.equals("04")){
        	mesFinal="Abril";
        }
        if(mes1.equals("05")){
        	mesFinal="Maio";
        }
        if(mes1.equals("06")){
        	mesFinal="Junho";
        }
        if(mes1.equals("07")){
        	mesFinal="Julho";
        }
        if(mes1.equals("08")){
        	mesFinal="Agosto";
        }
        if(mes1.equals("09")){
        	mesFinal="Setembro";
        }
        if(mes1.equals("10")){
        	mesFinal="Outubro";
        }
        if(mes1.equals("11")){
        	mesFinal="Novembro";
        }
        mes.setText(mesFinal);
        preco.setText("R$ "+mensalidade.getPreco()+"0" );
        data.setText(mensalidade.getDataVencimento());
        return convertView;
    }
 
}
