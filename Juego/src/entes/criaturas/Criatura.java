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
			if(!enColision(desplazamientoX, 0)){
				modificarPosicionX(desplazamientoX);
			}
			if(!enColision(0, desplazamientoY)){
				modificarPosicionY(desplazamientoY);
			}
		}
	}
	
	private boolean enColision(int desplazamientoX, int desplazamientoY){
		
		boolean colision=false;
		
		int posicionX = x + desplazamientoX;
		int posicionY = y + desplazamientoY;
		
		int margenIzquierdo = -30;
		int margenDerecho = 30;
		int margenSuperior = -20;
		int margenInferior = 20;
		
		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.obtenLado();
		int bordeDerecho = (posicionX + margenDerecho + margenIzquierdo) / sprite.obtenLado();
		int bordeSuperior = (posicionY + margenInferior) / sprite.obtenLado();
		int bordeInferior = (posicionY + margenInferior + margenSuperior) / sprite.obtenLado();
		
		if(mapa.obtenerCuadroCatalogo( bordeIzquierdo + bordeSuperior * mapa.obtenerAncho()).esSolido()){
			colision=true;
		}
		if(mapa.obtenerCuadroCatalogo( bordeIzquierdo + bordeInferior * mapa.obtenerAncho()).esSolido()){
			colision=true;
		}
		if(mapa.obtenerCuadroCatalogo( bordeDerecho + bordeSuperior * mapa.obtenerAncho()).esSolido()){
			colision=true;
		}
		if(mapa.obtenerCuadroCatalogo( bordeDerecho + bordeInferior * mapa.obtenerAncho()).esSolido()){
			colision=true;
		}
		
		return colision;
	}
	
	public Sprite obtenSprite(){
		return sprite;
	}
}
