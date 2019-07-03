package edu.asu.emit.qyan.alg.control;

public class Identificador {

	private Request request;
	private int id;
	private int random;
	private int marcado;
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRandom() {
		return random;
	}
	public void setRandom(int random) {
		this.random = random;
	}
	public int getMarcado() {
		return marcado;
	}
	public void setMarcado(int marcado) {
		this.marcado = marcado;
	}
	@Override
	public String toString() {
		return "Identificador [request=" + request + ", id=" + id + ", random=" + random + ", marcado=" + marcado + "]";
	}
	
	
}
