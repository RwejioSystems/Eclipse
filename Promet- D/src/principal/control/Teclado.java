package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	
	public Tecla arriba = new Tecla();
	public Tecla abajo = new Tecla();
	public Tecla izquierda = new Tecla();
	public Tecla derecha = new Tecla();
	private boolean corriendo = false;
	public boolean opcionesDebug=false;
	public boolean inventarioActivo = false;
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_W:
				arriba.teclaPulsada();
			break;
			case KeyEvent.VK_S:
				abajo.teclaPulsada();
			break;
			case KeyEvent.VK_D:
				derecha.teclaPulsada();
			break;
			case KeyEvent.VK_A:
				izquierda.teclaPulsada();
			break;
			case KeyEvent.VK_SHIFT:
				corriendo=true;
			break;
			case KeyEvent.VK_F3:
					opcionesDebug=!opcionesDebug;
			break;
			case KeyEvent.VK_I:
				inventarioActivo=!inventarioActivo;
			break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
			break;
		}
	}
	
	public boolean estaCorriendo(){
		return corriendo;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_W:
			arriba.teclaLiberada();
		break;
		case KeyEvent.VK_S:
			abajo.teclaLiberada();
		break;
		case KeyEvent.VK_D:
			derecha.teclaLiberada();
		break;
		case KeyEvent.VK_A:
			izquierda.teclaLiberada();
		break;
		case KeyEvent.VK_SHIFT:
			corriendo=false;
		break;
	}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
