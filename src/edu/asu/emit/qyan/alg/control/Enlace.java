package edu.asu.emit.qyan.alg.control;

public class Enlace {

	public int distancia;
	public int cantfs;
	public FrecuenciaSlot [] listafs;
	
	public Enlace() {
		
	}
	public Enlace(int distancia, int cantfs, int tam ) {
		super();
		this.distancia = distancia;
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
	
	
	
	
}
