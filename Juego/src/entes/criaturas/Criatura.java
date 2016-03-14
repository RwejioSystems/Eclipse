package entes.criaturas;

import entes.Ente;
import graficos.Sprite;

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';//e o s
	protected boolean entMovimiento=false;
	public void actualizar(){
		
	}
	
	public void mostrar(){
		
	}
	
	public void mover(int desplazamientoX, int desplazamientoY){
		if(desplazamientoX > 0 ){
			direccion = 'e';//este
		}
		if(desplazamientoX < 0){
			direccion = 'o';//oeste
		}
		if(desplazamientoY > 0){
			direccion = 's';
		}
		if(desplazamientoY < 0){
			direccion = 'n';
		}
		
		if(!estaEliminado()){
			modificarPosicionX(desplazamientoX);
			modificarPosicionY(desplazamientoY);
		}
	}
	
	private boolean enColision(){
		 return false;
	}
	
	public Sprite obtenSprite(){
		return sprite;
	}
}
