package edu.asu.emit.qyan.alg.control;

public class Enlace {

	public int distancia;
	public int tiempo;
	public int cantfs;
	public FrecuenciaSlot [] listafs;
	
	public Enlace() {
		
	}
	public Enlace(int distancia, int tiempo, int cantfs, int tam ) {
		super();
		this.distancia = distancia;
		this.tiempo = tiempo;
		this.cantfs = cantfs;
		this.listafs= new FrecuenciaSlot[tam];
		
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
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
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
}
