package edu.asu.emit.qyan.alg.control;

import java.util.List;

public class Abeja {
	
	private int Id;
	private List<Request> Demandas;
    private int solucion; 
    private double funcionObjetivo;
    private GrafoMatriz g;
    private double SU;
    private double APL;
    private double ob;
    private double pb;
    private double reclut;
    private int contadorBloqueo;
    
    
	public Abeja() {
		
	}

	public Abeja(int id, GrafoMatriz g) {
		//super();
		this.Id = id;
		this.g= g;
	}
	
	
	public Abeja(int id, List<Request> demandas, GrafoMatriz g) {
		super();
		Id = id;
		Demandas = demandas;
		this.g = g;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Request> getDemandas() {
		return Demandas;
	}

	public void setDemandas(List<Request> demandas) {
		Demandas = demandas;
	}

	public int getSolucion() {
		return solucion;
	}

	public void setSolucion(int solucion) {
		this.solucion = solucion;
	}
	public double getFuncionObjetivo() {
		return funcionObjetivo;
	}

	public void setFuncionObjetivo(double funcionObjetivo) {
		this.funcionObjetivo = funcionObjetivo;
	}

	public GrafoMatriz getG() {
		return g;
	}

	public void setG(GrafoMatriz g) {
		this.g = g;
	}

	public double getSU() {
		return SU;
	}

	public void setSU(double sU) {
		SU = sU;
	}

	public double getAPL() {
		return APL;
	}

	public void setAPL(double aPL) {
		APL = aPL;
	}
	

	public double getOb() {
		return ob;
	}

	public void setOb(double ob) {
		this.ob = ob;
	}
    
	public double getPb() {
		return pb;
	}

	public void setPb(double pb) {
		this.pb = pb;
	}
    
	public double getReclut() {
		return reclut;
	}

	public void setReclut(double reclut) {
		this.reclut = reclut;
	}

	public int getContadorBloqueo() {
		return contadorBloqueo;
	}

	public void setContadorBloqueo(int contadorBloqueo) {
		this.contadorBloqueo = contadorBloqueo;
	}

	@Override
	public String toString() {
		return "Abeja [Id=" + Id + ", Demandas=" + Demandas + ", solucion=" + solucion + ", funcionObjetivo="
				+ funcionObjetivo + ", g=" + g + ", SU=" + SU + ", APL=" + APL + ", ob=" + ob + ", pb=" + pb
				+ ", reclut=" + reclut + ", contadorBloqueo=" + contadorBloqueo + "]";
	}

    
}
