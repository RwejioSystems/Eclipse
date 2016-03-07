package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa{
	
	private int[] pixeles;
	
	
	public MapaCargado(String ruta) {
		super(ruta);
	}
	
	protected void cargarMapa(String ruta){
		try {
			BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));
			ancho = imagen.getWidth();
			alto = imagen.getHeight();
			
			cuadrosCatalogo = new Cuadro[ancho*alto];
			pixeles = new int[ancho * alto];
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	protected void generarMapa(){
		for(int i = 0; i < pixeles.length; i++){
			switch(pixeles[i]){
			case 0xff000000:
				cuadrosCatalogo[i] = Cuadro.ASFALTO;
				continue;
			case 0xffa9613d:
				cuadrosCatalogo[i] = Cuadro.ARENA;
				continue;
			case 0xff787777:
				cuadrosCatalogo[i] = Cuadro.CENTRO_CARRETERA;
				continue;
			case 0xffa56f54:
				cuadrosCatalogo[i] = Cuadro.BORDE_CARRETERA;
				continue;
			case 0xff955c40:
				cuadrosCatalogo[i] = Cuadro.ESQUINA_CARRETERA;
				continue;
			case 0xffa7a09d:
				cuadrosCatalogo[i] = Cuadro.PARED_PIEDRA_CARRETERA;
				continue;
			case 0xff684f46:
				cuadrosCatalogo[i] = Cuadro.PUERTA_INFERIOR;
				continue;
			case 0xffb7b7b7:
				cuadrosCatalogo[i] = Cuadro.PARED_PIEDRA;
				continue;
			case 0xff785b52:
				cuadrosCatalogo[i] = Cuadro.OXIDO;
				continue;
			case 0xfffa4913:
				cuadrosCatalogo[i] = Cuadro.PARED_PIEDRA_INFERIOR;
				continue;
			case 0xff2e2320:
				cuadrosCatalogo[i] = Cuadro.PARED_SUPERIOR;
				continue;
			case 0xff3e3e3e:
				cuadrosCatalogo[i] = Cuadro.VENTANA_PARED;
				continue;
			case 0xff42667d:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUPERIOR;
				continue;
			case 0xff503d38:
				cuadrosCatalogo[i] = Cuadro.PUERTA_INTERMEDIA_IZQUIERDA;
				continue;
			case 0xff775d54:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUPERIOR_IZQUIERDA;
				continue;
			case 0xff18262f:
				cuadrosCatalogo[i] = Cuadro.PARED_PIEDRA_CARRETERAB;
				continue;
			case 0xff2d4554:
				cuadrosCatalogo[i] = Cuadro.BORDE_CARRETERAB;	
				continue;
			case 0xff74add1:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUPERIOR_DERECHA;
				continue;
			case 0xff8acffa:
				cuadrosCatalogo[i] = Cuadro.PUERTA_INTERMEDIA_DERECHA;
				continue;
			default:
				cuadrosCatalogo[i] = Cuadro.VACIO;
				continue;
			}
		}
	}
}
