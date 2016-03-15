package mapa.cuadro;

import graficos.HojaSprites;
import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	private int x;
	private int y;
	
	public Sprite sprite;
	public static final int LADO=32;
	private boolean solido;
	//Coleccion de cuadros
		//Cuadros mapa Desierto
			public static final Cuadro VACIO = new Cuadro(Sprite.VACIO, true);
			public static final Cuadro ASFALTO=new Cuadro(Sprite.ASFALTO);
			public static final Cuadro ARENA = new Cuadro(Sprite.ARENA);
			public static final Cuadro BORDE_CARRETERA = new Cuadro(Sprite.BORDE_CARRETERA);
			public static final Cuadro BORDE_CARRETERAB = new Cuadro(Sprite.BORDE_CARRETERAB);
			public static final Cuadro CENTRO_CARRETERA = new Cuadro(Sprite.CENTRO_CARRETERA);
			public static final Cuadro ESQUINA_CARRETERA = new Cuadro(Sprite.ESQUINA_CARRETERA);
			public static final Cuadro PARED_PIEDRA = new Cuadro(Sprite.PARED_PIEDRA);
			public static final Cuadro PARED_PIEDRA_INFERIOR = new Cuadro(Sprite.PARED_PIEDRA_INFERIOR);
			public static final Cuadro PARED_PIEDRA_CARRETERA = new Cuadro(Sprite.PARED_PIEDRA_CARRETERA);
			public static final Cuadro PARED_PIEDRA_CARRETERAB = new Cuadro(Sprite.PARED_PIEDRA_CARRETERAB);
			public static final Cuadro PUERTA_SUPERIOR_IZQUIERDA = new Cuadro(Sprite.PUERTA_SUPERIOR_IZQUIERDA);
			public static final Cuadro PUERTA_INTERMEDIA_IZQUIERDA = new Cuadro(Sprite.PUERTA_INTERMEDIA_IZQUIERDA);
			public static final Cuadro PUERTA_SUPERIOR_DERECHA = new Cuadro(Sprite.PUERTA_SUPERIOR_DERECHA);
			public static final Cuadro PUERTA_INTERMEDIA_DERECHA = new Cuadro(Sprite.PUERTA_INTERMEDIA_DERECHA);
			public static final Cuadro PUERTA_SUPERIOR = new Cuadro(Sprite.PUERTA_SUPERIOR);
			public static final Cuadro PUERTA_INFERIOR = new Cuadro(Sprite.PUERTA_INFERIOR);
			public static final Cuadro OXIDO = new Cuadro(Sprite.OXIDO);
			public static final Cuadro VENTANA_PARED = new Cuadro(Sprite.VENTANA_PARED);
			public static final Cuadro PARED_SUPERIOR = new Cuadro(Sprite.PARED_SUPERIOR);
		//fin del conjunto de cuedros de desierto
		
		//CONJUNTO DE CUADROS DE LABERINTO
			public static final Cuadro PISO_LABERINTO = new Cuadro(Sprite.PISO_LABERINTO);
			public static final Cuadro PARED_LADRILLO_HORIZONTAL = new Cuadro(Sprite.PARED_LADRILLO_HORIZONTAL, true);
			public static final Cuadro PARED_LADRILLO_HORIZONTAL_INVERTIDO = new Cuadro(Sprite.PARED_LADRILLO_HORIZONTAL_INVERTIDO, true);
			public static final Cuadro PARED_LADRILLO_VERTICAL = new Cuadro(Sprite.PARED_LADRILLO_VERTICAL, true);
			public static final Cuadro PARED_LADRILLO_VERTICAL_INVERTIDO = new Cuadro(Sprite.PARED_LADRILLO_VERTICAL_INVERTIDO, true);
			public static final Cuadro PARED_LADRILLO_PISO_HORIZONTAL = new Cuadro(Sprite.PARED_LADRILLO_PISO_HORIZONTAL, true);
			public static final Cuadro PARED_LADRILLO_PISO_HORIZONTAL_INVERTIDO = new Cuadro(Sprite.PARED_LADRILLO_PISO_HORIZONTAL_INVERTIDO, true);
			public static final Cuadro PARED_LADRILLO_PISO_VERTICAL = new Cuadro(Sprite.PARED_LADRILLO_PISO_VERTICAL, true);
			public static final Cuadro PARED_LADRILLO_PISO_VERTICAL_INVERTIDO = new Cuadro(Sprite.PARED_LADRILLO_PISO_VERTICAL_INVERTIDO, true);
			
			public static final Cuadro ESQUINA_PARED_LADRILLO_PISO_SUP_IZQ = new Cuadro(Sprite.ESQUINA_PARED_LADRILLO_PISO_SUP_IZQ, true);
			public static final Cuadro ESQUINA_PARED_LADRILLO_PISO_SUP_DER = new Cuadro(Sprite.ESQUINA_PARED_LADRILLO_PISO_SUP_DER, true);
			public static final Cuadro ESQUINA_PARED_LADRILLO_PISO_INF_DER = new Cuadro(Sprite.ESQUINA_PARED_LADRILLO_PISO_INF_DER, true);
			public static final Cuadro ESQUINA_PARED_LADRILLO_PISO_INF_IZQ = new Cuadro(Sprite.ESQUINA_PARED_LADRILLO_PISO_INF_IZQ, true);
			
			public static final Cuadro ESQUINA_ABIERTA_PARED_LADRILLO_SUP_DER = new Cuadro(Sprite.ESQUINA_ABIERTA_PARED_LADRILLO_SUP_DER, true);
			public static final Cuadro ESQUINA_ABIERTA_PARED_LADRILLO_SUP_IZQ = new Cuadro(Sprite.ESQUINA_ABIERTA_PARED_LADRILLO_SUP_IZQ, true);
			public static final Cuadro ESQUINA_ABIERTA_PARED_LADRILLO_INF_DER = new Cuadro(Sprite.ESQUINA_ABIERTA_PARED_LADRILLO_INF_DER, true);
			public static final Cuadro ESQUINA_ABIERTA_PARED_LADRILLO_INF_IZQ = new Cuadro(Sprite.ESQUINA_ABIERTA_PARED_LADRILLO_INF_IZQ, true);
		//FIN DEL CONJUNTO DE CUADROS DEL LABERINTO
	//fin de la coleccion
	public Cuadro(Sprite sprite){
		this.sprite= sprite;
		solido=false;
	}
	
	public Cuadro(Sprite sprite, boolean solido){
		this.sprite = sprite;
		this.solido = solido;
	}
	
	public void mostrar(int x, int y, Pantalla pantalla){
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}
	
	public boolean esSolido(){
			return solido;
	}
}
