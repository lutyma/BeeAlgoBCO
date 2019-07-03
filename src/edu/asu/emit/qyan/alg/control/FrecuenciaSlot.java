package edu.asu.emit.qyan.alg.control;

public class FrecuenciaSlot {

	public int libreOcupado;
	public int id;
	public int tiempo;
	
	public FrecuenciaSlot(int libreOcupado, int id, int tiempo) {
		super();
		this.libreOcupado = libreOcupado;
		this.id = id;
		this.tiempo = tiempo;
	}

	public int getLibreOcupado() {
		return libreOcupado;
	}

	public void setLibreOcupado(int libreOcupado) {
		this.libreOcupado = libreOcupado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	} 
	
	
}
