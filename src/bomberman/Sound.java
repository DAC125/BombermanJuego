package bomberman;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public Sound(){}

    private void reproducirSonido(String nombreSonido, int loop){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if(loop==1){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void reproducirSonidoMuerte(){
        reproducirSonido("resources/Sounds/Shot.wav",0);
    }

    public void reproducirSonidoBG(){
        reproducirSonido("resources/Sounds/HappyBirthday.wav",1);
    }

    public void reproducirSonidoExplosion(){
        reproducirSonido("resources/Sounds/explosion.wav",0);
    }
}
