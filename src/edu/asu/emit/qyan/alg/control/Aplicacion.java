package edu.asu.emit.qyan.alg.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.asu.emit.qyan.alg.control.YenTopKShortestPathsAlg;
import edu.asu.emit.qyan.alg.model.Path;
import edu.asu.emit.qyan.alg.model.VariableGraph;

public class Aplicacion {
	public static ArrayList<String[]> caminos = new ArrayList<>();
	public static VariableGraph graph = new VariableGraph("data/test_16");
	public static int id = 1;

	public static void main(String[] args) throws InterruptedException, IOException {	

		for (int h = 1; h <= 100; h++) {
			crearArchivosSolicitudes(h);
		}

		crearArchivoCaminos();
		leerArchivoCaminos();

		// Matriz que representa la red igual al archivo test_16 que se va a utilar al tener los caminos.
		int[] vertices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		GrafoMatriz g = new GrafoMatriz(vertices);
		g.InicializarGrafo(g.grafo);

		g.agregarRuta(1 ,6 ,1, 3, 200);
		g.agregarRuta(1 ,7 ,1, 3, 200);
		g.agregarRuta(1 ,8 ,1, 3, 200);
		g.agregarRuta(2 ,3 ,1, 3, 200);
		g.agregarRuta(2 ,5 ,1, 3, 200);
		g.agregarRuta(2 ,6 ,1, 3, 200);
		g.agregarRuta(3 ,5 ,1, 3, 200);
		g.agregarRuta(3 ,8 ,1, 3, 200);
		g.agregarRuta(4 ,5 ,1, 3, 200);
		g.agregarRuta(4 ,6 ,1, 3, 200);
		g.agregarRuta(6 ,13 ,1, 3, 200);
		g.agregarRuta(7 ,10 ,1, 3, 200);
		g.agregarRuta(7 ,14 ,1, 3, 200);
		g.agregarRuta(8 ,9 ,1, 3, 200);
		g.agregarRuta(8 ,15 ,1, 3, 200);
		g.agregarRuta(9 ,10 ,1, 3, 200);
		g.agregarRuta(9 ,12 ,1, 3, 200);
		g.agregarRuta(9 ,15 ,1, 3, 200);
		g.agregarRuta(10 ,12 ,1, 3, 200);
		g.agregarRuta(11 ,13 ,1, 3, 200);
		g.agregarRuta(11 ,14 ,1, 3, 200);
		g.agregarRuta(13 ,14 ,1, 3, 200);



		ArrayList<Abeja> listaAbejaResult = new ArrayList();
		int contBloquePorTiempo = 0;
		int contSemiBloqueoTotal = 0;
		for(int w = 1; w <= 100; w++) {
			//			System.out.println("w:" + w);
			System.out.println("solicitud numero:" + "data/solicitudes" + w);
			FileReader input = new FileReader("data/solicitudes" + w);
			BufferedReader bufRead = new BufferedReader(input);
			String linea = bufRead.readLine();

			ArrayList<Request> solicitudes = new ArrayList();
			int contlineatxt = 0;
			int cont = 0;
			int semiBloqueo = 0;

			while (linea != null ) {

				contlineatxt++;

				if(linea.trim().equals("")) 
				{
					linea = bufRead.readLine();
					continue;
				}
				String[] str_list = linea.trim().split("\\s*,\\s*");
				Request solicitud = new Request();
				solicitud.setOrigen(Integer.parseInt(str_list[0]));
				//	System.out.println("origen:" + str_list[0]);
				solicitud.setDestino(Integer.parseInt(str_list[1]));
				//	System.out.println(str_list[1]);
				//				int calAux = Integer.parseInt(str_list[2]);
				//				double doubleAux = Integer.parseInt(str_list[2]);
				//				doubleAux = Math.ceil(calAux/10);
				//				calAux = (int) Math.ceil(doubleAux / 12);
				solicitud.setId(Integer.parseInt(str_list[4]));
				//	System.out.println("fsfsfsfsfs;" + calAux);
				solicitud.setFs(Integer.parseInt(str_list[2]));			//	System.out.println(str_list[2]);
				solicitud.setTiempo(Integer.parseInt(str_list[3]));
				solicitudes.add(solicitud);
				linea = bufRead.readLine();
			}    
			//	System.out.print("solicitudes entrantes:" + solicitudes);
			bufRead.close();
			// cantidad de pasos y numeros de demandas a tomar por cada paso.
			int divpasos = 2;
			//	System.out.println("divpasos:" + divpasos);
			int pasos = solicitudes.size()/2;
			//	System.out.println("pasos:" + pasos);
			int pasoinicio = 0;
			int pasofinal = divpasos;
			//	System.out.println("pasofinal:" + divpasos);

			Abeja resultadoFinal = new Abeja();
			for(int z = 0; z < 1; z++) {

				ArrayList<Abeja> listaNuevasAbejas = new ArrayList();

				// se ordena la solicitud de entrada de mayor a menor teniendo en cuenta el fs de cada solicitud
				if(z == 0) {
					Collections.sort(solicitudes, new Comparator<Request>(){

						@Override
						public int compare(Request o1, Request o2) {
							return String.valueOf(o2.getFs()).compareToIgnoreCase(String.valueOf(o1.getFs()));
						}
					});
				}
				// una vez ordenado la solicitud de entrada se le asigna a la primera abeja.
				// luego se realiza un reordenamiento y se le asigna a las demás abejas.
				int nroAbeja = 3;
				List<Abeja> listaAbejas = new ArrayList();
				AsignacionDemanda asig = new AsignacionDemanda(solicitudes, nroAbeja, g);
				if(w == 1) {
					listaAbejas = asig.asignacionAbeja();

					for(Abeja a : listaAbejas) {
						System.out.println(a);
					}

				}else {

					listaAbejas = asig.asignacionContinua(listaAbejaResult);

					for(Abeja a : listaAbejas) {
						System.out.println(a);
					}
				}

				for(int x = 0; x < 1; x++) {
					ArrayList<Abeja> abejatabla = new ArrayList<Abeja>();
					for (int a=0; a < listaAbejas.size(); a++) {
						GrafoMatriz graf = copiarGrafo(listaAbejas.get(a).getG());
						Abeja abe = new Abeja(listaAbejas.get(a).getId(),listaAbejas.get(a).getDemandas() , graf);
						int su = 0;

						for(int i=0; i < abe.getDemandas().size(); i++) {
							int inicio = abe.getDemandas().get(i).getOrigen();
							int fin = abe.getDemandas().get(i).getDestino();
							int fs = abe.getDemandas().get(i).getFs();
							int id = abe.getDemandas().get(i).getId();
							int tiempo = abe.getDemandas().get(i).getTiempo();

							String listaCaminos = "";

							for (int k = 0; k < caminos.size(); k++) {
								if (caminos.get(k)[0].equals(String.valueOf(inicio)) && caminos.get(k)[1].equals(String.valueOf(fin))) {
									listaCaminos = caminos.get(k)[2];
									//System.out.println("caminos: "+ listaCaminos);
									break;
								}
							}


							//							boolean ban = verificarExistenciaConexion(abe, id);
							//							System.out.println("ban: " + ban);
							//							System.out.println("ultima abeja :" + abe);
							//							System.out.println("reques: " + inicio + id + fs);
							//							if(ban) {
							boolean reasignar = abe.getG().verificar_conexion(inicio, id, fs);
							//								System.out.println("no salio de verificar");
							if (!reasignar) {
								//									System.out.println("SE VA A VOLVER A ASIGNAR");
								BuscarSlot r = new BuscarSlot(abe.getG(), listaCaminos);
								resultadoSlot res = r.concatenarCaminos(fs, 5, 0);
								if (res != null) {
									int p, h, f;
									for (p = 0; p < abe.getG().grafo.length; p++) {
										for (f = 0; f < abe.getG().grafo.length; f++) {
											for (h = 0; h < abe.getG().grafo[p][f].listafs.length; h++) {
												if (abe.getG().grafo[p][f].listafs[h].id == id) {
													abe.getG().grafo[p][f].listafs[h].id = 0;
													abe.getG().grafo[p][f].listafs[h].tiempo = 0;
													abe.getG().grafo[p][f].listafs[h].libreOcupado = 0;
												}
											}
										}
									}
									//System.out.println("Se elimino y se va a guardar de nuevo");
									Asignacion asignar = new Asignacion(abe.getG(), res);
									asignar.marcarSlotUtilizados(id, tiempo);
									//										abe.getG().restar();
								} else {
									//										System.out.println("NO SE ENCONTRO LUGAR");
									semiBloqueo++;
								}
							}else {

								BuscarSlot r = new BuscarSlot(abe.getG(), listaCaminos);
								resultadoSlot res = r.concatenarCaminos(fs,5,0);
								if (res !=null) {
									Asignacion asignar = new Asignacion(abe.getG(), res);
									asignar.marcarSlotUtilizados(id, tiempo);
									//									abe.getG().restar();


								}else {
									cont++;
									//	System.out.println("No se encontró camino posible.");

								}

							}   
						}
						//	System.out.println("abejaID:"+ abe.getId() + "apl:" + abe.getAPL() + "su:" + abe.getSU());
						cont = cont + abe.getContadorBloqueo();
						semiBloqueo = semiBloqueo + abe.getSemibloqueo();
						abe.setContadorBloqueo(cont);
						su = funcionObjetivo(abe.getG());
						abe.setSemibloqueo(semiBloqueo);

						//	double funcionObjetivo = su;
						//	funcionObjetivo = Math.round(funcionObjetivo * 100) / 100d;
						abe.setFuncionObjetivo(su);
						abejatabla.add(abe);
						cont = 0;
						semiBloqueo = 0;

						//	System.out.println("IDABEJA :" + abe.getId() + "###########funcion objetivos" + abe.getFuncionObjetivo() );
					}  

					List<Abeja> abejaslist = new ArrayList<Abeja>();
					Tablavalores tabla = new Tablavalores(abejatabla);
					abejaslist = tabla.valoresob();

					ArrayList<Abeja> abejaslistpb = tabla.valorespb(abejaslist);

					for(Abeja ab:abejaslistpb) {

						//			System.out.println("lista abejas para trabajar:"+ ab + "tamaño:"+ ab.getDemandas().size());
					}

					//	System.out.println();

					int numeroAleatorio = (int) (Math.random() * 9) + 1;
					//	System.out.println("numero aleatorio:" + numeroAleatorio);
					float numeroAleatorio2 = (float)numeroAleatorio / 10;
					numeroAleatorio2 = 0.5f;
					//	System.out.println("numero aleatorio:" + numeroAleatorio2);

					Reclutamiento reclutas = new Reclutamiento(abejaslistpb, numeroAleatorio2, pasofinal);
					listaNuevasAbejas = reclutas.reclutarAbejas();


					for(Abeja ab:listaNuevasAbejas) {

						//			System.out.println("abejas luego del reclutamiento " +ab);
					}

					pasoinicio = pasofinal;
					pasofinal = pasofinal + divpasos;
				}
				double auxiliar =1000;
				Abeja nuevaPoblacion = new Abeja();
				ArrayList<Request> aux = new ArrayList();
				for(int k = 0; k < listaNuevasAbejas.size(); k++) {
					if(listaNuevasAbejas.get(k).getFuncionObjetivo() < auxiliar) {
						nuevaPoblacion = listaNuevasAbejas.get(k);
						auxiliar = listaNuevasAbejas.get(k).getFuncionObjetivo();
					}
				}
				for (int l = 0; l < nuevaPoblacion.getDemandas().size(); l++) {
					aux.add(nuevaPoblacion.getDemandas().get(l));
				}

				for(int a = 0; a < listaNuevasAbejas.size(); a++) {
					System.out.println("abejas: " + listaNuevasAbejas.get(a));

				}

				for(int a = 0; a < listaNuevasAbejas.size(); a++) {
					listaNuevasAbejas.get(a).getG().restar();

				}

				solicitudes = aux;
				pasoinicio = 0;
				pasofinal = divpasos;
				resultadoFinal = nuevaPoblacion;
				listaAbejaResult = listaNuevasAbejas;

				//	System.out.println("primera iteracion:" + z);
			}	

			float entropia = contadorDeEntropia(resultadoFinal)/22;
			contBloquePorTiempo += resultadoFinal.getContadorBloqueo();
			contSemiBloqueoTotal += resultadoFinal.getSemibloqueo();
			System.out.println("############# t" + w);
			//			System.out.println("Cantidad de conexiones entrantes :" + contlineatxt);

			System.out.println("La mejor opción la tiene la abeja: " + resultadoFinal.getId() + " indice: " + resultadoFinal.getFuncionObjetivo()/200 + " contadorBloqueoPortiempo: " + resultadoFinal.getContadorBloqueo() + " bloqueoTotal: " + contBloquePorTiempo + " semiBloqueo: " + contSemiBloqueoTotal + " ContadorEntropia: " + entropia);     
		}
	}

	private static float contadorDeEntropia(Abeja abe) {

		int m,n,b = 0;
		float contadorEntropia = 0;
		int empezoEn = 0;

		for (m = 0; m < abe.getG().grafo.length; m++) {
			for (n = 0; n < abe.getG().grafo.length; n++) {
				if (abe.getG().grafo[m][n].distancia != 0) {
					empezoEn = abe.getG().grafo[m][n].listafs[0].libreOcupado;
					for (b = 0; b < abe.getG().grafo[m][n].listafs.length; b++) {
						if (empezoEn != abe.getG().grafo[m][n].listafs[b].libreOcupado) {
							empezoEn = abe.getG().grafo[m][n].listafs[b].libreOcupado;
							contadorEntropia++;
						}
					}
				}
			}
		}

		return contadorEntropia;
	}

	private static boolean verificarExistenciaConexion(Abeja abe, int id) {
		boolean ban = false;
		for(int i=0; i < abe.getG().getGrafo().length; i++)	{
			for(int j=0; j < abe.getG().getGrafo().length; j++) {
				if(abe.getG().getGrafo()[i][j].ids.contains(id)) {
					ban = true;

				}
			}
		}

		return ban;		

	}

	private static void leerArchivoCaminos() throws IOException {
		FileReader input = new FileReader("data/Kcaminos");
		BufferedReader bufRead = new BufferedReader(input);
		String linea = bufRead.readLine();

		while (linea != null) {
			String[] variables = linea.split("-");
			variables[2] = variables[2].replace(", [", ";[");
			variables[2] = variables[2].replace("[", "");
			variables[2] = variables[2].replace("]", "");
			variables[2] = variables[2].replace(", ", ",");
			caminos.add(variables);
			linea = bufRead.readLine();
		}
	}

	private static void crearArchivoCaminos() throws IOException {
		YenTopKShortestPathsAlg yenAlg = new YenTopKShortestPathsAlg(graph);
		PrintWriter writer = new PrintWriter("data/Kcaminos", "UTF-8");

		// en este for hay que poner la cantidad de vertices que tenemos
		for (int i = 0; i <= 15; i++) {
			for (int k = 0; k <= 15; k++) {
				if (i != k) {
					List<Path> shortest_paths_list = yenAlg.get_shortest_paths(graph.get_vertex(i), graph.get_vertex(k), 5);
					List<Path> shortest_paths_list2 = yenAlg.get_shortest_paths(graph.get_vertex(k), graph.get_vertex(i), 5);
					writer.println(i + "-" + k + "-" + shortest_paths_list.toString());
					writer.println(k + "-" + i + "-" + shortest_paths_list2.toString());

				}
			}
		}
		writer.close();
	}

	public static GrafoMatriz copiarGrafo(GrafoMatriz grafo) {
		GrafoMatriz graf = new GrafoMatriz(grafo.getCadenaVertices());
		graf.InicializarGrafo(graf.grafo);
		Enlace[][] enlace = graf.getGrafo();
		graf.setCadenaVertices(grafo.getCadenaVertices());
		graf.setNodos(grafo.getNodos());
		for(int i = 0; i < grafo.getGrafo().length; i++) {
			for(int j = 0; j < grafo.getGrafo().length; j++) {
				enlace[i][j].distancia = grafo.getGrafo()[i][j].distancia;
				enlace[i][j].cantfs = grafo.getGrafo()[i][j].cantfs;
				for(int p = 0; p < grafo.getGrafo()[i][j].ids.size(); p++) {		
					enlace[i][j].ids.add(grafo.getGrafo()[i][j].ids.get(p));
					enlace[i][j].enlace.add(grafo.getGrafo()[i][j].enlace.get(p));
				}
				for(int k = 0; k < grafo.getGrafo()[i][j].listafs.length; k++){
					enlace[i][j].listafs[k].libreOcupado = grafo.getGrafo()[i][j].listafs[k].libreOcupado;
					enlace[i][j].listafs[k].id = grafo.getGrafo()[i][j].listafs[k].id;
					enlace[i][j].listafs[k].tiempo = grafo.getGrafo()[i][j].listafs[k].tiempo;
				}

			}

		}
		graf.setGrafo(enlace);
		return graf;
	}

	public static int funcionObjetivo(GrafoMatriz grafo) {
		int auxiliar = 0;
		for(int i = 0; i < grafo.getGrafo().length; i++) {
			for(int j = 0; j < grafo.getGrafo().length; j++) {
				for(int k = 0; k < grafo.getGrafo()[i][j].listafs.length; k++){
					if (grafo.getGrafo()[i][j].listafs[k].libreOcupado == 1 && k >= auxiliar) {

						auxiliar = k;
					}
				}

			}

		}


		return auxiliar;
	}

	public static void crearArchivosSolicitudes(int l) throws IOException {
		PrintWriter writer = null;
		int contador = 0;
		try {
			writer = new PrintWriter("data/solicitudes" + l, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= 1; i++) {
			int origen = (int) (Math.random() * (15) + 1);
			int destino = (int) (Math.random() * (15) + 1);
			int fs = 1 + (int) (Math.random() * (10 - 1) + 1);
			int tiempo = 1 + (int) (Math.random() * (5 - 1) + 1);
			if (origen == destino) {
				while (origen == destino) {
					destino = (int) (Math.random() * (15) + 1);
				}
			}

			writer.println(origen + "," + destino + "," + fs + "," + tiempo + "," + id);
			id++;
		}

		if (l > 1) {

			FileReader input = new FileReader("data/solicitudes" + (l - 1));
			BufferedReader bufRead = new BufferedReader(input);

			String linea = bufRead.readLine();

			while (linea != null && contador < 10) {

				if (linea.trim().equals("")) {
					linea = bufRead.readLine();
					continue;
				}
				String[] str_list = linea.trim().split("\\s*,\\s*");
				int origen = Integer.parseInt(str_list[0]);
				int destino = Integer.parseInt(str_list[1]);
				int fsActual = Integer.parseInt(str_list[2]);
				int tiempo = Integer.parseInt(str_list[3]);
				int id1 = Integer.parseInt(str_list[4]);
				int fsNuevo = 1 + (int) (Math.random() * (10 - 1) + 1);

				if (fsActual == fsNuevo) {
					while (fsActual == fsNuevo) {
						fsNuevo = 1 + (int) (Math.random() * (10 - 1) + 1);
					}
				}

				writer.println(origen + "," + destino + "," + fsNuevo + "," + tiempo + "," + id1);
				contador++;
				linea = bufRead.readLine();

			}

			writer.close();
		} else {
			writer.close();
		}
	}

}