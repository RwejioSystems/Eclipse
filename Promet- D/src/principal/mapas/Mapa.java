package principal.mapas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

	private final int ancho;
	private final int alto;
	private String[] PartesMapa;
	private final Sprite[] paleta;
	private final boolean[] colisiones;
	private final int[] sprites;
	
	public Mapa(final String ruta) {
		String Contenido = CargadorRecursos.leerArchivoTexto(ruta);

		PartesMapa = Contenido.split("\\*");
		// ancho del mapa
		ancho = Integer.parseInt(PartesMapa[0]);
		// alto del mapa
		alto = Integer.parseInt(PartesMapa[1]);

		// hojas de sprites utilizadas
		String hojasUtilizadas = PartesMapa[2];
		String[] hojasSeparadas = hojasUtilizadas.split(",");

		// paleta de sprites
		String paletaEntera = PartesMapa[3];
		String[] partesPaleta = paletaEntera.split("#");

		paleta =  asignarSprite(partesPaleta, hojasSeparadas);
		
		// new Sprite[partesPaleta.length];
		/* colisiones */
		String colisionesEnteras = PartesMapa[4];
		colisiones = extraerColisiones(colisionesEnteras);
		
		String spritesEnteros = PartesMapa[5];
		String[] cadenaSprites = spritesEnteros.split(" ");
		sprites = extraerSprites(cadenaSprites);
		
		
	}

	private Sprite[] asignarSprite(final String[] partesPaleta, final String[] hojasSeparadas){
		Sprite[] paleta =  new Sprite[partesPaleta.length];
		HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/"+hojasSeparadas[0]+".png", 32, true);
		for(int i = 0; i< partesPaleta.length; i++){
			String spriteTemporal = partesPaleta[i];
			String[] partesSprite= spriteTemporal.split("-");
			int indicePaleta= Integer.parseInt(partesSprite[0]);
			int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);
		//	System.out.println("indices: "+indicePaleta);
			paleta[indicePaleta]=hoja.obtenerSprite(indiceSpriteHoja);
 		}
		return paleta;

	}
	
	private boolean[] extraerColisiones(final String cadenaColisiones) {
		boolean[] colisiones = new boolean[cadenaColisiones.length()];
		for(int i = 0; i< cadenaColisiones.length(); i++){
			if(cadenaColisiones.charAt(i) == '0'){
				colisiones[i] = false; 
			}else{
				colisiones[i] = true;
			}
		}
		return colisiones;
	}
	
	private int[] extraerSprites(final String[] cadenasSprites){
		ArrayList<Integer> sprites = new ArrayList<Integer>();
				for (int i = 0; i < cadenasSprites.length; i++) {
					if(cadenasSprites[i].length() == 2){
						sprites.add(Integer.parseInt(cadenasSprites[i]));
					}else{
						//System.out.println(cadenasSprites[i]);
						sprites.add(Integer.parseInt(cadenasSprites[i].substring(0, 2)));
						sprites.add(Integer.parseInt(cadenasSprites[i].substring(2)));
					}
				}
				
				int[] vectorSprites = new int[sprites.size()];
				
				for(int i = 0; i<sprites.size(); i++){
					vectorSprites[i]=sprites.get(i);
				}
				return vectorSprites;
	}

	public void dibujar(Graphics g, int posicionX, int posicionY){
		/*cuadrado*/
		int anchoSprite= Constantes.LADO_SPRITE;
		int altoSprite = anchoSprite;
		
		for(int y = 0 ; y < this.alto ; y++){
			for(int x = 0 ; x < this.ancho; x ++){
				BufferedImage imagen = paleta[sprites[x + y * this.ancho]].obtenerImagen();
				g.drawImage( imagen,  x* anchoSprite - posicionX, y* altoSprite - posicionY, null);
			}
		} 
	}
}