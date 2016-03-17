package principal.maquinaestado.estados.juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.maquinaestado.EstadoJuego;
import principal.sprites.HojaSprites;

public class GestorJuego implements EstadoJuego{
	private GestorMapa gestorMapa;
	HojaSprites hs = new HojaSprites("/imagenes/hojasTexturas/laberinto.png", 32, true);
	@Override
	public void actualizar() {		
	}

	@Override
	public void dibujar(Graphics g) {
		BufferedImage imagen = hs.obtenerSprite(0,0).obtenerImagen();
		g.drawImage(imagen, 100, 100, null);
	}

}
