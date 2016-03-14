package entes;

import mapa.Mapa;

public abstract class Ente {
	protected int x;
	protected int y;
	
	private boolean eliminado = false;
	
	protected Mapa mapa;
	
	public void modificarPosicionX(int desplazamientoX){
		x+=desplazamientoX;
	}
	
	public void modificarPosicionY(int desplazamientoY){
		y += desplazamientoY;
	}
	
	public int obtenerPosicionX(){
		return x;
	}
	
	public int ObtenerPosicionY(){
		return y;
	}
	
	public void eliminar(){
		eliminado=true;
	}
	
	public boolean estaEliminado(){
		return eliminado;
	}
	
	public void actualizar(){
		
	}
	
	public void mostrar(){
		
	}
}
