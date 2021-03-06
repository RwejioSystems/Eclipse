package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	public final int pixeles[];
	
	//coleccion de hojas de sprites
	public static HojaSprites desierto = new HojaSprites("/texturas/desierto.png", 320, 320);
	public static HojaSprites jugador = new HojaSprites("/texturas/sprite_personajes.png", 320, 320);
	public static HojaSprites laberinto = new HojaSprites("/texturas/laberinto.png", 320, 320);
	// fin de la coleccion
	
	public int obenerAncho(){
		return ancho;
	}
	
	public HojaSprites(final String ruta, final int ancho, final int alto){
		this.alto = alto;
		this.ancho = ancho;
		pixeles = new int[ancho * alto];
		
		BufferedImage imagen;
		try{
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
