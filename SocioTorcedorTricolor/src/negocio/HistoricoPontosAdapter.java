package negocio;

import java.util.List;





import bd.Banco;

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
 
public class HistoricoPontosAdapter extends ArrayAdapter<Produto> {
 
    /*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;
 
    /*
     * each list item layout ID
     */
    private final int resourceId;
 
    public HistoricoPontosAdapter(Context context, int resource, List<Produto> objects) {
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
        TextView qtd = (TextView) convertView.findViewById(R.id.qtd);
        TextView pontos = (TextView) convertView.findViewById(R.id.pontos);
        
        
     
        //get the Evento from position
        Produto produto = getItem(position);
        nome.setText(produto.getNomeProduto());
        int drawable=R.drawable.santa_cruz_2013;
        if(produto.getNomeProduto().equals("Calção Oficial")){
        	drawable=R.drawable.calcao_santa_cruz;
        }
        
        if(produto.getNomeProduto().equals("Camisa Polo")){
        	drawable=R.drawable.camisa_polo_santa_cruz;
        }
        if(produto.getNomeProduto().equals("Garrafa Oficial")){
        	drawable=R.drawable.garrafa_santa_cruz;
        }
        if(produto.getNomeProduto().length()>1 && produto.getNomeProduto().substring(0, 8).equals("Ingresso")){
        	drawable=R.drawable.ingresso_santa_cruz;
        	nome.setTextSize(13);
        }
        if(produto.getNomeProduto().equals("Toalha Oficial")){
        	drawable=R.drawable.toalha_santa_cruz;
        }
        //fill the view objects according values from Evento object
        if(produto.getPontosAdquiridos()==0){
        	preco.setText("Custou "+ produto.getPontos()+" pontos");
        	pontos.setText("");
        }
        else{
        	if(produto.getNomeProduto().length()>1 && produto.getNomeProduto().substring(0, 8).equals("Ingresso")){
        		preco.setText("Custou R$"+ produto.getPreco()+"0");
        	}else{
        		if(produto.getNomeProduto().length()==1){
        			preco.setText("");
        			nome.setText(produto.getCodigo());
        		}
        		else{
        			preco.setText("Custou R$"+ produto.getPreco()+"0 +Frete");
        		}
        		
        	}
        	
        	 pontos.setText("Ganhou "+ produto.getPontosAdquiridos()+" pontos");
        }
        
        
       
        image.setBackgroundResource(drawable);
    	qtd.setText(produto.getDataCompra());
        return convertView;
    }
 
}
