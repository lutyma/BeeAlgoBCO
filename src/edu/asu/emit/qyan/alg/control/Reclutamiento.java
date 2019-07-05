package edu.asu.emit.qyan.alg.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Reclutamiento {

	private ArrayList<Abeja> listaAbejas;
	private float rmd; 
	private int pasofinal;

	public Reclutamiento(ArrayList<Abeja> listaAbejas, float rmd, int pasofinal) {
		super();
		this.listaAbejas = listaAbejas;
		this.rmd = rmd;
		this.pasofinal = pasofinal;
	}



	public ArrayList<Abeja> reclutarAbejas(){
		
		ordenarListaAbejas(listaAbejas);
		int menorBloqueo = listaAbejas.get(0).getContadorBloqueo();
		
		for(Abeja ab:listaAbejas) {
			System.out.println("lista abejas ordenadas:"+ ab);
		}
		
		ArrayList<Abeja> respuesta = new ArrayList<Abeja>();
		ArrayList<Abeja> seguidoras = new ArrayList<Abeja>();
		ArrayList<Abeja> reclutadoras = new ArrayList<Abeja>();
		ArrayList<Abeja> reclutadoraspb = new ArrayList<Abeja>();
		double sumatoriapb = 0;

		for (int i = 0; i < listaAbejas.size(); i++) {

			if( listaAbejas.get(i).getContadorBloqueo() == menorBloqueo && listaAbejas.get(i).getPb() > rmd) {
				reclutadoras.add(listaAbejas.get(i));
				//	System.out.println("abejas seguidoras:"+ listaAbejas.get(i));

			}
			else {

				seguidoras.add(listaAbejas.get(i));
				//	System.out.println("abejas reclutadoras:"+ listaAbejas.get(i));

			}

		}

		for(Abeja ab:seguidoras) {

			System.out.println("lista de abejas seguidoras:"+ ab + "tamaño:"+ ab.getDemandas().size());
		}
		System.out.println();

		for (int j = 0; j < reclutadoras.size(); j++) {
			sumatoriapb = sumatoriapb + reclutadoras.get(j).getPb();

			//	System.out.println("Numero de abejas reclutadoras"+ reclutadoras.size());
		}
		
		for (int k = 0; k < reclutadoras.size(); k++) {
			Abeja auxiliar = new Abeja();

			double pbreclutamiento = (float)reclutadoras.get(k).getOb() / sumatoriapb;
			pbreclutamiento = Math.round(pbreclutamiento * 100) / 100d; 
			//	System.out.println("pbreclutamiento:"+ pbreclutamiento);

			auxiliar = reclutadoras.get(k);	
			auxiliar.setReclut(pbreclutamiento);
			reclutadoraspb.add(auxiliar);
			respuesta.add(auxiliar);
		}

		for(Abeja ab:reclutadoraspb) {

			System.out.println("lista de abejas reclutadoras:"+ ab + "tamaño:"+ ab.getDemandas().size());
		}
	//	System.out.println();
		
		if(!seguidoras.isEmpty()) {
			double sumapb = 0;
			double numeroAleatorio = 0;
			double sumaseleccion = 0;

			for(int a = 0; a < reclutadoraspb.size(); a++) {

				sumapb = sumapb + reclutadoraspb.get(a).getReclut();
			}

			for (int z = 0; z < seguidoras.size(); z++) {		
				numeroAleatorio = (Math.random() * sumapb);
				Abeja abemutada = new Abeja();
				int x = 0;
				//	System.out.println("sumapb:"+ sumapb);
				//	System.out.println("random:"+ numeroAleatorio);
				while( x < reclutadoraspb.size() ){
					sumaseleccion = sumaseleccion + reclutadoraspb.get(x).getReclut();
					if(sumaseleccion >= numeroAleatorio) {
						
						abemutada = mutacion(seguidoras.get(z), reclutadoraspb.get(x));
						abemutada.setReclut(0);
						x = reclutadoraspb.size();     
					}
					x++;
				}
				respuesta.add(abemutada);
			} 
		}


		return respuesta;
	}

	public Abeja mutacion(Abeja seguidora, Abeja reclutadora) {
		int aux = 0;
		List<Request> lista = new ArrayList<Request>();
		for(int a = 0; a < reclutadora.getDemandas().size(); a++) {
			Request auxiliar = new Request(reclutadora.getDemandas().get(a).getOrigen(), reclutadora.getDemandas().get(a).getDestino(), reclutadora.getDemandas().get(a).getFs());
		    lista.add(auxiliar);
		}
		Abeja respuesta_nueva_abeja = seguidora;
		AsignacionDemanda ordenar = new AsignacionDemanda();
		ArrayList<Request> demanNuevas = new ArrayList<Request>();
		ArrayList<Identificador> vecauxiliar = new ArrayList<Identificador>();
		respuesta_nueva_abeja.setG(reclutadora.getG()); 
		respuesta_nueva_abeja.setDemandas(lista);
		List<Request> demandasactuales = lista;
		//   System.out.println("demanda a copiar" + reclutadora.getDemandas());

		// se vuelve a ordenar las demandas restante de la abeja seguidora
		for(int i = pasofinal+1; i < seguidora.getDemandas().size(); i++) {
			// 	 System.out.println("pasofinal:"+ pasofinal);

			Identificador auxiliarId = new Identificador();
			Request auxiliar = new Request(seguidora.getDemandas().get(i).getOrigen(), seguidora.getDemandas().get(i).getDestino(), seguidora.getDemandas().get(i).getFs());
			int numeroAleatorio = (int) (Math.random() * 5) + 1;
			auxiliarId.setRandom(numeroAleatorio);
			auxiliar.setFs((auxiliar.getFs()*numeroAleatorio));
			auxiliarId.setRequest(auxiliar);
			auxiliarId.setId(i);
			vecauxiliar.add(auxiliarId); 
			//	System.out.println("demandas a ordenar:"+ vecauxiliar);
		}
		demanNuevas = ordenar.conversionfsnormal(vecauxiliar);
		//	System.out.println("demandas reordenadas:"+ demanNuevas);
		//    System.out.println("tamaño:"+ demanNuevas.size()); 
		for(int j = pasofinal+1; j < seguidora.getDemandas().size(); j++) {

			demandasactuales.set(j, demanNuevas.get(aux)) ;
			aux++;

		}
		respuesta_nueva_abeja.setDemandas(demandasactuales);

		return respuesta_nueva_abeja;
	}

	public void ordenarListaAbejas(ArrayList<Abeja> listaAbejas) {

		Collections.sort(listaAbejas, new Comparator<Abeja>(){

			@Override
			public int compare(Abeja o1, Abeja o2) {
				return String.valueOf(o1.getContadorBloqueo()).compareToIgnoreCase(String.valueOf(o2.getContadorBloqueo()));
			}
		});
			
	}
}
