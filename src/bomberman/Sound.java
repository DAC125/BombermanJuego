package bomberman;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//Clase encargada de Reproducir los sonidos necesarios para el juego
public class Sound {

    //Metodo que recibe una dirección de algún sonido, y si este se reproduce una y otra vez
    private void reproducirSonido(String nombreSonido, int loop){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();//reproduce el clip creado
            if(loop==1){//Reproduce indefinidamente
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    /*Metodos que llaman a la función anterior con un archivo .wav para su reproducción*/

    public void reproducirSonidoMuerte(){
        reproducirSonido("res/sonidos/Muerte1.wav",0);
    }

    public void reproducirSonidoBG(){
        reproducirSonido("res/sonidos/BG.wav",1);
    }

    public void reproducirSonidoExplosion(){
        reproducirSonido("/Sounds/explosion.wav",0);
    }
}
