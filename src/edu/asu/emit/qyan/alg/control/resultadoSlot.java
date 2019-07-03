package edu.asu.emit.qyan.alg.control;

import java.util.Arrays;

import edu.asu.emit.qyan.alg.model.Path;

public class resultadoSlot {

	String camino;
	int[] vectorAsignacion;
	int indice;
	int contador;
	int cantidadfs;
	
	public resultadoSlot() {
		super();
	}
	public String getCamino() {
		return camino;
	}
	public void setCamino(String camino) {
		this.camino = camino;
	}
	public int[] getVectorAsignacion() {
		return vectorAsignacion;
	}
	public void setVectorAsignacion(int[] vectorAsignacion) {
		this.vectorAsignacion = vectorAsignacion;
	}
	
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	
	
	public int getCantidadfs() {
		return cantidadfs;
	}
	public void setCantidadfs(int cantidadfs) {
		this.cantidadfs = cantidadfs;
	}
	
	@Override
	public String toString() {
		return "resultadoSlot [camino=" + camino + ", vectorAsignacion=" + Arrays.toString(vectorAsignacion)
				+ ", indice=" + indice + ", contador=" + contador + ", cantidadfs=" + cantidadfs + "]";
	}
		
	
}
