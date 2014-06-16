package negocio;

import java.util.List;




import com.example.sociotorcedortricolor.R;

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
 
public class CustomAdapter extends ArrayAdapter<Produto> {
 
    /*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;
 
    /*
     * each list item layout ID
     */
    private final int resourceId;
 
    public CustomAdapter(Context context, int resource, List<Produto> objects) {
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
        TextView nome = (TextView) convertView.findViewById(R.id.titulo);
        TextView preco = (TextView) convertView.findViewById(R.id.preco);

        image.setBackgroundResource(R.drawable.santa_cruz_2013);
        
        //get the Evento from position
        Produto produto = getItem(position);
 
        //fill the view objects according values from Evento object
        nome.setText(produto.getNomeProduto());
        preco.setText(Float.toString(produto.getPreco()));
        
      
        
        return convertView;
    }
 
}
