package principal.mapas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {

	private final int ancho;
	private final int alto;
	
	private final Point posisionInicial;
	private final Point puntoSalida;
	private String siguienteMapa;
	private Rectangle ZonaSalida;
	private String[] PartesMapa;
	private final Sprite[] paleta;
	private final boolean[] colisiones;
	public ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();
	private final int[] sprites;
	private final int MARGEN_X = (Constantes.ANCHO_JUEGO / 2) - (Constantes.LADO_SPRITE / 2);
	private final int MARGEN_Y = (Constantes.ALTO_JUEGO / 2) - (Constantes.LADO_SPRITE / 2);
	
	public Point obtenerPuntoSalida(){
		return puntoSalida;
	}
	
	public Point obtenerPosisionInicial(){
		return posisionInicial;
	}
	
	public String obtenerSiguienteMapa(){
		return siguienteMapa;
	}
	
	public Rectangle obtenerZonaSalida(){
		return ZonaSalida;
	}
	
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
		
		//Posision inicial para el mapa
		String posision = PartesMapa[6];
		String[] posisiones =  posision.split("-");
		posisionInicial = new Point();
		posisionInicial.x =Integer.parseInt(posisiones[0]) * Constantes.LADO_SPRITE;
		posisionInicial.y =Integer.parseInt(posisiones[1]) * Constantes.LADO_SPRITE;
		//multiplicar por constantes.ladosprite si se esta manejando en tiles
		
		//Salida
		String salida = PartesMapa[7];
		String[] datosSalida =  salida.split("-");
		puntoSalida = new Point();
		puntoSalida.x = Integer.parseInt(datosSalida[0]);
		puntoSalida.y = Integer.parseInt(datosSalida[1]);
		siguienteMapa = datosSalida[2];
		ZonaSalida = new Rectangle();
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

	public void dibujar(Graphics g, final int posicionX, final int posicionY){
		/*cuadrado*/
		
		for(int y = 0 ; y < this.alto ; y++){
			for(int x = 0 ; x < this.ancho; x ++){
				BufferedImage imagen = paleta[sprites[x + y * this.ancho]].obtenerImagen();
				
				int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
				int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
				DibujoDebug.dibujarImagen(g, imagen, puntoX, puntoY);
				
				
				/*
					g.setColor(Color.green);
				 	for(int i = 0; i< areasColision.size(); i++){
					Rectangle rect = areasColision.get(i);
					g.drawRect(rect.x, rect.y, rect.width,rect.height);
				}
				*/
			}
		} 
	}
	
	public void actualizar(final int posicionX, final int posicionY){
		actualizarAreasColision(posicionX, posicionY);
		actualizarZonaSalida(posicionX, posicionY);
	}
	
	private void actualizarAreasColision(final int posicionX, final int posicionY){
		if(!areasColision.isEmpty()){
			areasColision.clear();
		}
		
		for(int y = 0 ; y < this.alto; y++){
			for (int x = 0; x < this.ancho; x++){
				int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
				int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;
				
				if(colisiones[x + y * this.ancho]){
					final Rectangle AreaColision = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
					areasColision.add(AreaColision);
				}
			}
		}
	}
	
	public Rectangle obtenerBordes(final int posisionX, final int posisionY, final int anchoJugador, final int altoJugador){
		int x = MARGEN_X - posisionX + anchoJugador;
		int y = MARGEN_Y - posisionY + altoJugador;
		int ancho = this.ancho * Constantes.LADO_SPRITE - anchoJugador * 2;
		int alto = this.alto * Constantes.LADO_SPRITE - altoJugador *2;
		return new Rectangle(x, y, ancho, alto);
	}
	
	private void actualizarZonaSalida(final int posisionX, final int posisionY){
		int puntoX = ((int)puntoSalida.getX()) * Constantes.LADO_SPRITE - posisionX + MARGEN_X;
		int puntoY = ((int)puntoSalida.getY()) * Constantes.LADO_SPRITE - posisionY + MARGEN_Y;
		
		ZonaSalida= new Rectangle(puntoX, puntoY,  Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
	}
}