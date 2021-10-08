package bomberman;

import java.awt.image.BufferedImage;

//clase encargada de devolver el sprite o imagen para de esta menera lograr crear el efecto
//de caminar de los personajes
public class Animacion {
    private   int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;//guarda las diferentes imagenes de dirección

    //Constructor de la clase
    public Animacion(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }


    //actualiza el indice de la imagen
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if (index >= frames.length)
                index = 0;
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}
