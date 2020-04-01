package edu.asu.emit.qyan.alg.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AsignacionDemanda {

	private List<Request> demandas;
	private int nroAbeja;
	private GrafoMatriz g;
	private List<Abeja> abejas = new ArrayList<Abeja>();
	
	public AsignacionDemanda() {
		
	}

	public AsignacionDemanda(List<Request> demandas, int nroAbeja, GrafoMatriz g) {
		super();
		this.demandas = demandas;
		this.nroAbeja = nroAbeja;
		this.g = g;
	}

	public List<Abeja> asignacionAbeja() {
		int aux = 0;
		for(int i=1; i <= nroAbeja; i++) {
			ArrayList<Request> demanNuevas = new ArrayList<Request>();
			ArrayList<Identificador> vecauxiliar = new ArrayList<Identificador>();
			Abeja abe = new Abeja(i, g);

    // auxiliar que indica que la primera vez las solicitudes se le asignan asi como estan a la primera abeja.
	// en caso contrario se realiza un reordenamiento multiplicando el fs de cada demanda.		
			if(aux==0) {
				abe.setDemandas(demandas); 
				aux++;
	//		System.out.println("abe1:" + abe);
			}
			else {
				for(int j = 0; j < demandas.size(); j++) {
					
				// Identificador es un objeto que se utiliza para mantener el fs original ya que para el 
			    // reordenamiento se multiplica el fs de cada demanda luego se ordena y hay volver a traer el fs original.		
					Identificador auxiliarId = new Identificador();
					Request auxiliar = new Request(demandas.get(j).getOrigen(), demandas.get(j).getDestino(), demandas.get(j).getFs(), demandas.get(j).getId());
					int numeroAleatorio = (int) (Math.random() * 5) + 1;
					auxiliarId.setRandom(numeroAleatorio);
					auxiliar.setFs((auxiliar.getFs()*numeroAleatorio));
					auxiliarId.setRequest(auxiliar);
					auxiliarId.setId(j);	
				//	System.out.println("fssss:" + auxiliar.getFs());
					vecauxiliar.add(auxiliarId); 
				}
		//		System.out.println("vecauxiliar:" + vecauxiliar);
				demanNuevas = conversionfsnormal(vecauxiliar);
		//		System.out.println("demandasnuevas"+demanNuevas);
				abe.setDemandas(demanNuevas);

			}
			abejas.add(abe);

		//	System.out.println("abejas"+abejas);
		//	System.out.println("cilclo2");
		}    	  

		for(Abeja ab:abejas) {

	//		System.out.println("lista inicial de abejas:"+ ab + "tamaño:"+ ab.getDemandas().size());
		}
		return abejas;
	}

	
	public List<Abeja> asignacionContinua(ArrayList<Abeja> listaAbejaResult){
		
		int aux = 0;
		for(int i=0; i < listaAbejaResult.size(); i++) {
			ArrayList<Request> demanNuevas = new ArrayList<Request>();
			ArrayList<Identificador> vecauxiliar = new ArrayList<Identificador>();
			Abeja abe = listaAbejaResult.get(i);

    // auxiliar que indica que la primera vez las solicitudes se le asignan asi como estan a la primera abeja.
	// en caso contrario se realiza un reordenamiento multiplicando el fs de cada demanda.		
			if(aux==0) {
				abe.setDemandas(demandas); 
				aux++;
	//		System.out.println("abe1:" + abe);
			}
			else {
				for(int j = 0; j < demandas.size(); j++) {
					
				// Identificador es un objeto que se utiliza para mantener el fs original ya que para el 
			    // reordenamiento se multiplica el fs de cada demanda luego se ordena y hay volver a traer el fs original.		
					Identificador auxiliarId = new Identificador();
					Request auxiliar = new Request(demandas.get(j).getOrigen(), demandas.get(j).getDestino(), demandas.get(j).getFs(), demandas.get(j).getId());
					int numeroAleatorio = (int) (Math.random() * 5) + 1;
					auxiliarId.setRandom(numeroAleatorio);
					auxiliar.setFs((auxiliar.getFs()*numeroAleatorio));
					auxiliarId.setRequest(auxiliar);
					auxiliarId.setId(j);	
				//	System.out.println("fssss:" + auxiliar.getFs());
					vecauxiliar.add(auxiliarId); 
				}
		//		System.out.println("vecauxiliar:" + vecauxiliar);
				demanNuevas = conversionfsnormal(vecauxiliar);
		//		System.out.println("demandasnuevas"+demanNuevas);
				abe.setDemandas(demanNuevas);

			}
			abejas.add(abe);

		//	System.out.println("abejas"+abejas);
		//	System.out.println("cilclo2");
		}    	  

		for(Abeja ab:abejas) {

	//		System.out.println("lista inicial de abejas:"+ ab + "tamaño:"+ ab.getDemandas().size());
		}
		return abejas;
		
	}
	
	public ArrayList<Request> conversionfsnormal(ArrayList<Identificador> conversiones){
		ArrayList<Request> demandas = new ArrayList<Request>();
		int origen = 0;
		int destino = 0;
		int idRequest = 0;
		int fs = 0;
		int id = 1000;
		int aux = 0;
		
		// se recorre el todas las demandas y se ordena de mayor a menor esto una vez que ya se multiplico el fs para el reordenamiento.
		for(int j = 0; j < conversiones.size(); j++) {
			origen = 0;
			destino = 0;
			idRequest = 0;
			fs = 0;
			aux = 0;
			for(int i = 0; i < conversiones.size(); i++) {
				if(conversiones.get(i).getRequest().getFs() > fs && conversiones.get(i).getMarcado() != 1) {
					origen = conversiones.get(i).getRequest().getOrigen();
					destino = conversiones.get(i).getRequest().getDestino();
					idRequest = conversiones.get(i).getRequest().getId();
					fs = (conversiones.get(i).getRequest().getFs());
					aux = i;
					//   auxiliar = new Request(conversiones.get(i).getRequest().getOrigen(), conversiones.get(i).getRequest().getDestino(), fs);
					//	auxiliar.setOrigen(conversiones.get(i).getRequest().getOrigen());
					//	auxiliar.setDestino(conversiones.get(i).getRequest().getDestino());
					//	auxiliar.setFs(fs);
				}

			}
			id = conversiones.get(aux).getId();
			
			// una vez ordenado se divide el fs por el numero por el cual se multiplico para asi volver a su valor original.
			fs = (conversiones.get(aux).getRequest().getFs()/conversiones.get(aux).getRandom());
			conversiones.get(aux).setMarcado(1);
		//	System.out.println("parametros" + origen + ","+ destino + ","+fs + ","+ id);
			Request auxiliar = new Request(origen, destino, fs, idRequest);
			
			demandas.add(auxiliar);
		}
		return demandas;
	}


}
