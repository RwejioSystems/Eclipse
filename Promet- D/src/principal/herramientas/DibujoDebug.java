package principal.herramientas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DibujoDebug {
	//Wrapper
	//metodo abreviado que junte otros metodos
	//Devolver objeto Construido
	//
	private static int objetodDibujados = 0;
	
	public static int obtenerContadorObjetos(){
		return objetodDibujados;
	}
	
	public static void reiniciarContadorObjetos(){
		objetodDibujados=0;
	}
	
	public static void dibujarImagen(Graphics g, final BufferedImage img, final int x, final int y){
		objetodDibujados++;
		g.drawImage(img, x, y, null);
	}
	
	public static void dibujarImagen(Graphics g, final BufferedImage img, final Point p){
		objetodDibujados++;
		g.drawImage(img, p.x, p.y, null);
	}
	
	public static void dibujarString(final Graphics g, final String s, final int x, final int y){
		objetodDibujados++;
		g.drawString(s, x, y);
	}
	
	public static void dibujarString(final Graphics g, final String s, Point p){
		objetodDibujados++;
		g.drawString(s, p.x, p.y);
	}
	
	public static void dibujarString(final Graphics g, final String s, final int x, final int y, Color color){
		objetodDibujados++;
		g.setColor(color);
		g.drawString(s, x, y);
	}
	
	public static void dibujarString(final Graphics g, final String s, Point p, Color color){
		objetodDibujados++;
		g.setColor(color);
		g.drawString(s, p.x, p.y);
	}
	
	public static void dibujarRectanguloRelleno(final Graphics g, final int x, final int y, final int ancho, final int alto){
		objetodDibujados++;
		g.fillRect(x, y, ancho, alto);
	}
	
	public static void dibujarRectanguloRelleno(final Graphics g, final Rectangle r){
		objetodDibujados++;
		g.fillRect(r.x, r.y, r.width, r.height);
	}
	
	public static void dibujarRectanguloRelleno(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color color){
		objetodDibujados++;
		g.setColor(color);
		g.fillRect(x, y, ancho, alto);
	}
	
	public static void dibujarRectanguloRelleno(final Graphics g, final Rectangle r, final Color color){
		objetodDibujados++;
		g.setColor(color);
		g.fillRect(r.x, r.y, r.width, r.height);
	}
	
	public static void dibujarRectanguloContorno(final Graphics g, final int x, final int y, final int ancho, final int alto){
		objetodDibujados++;
		g.drawRect(x, y, ancho, alto);
	}
	
	public static void dibujarRectanguloContorno(final Graphics g, final Rectangle r){
		objetodDibujados++;
		g.drawRect(r.x, r.y, r.width, r.height);
	}
	
	public static void dibujarRectanguloContorno(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color color){
		objetodDibujados++;
		g.setColor(color);
		g.drawRect(x, y, ancho, alto);
	}
	
	public static void dibujarRectanguloContorno(final Graphics g, final Rectangle r, final Color color){
		objetodDibujados++;
		g.setColor(color);
		g.drawRect(r.x, r.y, r.width, r.height);
	}
}
