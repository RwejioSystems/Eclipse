package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Jugador {
	private double posicionX;
	private double posicionY;
	
	private int direccion;
	
	private double velocidad = 1;
	
	private boolean enMovimiento= false;
	
	private HojaSprites hs;
	
	private BufferedImage imagenActual;
	
	private int animacion;
	private int estado;
	
	public double obtenerPosicionX() {
		return posicionX;
	}

	public double obtenerPosicionY() {
		return posicionY;
	}

	public void establecerPosicionX(double posicionX) {
		this.posicionX = posicionX;
	}

	public void establecerPosicionY(double posicionY) {
		this.posicionY = posicionY;
	}

	public Jugador(double posicionX, double posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		
		hs = new HojaSprites("/imagenes/hojasPersonajes/1.png", 	Constantes.LADO_SPRITE, false);
		
		imagenActual = hs.obtenerSprite(0).obtenerImagen();
		
		animacion = 0;
		estado = 0;
	}

	public void actualizar() {
		cambiarAnimacionEstado();
		enMovimiento = false;
		determinarDireccion();
		animar();
	}
	
	private void cambiarAnimacionEstado(){
		if(animacion < 30){
			animacion++;
		}else{
			animacion = 0;
		}
		
		if(animacion < 15){
			estado = 1;
		}else{
			estado = 2;
		}
	}
	
	private void determinarDireccion(){
		final int velocidadX = evaluarVelocidadX();
		final int velocidadY = evaluarVelocidadY();
		
		if(velocidadX == 0 && velocidadY == 0 ){
			return;
		}
		if( ( velocidadX != 0 && velocidadY == 0 ) || ( velocidadX == 0 && velocidadY != 0 ) ){
			mover(velocidadX, velocidadY);
		}else{
			//izquierda y arriba a la vez
			if(velocidadX == -1 && velocidadY == -1){
				if(GestorControles.teclado.izquierda.obtenerUlltimaPulsacion() > GestorControles.teclado.arriba.obtenerUlltimaPulsacion()){
					mover(velocidadX, 0);
				}else{
					mover(0, velocidadY  );
				}
			}
			//izquierda y abajo
			if(velocidadX == -1 && velocidadY == 1){
				if(GestorControles.teclado.izquierda.obtenerUlltimaPulsacion() > GestorControles.teclado.abajo.obtenerUlltimaPulsacion()){
					mover(velocidadX, 0);
				}else{
					mover(0, velocidadY  );
				}
			}
			//derecha y arriba
			if(velocidadX == 1 && velocidadY == -1){
				if(GestorControles.teclado.derecha.obtenerUlltimaPulsacion() > GestorControles.teclado.arriba.obtenerUlltimaPulsacion()){
					mover(velocidadX, 0);
				}else{
					mover(0, velocidadY  );
				}
			}
			//derecha y abajo
			if(velocidadX == 1 && velocidadY == 1){
				if(GestorControles.teclado.derecha.obtenerUlltimaPulsacion() > GestorControles.teclado.abajo.obtenerUlltimaPulsacion()){
					mover(velocidadX, 0);
				}else{
					mover(0, velocidadY  );
				}
			}
		}
	}
	
	private int evaluarVelocidadX(){
		int velocidadX = 0;
		if(GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()){
			velocidadX = -1;
		}else if(GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()){
			velocidadX = 1;
		}
		return velocidadX;
	}
	
	private int evaluarVelocidadY(){ 
		int velocidadY = 0;
		if(GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()){
			velocidadY = -1;
		}else if(GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()){
			velocidadY = 1;
		}
		return velocidadY;
	}
	
	private void mover(final int velocidadX, final int velocidadY){
		enMovimiento=true;
		
		cambiarDireccion(velocidadX, velocidadY);
		
		posicionX += velocidadX * velocidad;
		posicionY += velocidadY * velocidad;
	}
	
	private void cambiarDireccion(final int velocidadX, final int velocidadY){
		if(velocidadX==-1){
			direccion = 3; //refiere a la columna en los sprites
		}else if(velocidadX == 1){
			direccion = 2;
		}
		
		if(velocidadY == -1){
			direccion = 1;
		}else if(velocidadY == 1){
			direccion = 0;
		}
	}
	
	private void animar(){
		if(!enMovimiento){
			estado = 0;
			animacion = 0;
		}
		imagenActual = hs.obtenerSprite(direccion, estado).obtenerImagen();
	}
	
	public void dibujar(Graphics g) {
		final int centroX = Constantes.ANCHO_PANTALLA / 2 - Constantes.LADO_SPRITE / 2;
		final int centroY = Constantes.ALTO_PANTALLA / 2 - Constantes.LADO_SPRITE / 2;
		g.setColor(Color.green);
		g.drawImage(imagenActual, centroX, centroY, null);
		g.drawRect(centroX+ 8, centroY, Constantes.LADO_SPRITE/2, Constantes.LADO_SPRITE);
		
	}
}
