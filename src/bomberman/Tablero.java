package bomberman;

public class Tablero {
    protected Nivel nivel;
    protected Juego juego;
    protected Teclado teclado;
    protected Pantalla pantalla;
    private int tiempo = JuegoConfig.TIEMPO;
    private int puntos = JuegoConfig.PUNTOS;
    private int vidas = JuegoConfig.VIDAS;

    public Tablero(Juego juego, Teclado teclado, Pantalla pantalla){
        this.juego = juego;
        this.teclado = teclado;
        this.pantalla = pantalla;
    }

    public int getTimpo() {
        return tiempo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getVidas(){
        return vidas;
    }
}
