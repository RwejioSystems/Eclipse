package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.herramientas.DibujoDebug;

public abstract class SeccionMenu {
	protected final String nombreSeccion;
	protected final Rectangle etiquetaMenu;
	final Color fondo = new Color(128, 128, 128);
	
	public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu){
		this.nombreSeccion = nombreSeccion;
		this.etiquetaMenu = etiquetaMenu;
	}
	
	public abstract void actualizar();
	
	public abstract void dibujar(final Graphics g);
	
	public void dibujarEtiquetaInactiva(final Graphics g){
		DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
		DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.black);
	}
	
	public void dibujarEtiquetaActiva(final Graphics g){
		DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, fondo);
		final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 5 , etiquetaMenu.height);
		final Color colorActivo = new Color(0xff6700);
		DibujoDebug.dibujarRectanguloRelleno(g, marcaActiva, colorActivo);
		DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.black);
	}
	
	public void dibujarEtiquetaInactivaResaltada(final Graphics g){
		DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
		
		final Rectangle marcaActivaM = new Rectangle(etiquetaMenu.x + etiquetaMenu.width -5, etiquetaMenu.y + 5, 5, etiquetaMenu.height -10);
		final Color colorActivo = new Color(0x2a2a2a);
		DibujoDebug.dibujarRectanguloRelleno(g, marcaActivaM, colorActivo);
		DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.black);
	}
	
	public void dibujarEtiquetaActivaResaltada(final Graphics g){
		DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, fondo);
		//rectangulo naranja
		final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 5 , etiquetaMenu.height);
		final Color colorActivo = new Color(0xff6700);
		DibujoDebug.dibujarRectanguloRelleno(g, marcaActiva, colorActivo);
		//rectangulo mouse hover
		final Rectangle marcaActivaM = new Rectangle(etiquetaMenu.x + etiquetaMenu.width -5, etiquetaMenu.y + 5, 5, etiquetaMenu.height -10);
		final Color colorActivoM = new Color(0x2a2a2a);
		DibujoDebug.dibujarRectanguloRelleno(g, marcaActivaM, colorActivoM);
		
		DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.black);
	}
	
	public String obtenerNombreSeccion(){
		return nombreSeccion;
	}
	
	public Rectangle obtenerEtiquetaMenu(){
		return etiquetaMenu;
	}
	
	public Rectangle obtenerEtiquetaEscalada(){
		final Rectangle etiquetaEscalada = new Rectangle( (int)(etiquetaMenu.x * Constantes.FACTOR_ESCALADO_X), 
				(int) (etiquetaMenu.y * Constantes.FACTOR_ESCALADO_Y),
				(int) (etiquetaMenu.width * Constantes.FACTOR_ESCALADO_X),
				(int) (etiquetaMenu.height * Constantes.FACTOR_ESCALADO_Y));
		return etiquetaEscalada;
	}
}
