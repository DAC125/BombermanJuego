package bomberman;

public class Imagenes {

    private CargaImagenes cargaImagenes;

    public final int TAMAÑO = 16;
    public int x=0,y=0;
    public int[] pixeles;

    public Imagenes(CargaImagenes cargaImagenes){
        this.cargaImagenes = cargaImagenes;
        pixeles = new int[16 * 16];
        carga();
    }
    private void carga(){
        for (int y = 0; y<TAMAÑO; y++){
            for (int x = 0; x<TAMAÑO; x++){
                pixeles[x + y *TAMAÑO] = cargaImagenes.pixeles[(x + this.x) + (y + this.y) * cargaImagenes.TAMAÑO];
            }
        }
    }

    public int getPixel(int i) {
        return pixeles[i];
    }
}
