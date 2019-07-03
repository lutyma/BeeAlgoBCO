package edu.asu.emit.qyan.alg.control;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Tablavalores {

	private ArrayList<Abeja> listaAbejas;

	public Tablavalores(ArrayList<Abeja> listaAbejas) {
		super();
		this.listaAbejas = listaAbejas;
	}
	
	public ArrayList<Abeja> valoresob(){
		ArrayList<Abeja> respuesta = new ArrayList<Abeja>();
		 double maximo = 0;
		 double minimo = 1000;
		 
		for(int i = 0; i < listaAbejas.size(); i++) {
			if(listaAbejas.get(i).getFuncionObjetivo() >= maximo)
			    maximo = listaAbejas.get(i).getFuncionObjetivo();
		}
		
		for(int j = 0; j < listaAbejas.size(); j++) {
			if(listaAbejas.get(j).getFuncionObjetivo() < minimo)
			   minimo = listaAbejas.get(j).getFuncionObjetivo();	
		}
		
		for (int k = 0; k < listaAbejas.size(); k++) {
			Abeja auxiliar = new Abeja();
			double ob = 0;

			ob = ((maximo - listaAbejas.get(k).getFuncionObjetivo()) / (maximo - minimo));
			ob = Math.round(ob * 100) / 100d;
			
			auxiliar = listaAbejas.get(k);
			auxiliar.setOb(ob);
			respuesta.add(auxiliar);
		}
		
		return respuesta;
	}
	
		public ArrayList<Abeja> valorespb(List<Abeja> datos){
			ArrayList<Abeja> respuesta = new ArrayList<Abeja>();
			double max = 0;
		    for (int i = 0; i < datos.size(); i++) {
		    	if(datos.get(i).getOb() > max)
					max = datos.get(i).getOb();
		    }
		    
		    for (int j = 0; j < datos.size(); j++) {
		    	Abeja auxiliar = new Abeja();	
		    	double pb = 0;
		    	
		        pb = 1 - Math.log(1 + (max - datos.get(j).getOb()));
		        
		        pb = Math.round(pb * 100) / 100d; 
		        auxiliar = datos.get(j);
		        auxiliar.setPb(pb);
		        respuesta.add(auxiliar);
		        
		    }
		     
			return respuesta;
	}
	
}
