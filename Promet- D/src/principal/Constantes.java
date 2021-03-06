package principal;

import java.awt.Font;

import principal.herramientas.CargadorRecursos;

public class Constantes {
	public static final int LADO_SPRITE = 32;
	public static final int LADO_TILE = 32; 
	public static final int ANCHO_JUEGO = 640;//640
	public static final int ALTO_JUEGO = 360;//360
		
	public static final int ANCHO_PANTALLA_COMPLETA = 1366;
	public static final int ALTO_PANTALLA_COMPLETA= 768;
	
	public static double FACTOR_ESCALADO_X=ANCHO_PANTALLA_COMPLETA/ ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y=ALTO_PANTALLA_COMPLETA/ ALTO_JUEGO;
	
	public static final int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
	public static final int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;
	
	//public static String RUTA_MAPA = "/mapas/Laberinto.pd";
	public static String RUTA_MAPA = "/mapas/Desierto.pd";
	public static String ICONO_RATON= "/imagenes/iconos/iconoCursor.png";
	public static String RUTA_PERSONAJE ="/imagenes/hojasPersonajes/1.png";
	public static String RUTA_ICONO_VENTANA ="/imagenes/iconos/iconoVentana.png";
	public static String RUTA_LOGOTIPO ="/imagenes/iconos/logotipo.png";
	
	public static Font FUENTE_PIXELES = CargadorRecursos.cargarFuente("/fuentes/pcsenior.ttf");
}
