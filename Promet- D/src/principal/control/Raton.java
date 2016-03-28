package principal.control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;

public class Raton extends MouseAdapter{
	private Cursor cursor;
	private final Point posision;
	private boolean click;
	
	public Cursor obtenerCursor(){
		return this.cursor;
	}
	
	private void actualizarPosision(final SuperficieDibujo sd){
		final Point posisionInicial= MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(posisionInicial, sd);
		posision.setLocation(posisionInicial.getX(), posisionInicial.getY());
	}
	
	public Raton(final SuperficieDibujo sd){
		Toolkit configuracion = Toolkit.getDefaultToolkit();
		BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.ICONO_RATON);
		Point punta= new Point(0,0);
		this.cursor= configuracion.createCustomCursor(icono, punta, "Cursor por defecto");
		posision = new Point();
		actualizarPosision(sd);
		click = false;
	}
	
	public Point obtenerPuntoPosision(){
		return posision;
	}
	
	public Rectangle obtenerRectanguloPosision(){
		final Rectangle area = new Rectangle(posision.x, posision.y, 1, 1);
		return area;
	}
	
	public void actualizar(final SuperficieDibujo sd){
		actualizarPosision(sd);
		
	}
	
	public void dibujar(Graphics g){
		DatosDebug.enviarDato("RX:"+ posision.getX());
		DatosDebug.enviarDato("RX:"+ posision.getY());
	}
	
	public void mouseClicked(MouseEvent e){
		if(!click){
			click = true;
		}
	}
	
	public boolean obtenerClick(){
		return click;
	}
	
	public void reiniciarClick(){
		if(click){
			click= false;
		}
	}
}
