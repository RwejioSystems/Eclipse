package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;
import principal.sprites.HojaSprites;

public class GestorJuego implements EstadoJuego{
	Mapa mapa = new Mapa("/texto/Laberinto.pd");
	Jugador jugador = new Jugador(126, 443);
	BufferedImage logo = new CargadorRecursos().cargarImagenCompatibleTranslucida("/imagenes/iconos/logotipo.png"); 
	@Override
	public void actualizar() {
		jugador.actualizar();
	}

	@Override
	public void dibujar(Graphics g) {
		mapa.dibujar(g, (int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
		jugador.dibujar(g);
		g.drawImage(logo, Constantes.ANCHO_PANTALLA - logo.getWidth() - 5, Constantes.ALTO_PANTALLA - logo.getHeight() - 5, null);
		g.drawString("Posicion X:"+ jugador.obtenerPosicionX(), 20, 20);
		g.drawString("Posicion Y:"+ jugador.obtenerPosicionY(), 20, 30);
	}

}
