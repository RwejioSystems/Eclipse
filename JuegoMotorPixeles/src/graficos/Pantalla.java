package graficos;

import entes.criaturas.Jugador;
import mapa.cuadro.Cuadro;

public final class Pantalla {
	private final int ancho;
	private final int alto;
	public int[] pixeles;
	private int diferenciaX;
	private int diferenciaY;

	// //Temporal
	// private final static int LADO_SPRITE=32;
	// private final static int MASCARA_SPRITE=LADO_SPRITE-1;
	// fin Temporal

	public int obteneAncho() {
		return ancho;
	}

	public int obteneAlto() {
		return alto;
	}

	public void estableceDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];
	}

	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	// /*TEMPORAL*/
	// public void mostrar(final int compensacionX, final int compensacionY){
	// for(int y = 0; y < alto; y++){
	// int posicionY = y + compensacionY;
	// if(posicionY < 0 || posicionY >= alto){
	// continue;
	// }
	//
	// for(int x = 0; x < ancho; x++){
	// int posicionX = x + compensacionX;
	// if(posicionX < 0 || posicionX >= ancho){
	// continue;
	// }
	// //Temporal
	// pixeles[posicionX + posicionY * ancho] =Sprite.ASFALTO.pixeles[(x &
	// MASCARA_SPRITE)+ (y & MASCARA_SPRITE) * LADO_SPRITE];
	// }
	// }
	// }
	// /*FIN TEMPORAL*/
	public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador){
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;
		for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -jugador.obtenSprite().obtenLado()
						|| posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}
				if (posicionX < 0) {
					posicionX = 0;
				}
//				pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x
//						+ y * jugador.obtenSprite().obtenLado()];
				int colorPixelJugador = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
				if(colorPixelJugador != 0xff48ff00){
					pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
				}
			}
		}
	}
	
	public void mostrarCuadro(int compensacionX, int compensacionY,
			Cuadro cuadro) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;
		for (int y = 0; y < cuadro.sprite.obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < cuadro.sprite.obtenLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -cuadro.sprite.obtenLado()
						|| posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;
				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x
						+ y * cuadro.sprite.obtenLado()];
			}
		}
	}
}
