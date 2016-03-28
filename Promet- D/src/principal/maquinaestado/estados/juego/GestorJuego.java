package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;
import principal.interfaz_usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;
import principal.sprites.HojaSprites;

public class GestorJuego implements EstadoJuego{
	Mapa mapa;
	//Jugador jugador = new Jugador(430, 600, mapa);
	Jugador jugador;
	BufferedImage logo; 
	MenuInferior menuInferior;
	
	public GestorJuego(){
		iniciarMapa(Constantes.RUTA_MAPA);
		iniciarJugador();
		menuInferior = new MenuInferior(jugador);
		logo = new CargadorRecursos().cargarImagenCompatibleTranslucida(Constantes.RUTA_LOGOTIPO);
	}
	
	private void recargarJuego(){
		final String ruta = "/mapas/"+ mapa.obtenerSiguienteMapa();
		iniciarMapa(ruta);
		iniciarJugador();
	}
	
	private void iniciarMapa(String ruta){
		mapa = new Mapa(ruta);
	}
	
	private void iniciarJugador(){
		jugador = new Jugador(mapa);
	}
	
	@Override
	public void actualizar() {
		if(jugador.ObtenerLimiteArriba().intersects(mapa.obtenerZonaSalida()) ){
			recargarJuego();
		}
		jugador.actualizar();
		mapa.actualizar((int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
		
	}

	@Override
	public void dibujar(Graphics g) {
		mapa.dibujar(g, (int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
		jugador.dibujar(g);
		menuInferior.dibujar(g, jugador);
		
		DibujoDebug.dibujarImagen(g, logo, Constantes.ANCHO_JUEGO - logo.getWidth() - 5, 0+5);
		DibujoDebug.dibujarRectanguloRelleno(g, mapa.obtenerZonaSalida().x, mapa.obtenerZonaSalida().y, Constantes.LADO_SPRITE,  Constantes.LADO_SPRITE);
		
		DatosDebug.enviarDato("X:"+ jugador.obtenerPosicionX());
		DatosDebug.enviarDato("Y:"+ jugador.obtenerPosicionY());
		DatosDebug.enviarDato("Siguiente mapa:"+ mapa.obtenerSiguienteMapa());
		DatosDebug.enviarDato("Salida: X "+ mapa.obtenerPuntoSalida().x +" Y "+mapa.obtenerPuntoSalida().y);
		//HUD.dibujarBarraResistencia(g, jugador.resistencia);
	}
}
