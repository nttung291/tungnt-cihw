package game.enemies;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.util.ArrayList;
import java.util.Random;

import static game.player.Player.newPlayer;

/**
 * Created by Nttung PC on 7/21/2017.
 */
public class BlackEnemy extends GameObject{
    ArrayList<Bullets> bullets;

    FrameCounter coolDownCounter;
    FrameCounter frameCounterStand;
    boolean bulletDisabled;
    boolean moveDisabled;
    public int check=0;

    public BlackEnemy(){
        this.position = new Vector2D(200,0);
        this.bullets = new ArrayList<>();
        renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/0.png"));
        coolDownCounter = new FrameCounter(170);
        frameCounterStand = new FrameCounter(50);
    }

    public void castBullets1 () {
        if (!bulletDisabled){
           if (coolDownCounter.run()){
               Vector2D newVector2d = new Vector2D(newPlayer.position.x - this.position.x, newPlayer.position.y - this.position.y);
               newVector2d = newVector2d.nomalize();
               for (double i = 0; i < 2*Math.PI; i += Math.PI/25) {
                   GameObject.add(new Bullets((float)(newVector2d.x*Math.cos(i) - newVector2d.y*Math.sin(i)),(float)(newVector2d.x*Math.sin(i) + newVector2d.y*Math.cos(i)), this.position));
               }
               bulletDisabled = true;
               coolDownCounter.reset();
           }
        }
    }
    public void castBullets2 () {
        if (!bulletDisabled){
            if (coolDownCounter.run()){
                Vector2D newVector2d = new Vector2D(newPlayer.position.x - this.position.x, newPlayer.position.y - this.position.y);
                newVector2d = newVector2d.nomalize();
                for (float i = 4; i >= 3.5; i -= 0.05) {
                    GameObject.add(new Bullets(newVector2d.x*i, newVector2d.y*i, this.position));
                }
                bulletDisabled = true;
                coolDownCounter.reset();
            }
        }
    }

    public void coolDownMove() {
        if(moveDisabled){
            boolean status = frameCounterStand.run();
            if (status){
                moveDisabled = false;
                frameCounterStand.reset();
            }
        }
    }
    public void move(){
        if(position.y <= 100){
            this.position.addUp(0,1);
        }
        if (position.x < 20){
            check = 0;
        }
        if (position.x >= 370){
            check = 1;
        }
        if (check == 0){
            this.position.addUp(1,0);
        }
        else{
            this.position.addUp(-1,0);
        }
    }

    @Override
    public void run() {
       if (frameCounterStand.run()){
           this.position.addUp(0,0);
       }else{
           move();
           frameCounterStand.reset();
       }
        switch (new Random().nextInt(1)){
            case 0:
                castBullets1();
                bulletDisabled = false;
            case 1:
                castBullets2();
                bulletDisabled = false;
        }
    }
}
