package edu.asu.emit.qyan.alg.control;

public class Request {

	private int origen;
	private int destino;
	private int fs;
	private int id;





	public Request() {

	}

	public Request(int origen, int destino, int fs, int id) {
		this.origen = origen;
		this.destino = destino;
		this.fs = fs;
		this.id = id;
	}



	public int getOrigen() {
		return origen;
	}



	public void setOrigen(int origen) {
		this.origen = origen;
	}



	public int getDestino() {
		return destino;
	}



	public void setDestino(int destino) {
		this.destino = destino;
	}



	public int getFs() {
		return fs;
	}



	public void setFs(int fs) {
		this.fs = fs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Request [origen=" + origen + ", destino=" + destino + ", fs=" + fs + ", id=" + id + "]";
	}
	
	

}
