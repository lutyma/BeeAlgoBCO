package edu.asu.emit.qyan.alg.control;

import edu.asu.emit.qyan.alg.model.abstracts.BaseVertex;

public class Asignacion {

    GrafoMatriz g;
    resultadoSlot resultado;
    int p;
    int m;


    Asignacion(GrafoMatriz g, resultadoSlot resultado) {
        this.g = g;
        this.resultado = resultado;
    }



    public void marcarSlotUtilizados(int id) {
        
        int mitad = lugarInicialAsignacion(resultado);
        
     //  System.out.println("######" + resultado.camino.length());

        for (int i = 0; i < resultado.camino.length() - 1; i++) {

//            BaseVertex id1 = resultado.camino.get_vertex_list().get(i);
//            BaseVertex id2 = resultado.camino.get_vertex_list().get(i + 1);

            int k = (int) resultado.camino.charAt(i) - 48;
            int l = (int) resultado.camino.charAt(i+1) - 48;

            int n1 = g.posicionNodo(k);
            int n2 = g.posicionNodo(l);

            p = n1;
            m = n2;
         //   System.out.println("######" + p + m);
            int mitadderecha = mitad;
            int mitadizquierda = mitad;
            
            for (int x = 0; x < resultado.cantidadfs; x++) {

                if (x == 0) {
                    g.grafo[n1][n2].listafs[mitad].libreOcupado = 1;
                    g.grafo[n2][n1].listafs[mitad].libreOcupado = 1;
                    g.grafo[n1][n2].listafs[mitad].id = id;
                    g.grafo[n2][n1].listafs[mitad].id = id;

                } else if (x != 0 && (x % 2) == 0) {
                    mitadizquierda--;
                    g.grafo[n1][n2].listafs[mitadizquierda].libreOcupado = 1;
                    g.grafo[n2][n1].listafs[mitadizquierda].libreOcupado = 1;
                    g.grafo[n1][n2].listafs[mitadizquierda].id = id;
                    g.grafo[n2][n1].listafs[mitadizquierda].id = id;
                } else if (x != 0 && (x % 2) != 0) {
                    mitadderecha++;
                    g.grafo[n1][n2].listafs[mitadderecha].libreOcupado = 1;
                    g.grafo[n2][n1].listafs[mitadderecha].libreOcupado = 1;
                    g.grafo[n1][n2].listafs[mitadderecha].id = id;
                    g.grafo[n2][n1].listafs[mitadderecha].id = id;

                }
            }
       //     System.out.println("######");
            for (int x = 0; x < g.grafo[0][0].listafs.length; x++) {

           // 	 System.out.println("###### hola ###########");
               System.out.println("grafo de la abeja en cuestion :" + g.grafo[p][m].listafs[x].libreOcupado);
            }
            System.out.println("######");
        }


    }

    public int lugarInicialAsignacion(resultadoSlot resultado) {

        int indiceInicio = (resultado.indice - resultado.contador) + 1;
        int mitad = (indiceInicio + resultado.indice) / 2;
        return mitad;
    }


}