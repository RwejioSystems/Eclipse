package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoDebug;

public class MenuInferior {
	private Rectangle areaInventario;
	private Rectangle bordeAreaInventario;
	private Color rojoClaro;
	private Color rojoObscuro;
	private Color grisObscuro;
	private Color azulClaro;
	private Color azulObscuro;
	private Color verdeClaro;
	private Color VerdeObscuro;
	private Color rosaClaro;
	private Color rosaObscuro;
	
	public MenuInferior(final Jugador jugador) {
		int altoMenu = 64;
		areaInventario = new Rectangle(5, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
		bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);
		
		grisObscuro = new Color(2, 28,28);
		
		rojoClaro = new Color(255, 0, 0);
		rojoObscuro = new Color(150, 0, 0);
		azulClaro = new Color(0, 200, 255);
		azulObscuro = new Color(0, 132, 168);
		verdeClaro = new Color(0, 255, 0);
		VerdeObscuro = new Color(0, 150, 0);
		rosaClaro = new Color(255, 0, 10);
		rosaObscuro = new Color(128, 0, 74);
	}
	
	public void dibujar(final Graphics g, final Jugador jugador){
		dibjarAreaIventario(g);
		dibujarBarraVitalidad(g);
		dibujarBarraMana(g);
		dibujarBarraResistencia(g, jugador.resistencia);
		dibujarBarraExperiencia(g, 2);
		dibujarRanurasObjetos(g);
	}
	
	private void dibjarAreaIventario(final Graphics g){
		DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario, Color.white);
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, grisObscuro);
	}
	
	private void dibujarBarraVitalidad(final Graphics g){
		final int medidaVertical = 4;
		final int anchoTotal=100;
		
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical, anchoTotal, medidaVertical, rojoClaro);
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 2, anchoTotal, medidaVertical, rojoObscuro);
		g.setColor(Color.white);
		DibujoDebug.dibujarString(g, "VIT", areaInventario.x + 5, areaInventario.y+medidaVertical*3);
		DibujoDebug.dibujarString(g, "100", anchoTotal+ 55, areaInventario.y+medidaVertical*3);
	}
	
	private void dibujarBarraMana(final Graphics g){
		final int medidaVertical = 4;
		final int anchoTotal=100;
		
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 4, anchoTotal, medidaVertical, azulClaro);
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 5, anchoTotal, medidaVertical, azulObscuro);
		
		g.setColor(Color.white);
		DibujoDebug.dibujarString(g, "Mana", areaInventario.x + 5, areaInventario.y+medidaVertical*6);
		DibujoDebug.dibujarString(g, "100", anchoTotal+ 55, areaInventario.y+medidaVertical*6);
	}
	
	public void dibujarBarraResistencia(final Graphics g, final int resistencia){
		final int medidaVertical = 4;
		final int anchoTotal=100;
		final int ancho = anchoTotal * resistencia / Jugador.RESISTENCIA_TOTAL;
		
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 7, ancho, medidaVertical, verdeClaro);
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 8, ancho, medidaVertical, VerdeObscuro);
		
		g.setColor(Color.white);
		DibujoDebug.dibujarString(g, "Rst", areaInventario.x + 5, areaInventario.y+medidaVertical * 9);
		DibujoDebug.dibujarString(g, ""+resistencia, anchoTotal+ 55, areaInventario.y+medidaVertical * 9);
	}
	
	private void dibujarBarraExperiencia(final Graphics g, final int experiencia){
		final int medidaVertical = 4;
		final int anchoTotal=100;
		final int ancho = anchoTotal * experiencia / anchoTotal;
		
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 10, ancho, medidaVertical, rosaClaro);
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y+ medidaVertical * 11, ancho, medidaVertical, rosaObscuro);
		
		g.setColor(Color.white);
		DibujoDebug.dibujarString(g, "Exp", areaInventario.x + 5, areaInventario.y+medidaVertical * 12);
		DibujoDebug.dibujarString(g, ancho+"%", anchoTotal+ 55, areaInventario.y+medidaVertical * 12);
	}
	
	private void dibujarRanurasObjetos(final Graphics g){
		final int anchoRanura = Constantes.LADO_SPRITE;
		final int numeroRanuras = 10;
		final int espaciadoRanuras = 10;
		final int anchoTotal = anchoRanura * numeroRanuras + espaciadoRanuras * numeroRanuras;
		final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
		final int anchoRanuraYEspacio = anchoRanura + espaciadoRanuras;
		
		g.setColor(Color.white);
		
		for(int i = 0; i < numeroRanuras; i++){
			int xActual = xInicial + anchoRanuraYEspacio * i;
			Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);
			DibujoDebug.dibujarRectanguloContorno(g, ranura);
			DibujoDebug.dibujarString(g, ""+i, xActual + 13, areaInventario.y + 54);
		}
	}
}