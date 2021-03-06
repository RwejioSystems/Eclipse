 package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstados;

public class GestorPrincipal {
	
	private boolean enFuncionamiento=false;
	private String titulo;
	private int ancho;
	private int alto;
	
	private SuperficieDibujo sd;
	private Ventana ventana;
	private GestorEstados ge;

	private static int fps=0;
	private static int aps=0;
	
	private GestorPrincipal(final String titulo, final int ancho, final int alto){
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public static void main(String [] args){
		GestorPrincipal gp = new GestorPrincipal("Promet-D", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
		gp.iniciarJuego();
		gp.inicialBuclePrincipal();
	}

	private void iniciarJuego() {
		enFuncionamiento = true;
		inicializar();
	}
	
	private void inicializar() {
		sd = new SuperficieDibujo(ancho, alto);
		ventana = new Ventana(titulo, sd);
		ge = new GestorEstados(sd);
	}

	private void inicialBuclePrincipal() {	
		int actualizacionesAcumuladas=0;
		int framesAcumulados=0;
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO=60;
		final double NS_POR_ACTUALIZACION= NS_POR_SEGUNDO / APS_OBJETIVO;
		
		long referenciaActualizacion=System.nanoTime();
		long referenciaContador=System.nanoTime();
		
		double tiempoTranscurrido;
		double delta=0;//cantidad de tiempo que ha transcurrido desde la actualizacion
		
		while(enFuncionamiento){
			final long inicioBucle= System.nanoTime();
			
			tiempoTranscurrido=inicioBucle - referenciaActualizacion;
			referenciaActualizacion=inicioBucle;
			delta += tiempoTranscurrido/NS_POR_ACTUALIZACION;
			while (delta >=1){
				actualizar();
				delta--;
				actualizacionesAcumuladas++;
			}
			dibujar();
			framesAcumulados++;
			
			if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
				fps=framesAcumulados;
				aps = actualizacionesAcumuladas;
				actualizacionesAcumuladas=0;
				framesAcumulados=0;
				referenciaContador=System.nanoTime();
			}
		}
	}
	
	public static int obtenerFPS(){
		return fps;
	}
	
	public static int obtenerAPS(){
		return aps;
	}
	
	private void actualizar(){
		if(GestorControles.teclado.inventarioActivo){
			ge.cambiarEstadoActual(1);
		}else{
			ge.cambiarEstadoActual(0);
		}
		ge.actualizar();
		sd.actualizar();
	}
	private void dibujar(){
		sd.dibujar(ge);
	}
	
}
