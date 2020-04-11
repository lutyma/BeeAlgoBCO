package edu.asu.emit.qyan.alg.control;

import java.util.Arrays;

public class GrafoMatriz {

	int nodos;
	Enlace[][] grafo; 
	int[] cadenaVertices;
	GrafoMatriz(){


	}

	GrafoMatriz(int[] serieNodos) {
		cadenaVertices = new int[serieNodos.length];
		for (int i = 0; i<serieNodos.length;i++) {
			cadenaVertices[i]=serieNodos[i];
		}
		nodos = serieNodos.length;
		//	System.out.println(nodos.length);
		grafo = new Enlace[serieNodos.length][serieNodos.length];
	}


	public GrafoMatriz(int nodos, Enlace[][] grafo, int[] cadenaVertices) {
		super();
		this.nodos = nodos;
		this.grafo = grafo;
		this.cadenaVertices = cadenaVertices;
	}

	public void InicializarGrafo (Enlace[][] grafo) {

		for (int x=0; x < grafo.length; x++) {
			for (int y=0; y < grafo[x].length; y++) {
				grafo[x][y] = new Enlace(0,0,0,200);

				for (int k=0; k < grafo[x][y].listafs.length; k++) {

					grafo[x][y].listafs[k] = new FrecuenciaSlot(0, 0, 0);
				}

			}
		}	
	}

	public void agregarRuta(int origen, int destino, int distancia, int tiempo, int cantfs) {
		//	System.out.println(origen);
		//	System.out.println(destino);
		int n1 = posicionNodo(origen);
		//	System.out.print(n1);

		int n2 = posicionNodo(destino);
		grafo[n1][n2].distancia = distancia;
		grafo[n1][n2].tiempo = tiempo;
		grafo[n1][n2].cantfs = cantfs;

		grafo[n2][n1].distancia = distancia;
		grafo[n2][n1].tiempo = tiempo;
		grafo[n2][n1].cantfs = cantfs;

	}

	public void restar() {
		int i, j, k;

		for (i = 0; i < this.grafo.length; i++) {

			for (j = 0; j < this.grafo.length; j++) {

				for (k = 0; k < this.grafo[i][j].listafs.length; k++) {

					if (this.grafo[i][j].listafs[k].id != 0) {
						if (this.grafo[i][j].listafs[k].tiempo - 1 == 0) {
							this.grafo[i][j].listafs[k].id = 0;
							this.grafo[i][j].listafs[k].tiempo = 0;
							this.grafo[i][j].listafs[k].libreOcupado = 0;
						} else if (this.grafo[i][j].listafs[k].tiempo - 1 > 0) {
							this.grafo[i][j].listafs[k].tiempo--;
						}
					}
				}

			}
		}
	}

	public boolean verificar_conexion(int origen, Integer id, int cantfs) {

		int i;
		int long_grafo = this.grafo.length;
		FrecuenciaSlot[] concatenado;
		concatenado = new FrecuenciaSlot[this.grafo[origen][origen].listafs.length];
		String[] conexiones = null;

		//busca la ubicacion en la matriz de esa conexion
		for (i = 0; i < long_grafo; i++){
			if (this.grafo[origen][i].ids.contains(id)){
				//i=long_grafo; para terminar el for

				// se busca el camino que recorre la conexion
				for (int j = 0; j < this.grafo[origen][i].enlace.size(); j++){
					String[] variables = this.grafo[origen][i].enlace.get(j).split(",");
					String id1 = variables[0];
					if (id.toString().equals(id1)){
						//						j=this.grafo[origen][i].enlace.size(); PARA TERMINAR EL FOR
						conexiones = variables;

						// se van a concatenar los vectores del camino de la conexion
						for (int k = 1; k < conexiones.length-1; k++) {
							int origen1 = Integer.parseInt(conexiones[k]);
							int destino1 = Integer.parseInt(conexiones[k+1]);
							for (int p = 0; p < concatenado.length; p++) {
								concatenado[p] = this.grafo[origen1][destino1].listafs[p];
							}
						}
					}
				}
			}

		}
		// IF PARA SALIR SI NO SE CUMPLIO O NO SE ENCONTRO
		if(i == long_grafo && concatenado[0] == null) {
			return false;
		}
		//concatenado[] es el vector para ver si la conexion nueva entra
		boolean bandera = true;
		int inicio = 0;
		int longitud = 0;
		//se busca el lugar de la conexion y se encuentran los extremos
		for (int p = 0; p < concatenado.length; p++){
			if (concatenado[p].id == id){
				while(bandera){
					inicio = p;
					bandera = false;
				}
				longitud++;
			}
		}

		/**+
		 * DISMINUIR LA CONEXION SI ES EL CASO
		 */
		int m = 1;
		boolean lado = true;
		if (longitud > cantfs) {
			for (int k = 1; k < conexiones.length - 1; k++) {
				int origen1 = Integer.parseInt(conexiones[k]);
				int destino1 = Integer.parseInt(conexiones[k + 1]);
				m = 1;
				int longitudAux = longitud;
				int inicioAux = inicio;
				while (longitudAux != cantfs) {
					if (lado) {
						lado = false;
						this.grafo[origen1][destino1].listafs[inicioAux].libreOcupado = 0;
						this.grafo[origen1][destino1].listafs[inicioAux].id = 0;
						this.grafo[origen1][destino1].listafs[inicioAux].tiempo = 0;

						this.grafo[destino1][origen1].listafs[inicioAux].libreOcupado = 0;
						this.grafo[destino1][origen1].listafs[inicioAux].id = 0;
						this.grafo[destino1][origen1].listafs[inicioAux].tiempo = 0;
						inicioAux++;
						longitudAux--;
					} else {
						lado = true;
						this.grafo[origen1][destino1].listafs[longitudAux + inicioAux - 1].libreOcupado = 0;
						this.grafo[origen1][destino1].listafs[longitudAux + inicioAux - 1].id = 0;
						this.grafo[origen1][destino1].listafs[longitudAux + inicioAux - 1].tiempo = 0;

						this.grafo[destino1][origen1].listafs[longitudAux + inicioAux - 1].libreOcupado = 0;
						this.grafo[destino1][origen1].listafs[longitudAux + inicioAux - 1].id = 0;
						this.grafo[destino1][origen1].listafs[longitudAux + inicioAux - 1].tiempo = 0;
						longitudAux--;
					}
					if (lado) {
						m++;
					}
				}

			}
			return true;
		}


		int contador_izq = 0;
		int contador_der = 0;
		int verificador_izq = 0;
		int verificador_der = 0;

		bandera = true;
		while (bandera){
			/**
			 * en la variable contador vamos a saber cuantos espacios libres hay para la conexion
			 * contador_izq = la cantidad de espacios a la izquierda
			 * contador_der = la cantidad de espacios a la derecha
			 */

			for (int p = 1; p< concatenado.length; p++) {

				if ((inicio-p) >= 0 && concatenado[inicio - p].libreOcupado == 0 && verificador_izq == 0) {
					contador_izq++;
				} else {
					verificador_izq = 1;
				}

				if (p == 1) {
					if (inicio + longitud < concatenado.length) {
						if (concatenado[(longitud + inicio)].libreOcupado == 0 && verificador_der == 0) {
							contador_der++;
						} else {
							verificador_der = 1;
						}
					} else {
						verificador_der = 1;
					}
				} else {
					if (inicio + longitud < concatenado.length) {
						if ((longitud + inicio + (p - 1)) < concatenado.length && concatenado[(longitud + inicio) + (p - 1)].libreOcupado == 0 && verificador_der == 0) {
							contador_der++;
						} else {
							verificador_der = 1;
						}
					} else {
						verificador_der = 1;
					}
				}
				if(verificador_der == 1 && verificador_izq == 1) {
					bandera = false;
					break;
				}
			}
		}

		/**
		 * vamos a calcular cuantos slots de cada lado se van a asignar
		 */
		int long_conexion = longitud;
		int espacios_necesarios = cantfs - longitud;
		int derecha = 0;
		int izquierda = 0;

		/**
		 * en las variables izquierda y derecha se van a guardar cuantos lugares se van a usar para cada lado
		 */
		while (espacios_necesarios != 0){
			if(espacios_necesarios > 0 && contador_izq > 0) {
				izquierda++;
				contador_izq--;
				espacios_necesarios--;
			}
			if(espacios_necesarios > 0 && contador_der > 0) {
				derecha++;
				contador_der--;
				espacios_necesarios--;
			}
			if (contador_der == 0 && contador_izq == 0 && espacios_necesarios > 0) {
				//				System.out.println("la conexion no entra");
				return false;
			}
		}



		//vamos a ver si entra la conexion nueva y vamos a asignar
		int espacios = long_conexion + izquierda + derecha;
		int h = 1;
		int g = 1;
		boolean cab = true;
		boolean banderaIzq = false;
		boolean banderaDer = false;
		/**
		 * PARA AGRANDAR LA SOLICITUD
		 */
		if (cantfs == espacios) {
			/**
			 * se van a asignar los nuevos lugares al camino
			 */
			for (int k = 1; k < conexiones.length-1; k++) {
				int izqFalso = izquierda;
				int derFalso = derecha;
				h = 1;
				g = 1;
				banderaIzq = false;
				banderaDer = false;
				int origen1 = Integer.parseInt(conexiones[k]);
				int destino1 = Integer.parseInt(conexiones[k+1]);
				cab = true;

				while(izqFalso+derFalso > 0){
					//					System.out.println("izq " + izqFalso + "der " + derFalso);
					if (izqFalso > 0) {
						cab = false;
						banderaIzq = true;
						this.grafo[origen1][destino1].listafs[inicio - h].libreOcupado = 1;
						this.grafo[origen1][destino1].listafs[inicio - h].id = this.grafo[origen1][destino1].listafs[inicio].id;
						this.grafo[origen1][destino1].listafs[inicio - h].tiempo = this.grafo[origen1][destino1].listafs[inicio].tiempo;

						this.grafo[destino1][origen1].listafs[inicio - h].libreOcupado = 1;
						this.grafo[destino1][origen1].listafs[inicio - h].id = this.grafo[origen1][destino1].listafs[inicio].id;
						this.grafo[destino1][origen1].listafs[inicio - h].tiempo = this.grafo[origen1][destino1].listafs[inicio].tiempo;
						izqFalso--;
					} else if(derFalso > 0) {
						//						System.out.println(longitud + " " + inicio + " " + h);
						if (!banderaDer) {
							banderaDer = true;
							//							System.out.println("entro en h==1");
							cab = true;
							this.grafo[origen1][destino1].listafs[longitud + inicio].libreOcupado = 1;
							this.grafo[origen1][destino1].listafs[longitud + inicio].id = this.grafo[origen1][destino1].listafs[inicio].id;
							this.grafo[origen1][destino1].listafs[longitud + inicio].tiempo = this.grafo[origen1][destino1].listafs[inicio].tiempo;

							this.grafo[destino1][origen1].listafs[longitud + inicio].libreOcupado = 1;
							this.grafo[destino1][origen1].listafs[longitud + inicio].id = this.grafo[origen1][destino1].listafs[inicio].id;
							this.grafo[destino1][origen1].listafs[longitud + inicio].tiempo = this.grafo[origen1][destino1].listafs[inicio].tiempo;
							derFalso--;
						} else {
							cab = true;
							//							System.out.println("entro en else");
							//							System.out.println(h);
							this.grafo[origen1][destino1].listafs[(longitud + inicio) + (g-1)].libreOcupado = 1;
							this.grafo[origen1][destino1].listafs[(longitud + inicio) + (g-1)].id = this.grafo[origen1][destino1].listafs[inicio].id;
							this.grafo[origen1][destino1].listafs[(longitud + inicio) + (g-1)].tiempo = this.grafo[origen1][destino1].listafs[inicio].tiempo;

							this.grafo[destino1][origen1].listafs[(longitud + inicio) + (g-1)].libreOcupado = 1;
							this.grafo[destino1][origen1].listafs[(longitud + inicio) + (g-1)].id = this.grafo[origen1][destino1].listafs[inicio].id;
							this.grafo[destino1][origen1].listafs[(longitud + inicio) + (g-1)].tiempo = this.grafo[origen1][destino1].listafs[inicio].tiempo;
							derFalso--;
						}
					}
					//					if (cab || izqFalso == 0) {
					//						h++;
					//					}
					if(banderaIzq) h++;
					if (banderaDer) g++;
				}
			}
			//}
		}
		else{
			//			System.out.println("la conexion no entra");
			return false;
		}
		return true;

	}

	public int posicionNodo(int nodo) {
		for(int i=0; i<cadenaVertices.length; i++) {
			if(cadenaVertices[i]==nodo) return i;
		}
		return -1;
	}

	public int getNodos() {
		return nodos;
	}

	public void setNodos(int nodos) {
		this.nodos = nodos;
	}

	public Enlace[][] getGrafo() {
		return grafo;
	}

	public void setGrafo(Enlace[][] grafo) {
		this.grafo = grafo;
	}

	public int[] getCadenaVertices() {
		return cadenaVertices;
	}

	public void setCadenaVertices(int[] cadenaVertices) {
		this.cadenaVertices = cadenaVertices;
	}

	@Override
	public String toString() {
		return "GrafoMatriz [nodos=" + nodos + ", grafo=" + Arrays.toString(grafo) + ", cadenaVertices="
				+ Arrays.toString(cadenaVertices) + "]";
	}


}
