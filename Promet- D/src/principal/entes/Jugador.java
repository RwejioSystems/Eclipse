package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.mapas.Mapa;
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
	public static final int RESISTENCIA_TOTAL = 600;
	private final int ANCHO_JUGADOR = 16;
	private final int ALTO_JUGADOR = 16;
	
	private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR/2, Constantes.CENTRO_VENTANA_Y, ALTO_JUGADOR , 1);
	private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR/2, Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ALTO_JUGADOR , 1);
	private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR/2, Constantes.CENTRO_VENTANA_Y, 1 ,ALTO_JUGADOR);
	private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR/2, Constantes.CENTRO_VENTANA_Y, 1 , ALTO_JUGADOR);
	
	public int resistencia = 600; // para correr 10 segundos numero divisible entre 60 por cada segundo

	private int recuperacion =  0;
	private  final int RECUPERACION_MAXIMA = 300;
	 // numero de actualizaciones para recuperar resistencia
	private boolean recuperado = true;
	
	private Mapa mapa;
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
	
	public Rectangle ObtenerLimiteArriba(){
		return LIMITE_ARRIBA;
	}
	
	public Rectangle ObtenerLimiteAbajo(){
		return LIMITE_ABAJO;
	}
	
	public Rectangle ObtenerLimiteDerecha(){
		return LIMITE_DERECHA;
	}
	
	public Rectangle ObtenerLimiteIzquierda(){
		return LIMITE_IZQUIERDA;
	}
	
	private void ajustarPosicionX(int velocidadX){
		posicionX += velocidadX * velocidad;
	}
	
	private void ajustarPosicionY(int velocidadY){
		posicionY += velocidadY * velocidad;
	}

	public Jugador(Mapa mapa) {
		
		this.posicionX = mapa.obtenerPosisionInicial().getX();
		this.posicionY = mapa.obtenerPosisionInicial().getY();
		
		hs = new HojaSprites(Constantes.RUTA_PERSONAJE, 	Constantes.LADO_SPRITE, false);
		
		imagenActual = hs.obtenerSprite(0).obtenerImagen();
		
		animacion = 0;
		estado = 0;
		this.mapa= mapa;
	}
	
	private void GestionarVelocidadResistencia(){
		if(GestorControles.teclado.estaCorriendo() && resistencia > 0){
			velocidad=2;
			recuperado= false;
			recuperacion = 0;
		} else{
			velocidad=1;
			if(!recuperado && recuperacion < RECUPERACION_MAXIMA){
				recuperacion++;
			}
			if(recuperacion  == RECUPERACION_MAXIMA && resistencia < 600){
				resistencia++;
			}
		}
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
		if(!fueraMapa(velocidadX, velocidadY)){
			if(velocidadX == -1 && !enColisionIzquierda(velocidadX)){
				//posicionX += velocidadX * velocidad;
				ajustarPosicionX(velocidadX);
				restarResistencia();
				return;
			}
			if(velocidadX == 1 && !enColisionDerecha(velocidadX)){
				//posicionX += velocidadX * velocidad;
				ajustarPosicionX(velocidadX);
				restarResistencia();
				return;
			}
			if(velocidadY == -1 && !enColisionArriba(velocidadY)){
				//posicionY += velocidadY * velocidad;
				ajustarPosicionY(velocidadY);
				restarResistencia();
				return;
			}
			if(velocidadY == 1 && !enColisionAbajo(velocidadY)){
				//posicionY += velocidadY * velocidad;
				ajustarPosicionY(velocidadY);
				restarResistencia();
				return;
			}
		}
		
	}
	
	private void restarResistencia(){
		if(GestorControles.teclado.estaCorriendo() && resistencia> 0){
			resistencia--;
		}
	}
	
	private boolean fueraMapa(final int velocidadX, final int velocidadY){
		
		int posicionFuturaX = (int) posicionX + velocidadX * (int)velocidad;
		int posicionFuturaY = (int) posicionY + velocidadY * (int)velocidad;
		
		final Rectangle bordesMapa = mapa.obtenerBordes(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);
		
		final boolean fuera;
		
		if(LIMITE_ARRIBA.intersects(bordesMapa) || (LIMITE_ABAJO.intersects(bordesMapa)) || (LIMITE_IZQUIERDA.intersects(bordesMapa)) || (LIMITE_DERECHA.intersects(bordesMapa))){
			fuera = false;
		}else{
			fuera = true;
		}
		 return fuera;
	}
	
	private boolean enColisionArriba(int velocidadY){
		for(int r = 0 ; r < mapa.areasColision.size(); r++){
			final Rectangle Area = mapa.areasColision.get(r);
			
			int origenX = Area.x;
			int origenY = Area.y + velocidadY * (int) velocidad + 3* (int) velocidad;
			
			final Rectangle areaFutura= new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
			
			if(LIMITE_ARRIBA.intersects(areaFutura)){
				return true;
			}
		}
		return false;
	}
	
	private boolean enColisionAbajo(int velocidadY){
		for(int r = 0 ; r < mapa.areasColision.size(); r++){
			final Rectangle Area = mapa.areasColision.get(r);
			
			int origenX = Area.x;
			int origenY = Area.y + velocidadY * (int) velocidad - 3* (int) velocidad;
			
			final Rectangle areaFutura= new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
			
			if(LIMITE_ABAJO.intersects(areaFutura)){
				return true;
			}
		}
		return false;
	}
	
	private boolean enColisionIzquierda(int velocidadX){
		for(int r = 0 ; r < mapa.areasColision.size(); r++){
			final Rectangle Area = mapa.areasColision.get(r);
			
			int origenX = Area.x+ velocidadX * (int) velocidad + 3* (int) velocidad;
			int origenY = Area.y ;
			
			final Rectangle areaFutura= new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
			
			if(LIMITE_IZQUIERDA.intersects(areaFutura)){
				return true;
			}
		}
		return false;
	}
	
	private boolean enColisionDerecha(int velocidadX){
		for(int r = 0 ; r < mapa.areasColision.size(); r++){
			final Rectangle Area = mapa.areasColision.get(r);
			
			int origenX = Area.x+ velocidadX * (int) velocidad - 3* (int) velocidad;
			int origenY = Area.y;
			
			final Rectangle areaFutura= new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
			
			if(LIMITE_DERECHA.intersects(areaFutura)){
				return true;
			}
		}
		return false;
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
	
	public void actualizar() {
		GestionarVelocidadResistencia();
		cambiarAnimacionEstado();
		enMovimiento = false;
		determinarDireccion();
		animar();
	}
	
	public void dibujar(Graphics g) {
		final int centroX = Constantes.CENTRO_VENTANA_X - Constantes.LADO_SPRITE / 2;
		final int centroY = Constantes.CENTRO_VENTANA_Y - Constantes.LADO_SPRITE / 2;
		DibujoDebug.dibujarImagen(g, imagenActual, centroX, centroY);
		//DibujoDebug.dibujarString(g, "Resistencia: " + resistencia, 20, 40, Color.green);
	}
}
