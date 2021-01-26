package bomberman;

//Posee la "configuración" de cada tipo de villano
//El nombre de la variable es el nivel en el que aparece el villano
//{X,Y,I,J} = {Velocidad, AtraviesaMuros, nivelIA, PuntosMorir}
//Notas: velocidad respecto al jugador
public interface ParametrosVillanos {
    float[] niv1 = {0.66f,0,0,100};
    float[] niv2 = {1,0,0,200};
    float[] niv3 = {1.33f,0,2,400};
    float[][] primeros = {niv1,niv2,niv3};
    float[] niv6 = {0.33f,1,1,1000};
    float[] niv8 = {1,1,2,2000};
    float[] niv11 = {1.5f,0,3,3000};
    float[] niv14= {1.5f,1,4,4000};
}
