package negocio;

import dominio.Socio;
import android.content.Context;

public class CalculoPontos {
	public int pontos=0;
	public CalculoPontos(Context context, Socio socio, float preco){
		
		if(socio.getTipoSocio().equals("Master")){
			pontos=(int)(preco*0.8);
		}
		if(socio.getTipoSocio().equals("Ouro")){
			pontos=(int)(preco*0.7);
		}
		if(socio.getTipoSocio().equals("Prata")){
			pontos=(int)(preco*0.6);
		}
		if(socio.getTipoSocio().equals("Patrimonial")){
			pontos=(int)(preco*0.45);
		}
		
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	

}
