package edu.asu.emit.qyan.alg.control;

import java.util.ArrayList;

public class Enlace {

	public int distancia;
	public int tiempo;
	public int cantfs;
	public FrecuenciaSlot [] listafs;
	public ArrayList<String> enlace;
	public ArrayList<Integer> ids;
	public Enlace(int distancia, int tiempo, int cantfs, int tam ) {
		super();
		this.distancia = distancia;
		this.tiempo = tiempo;
		this.cantfs = cantfs;
		this.listafs= new FrecuenciaSlot[tam];
		this.enlace = new ArrayList<String>();
		this.ids = new ArrayList<Integer>();
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public int getCantfs() {
		return cantfs;
	}
	public void setCantfs(int cantfs) {
		this.cantfs = cantfs;
	}
	public FrecuenciaSlot[] getListafs() {
		return listafs;
	}
	public void setListafs(FrecuenciaSlot[] listafs) {
		this.listafs = listafs;
	}
	
	
	
	
}
