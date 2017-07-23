package game.enemies;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static game.player.Player.newPlayer;


/**
 * Created by Nttung PC on 7/18/2017.
 */
public class PinkEnemies extends GameObject{
    int randy =  new Random().nextInt(300)+100;
    int randx =  new Random().nextInt(380);
    int rand =  new Random().nextInt(2);
    boolean checkFire;

    private int speed;

    ArrayList<Bullets> bullets;

    FrameCounter coolDownCounter;
    boolean bulletDisabled;

    FrameCounter frameCounterChangePicture;
    private ImageRenderer imageRenderer1;
    private ImageRenderer imageRenderer2;

    private FrameCounter frameCounterStillStand;

    public PinkEnemies() {
        this.position = new Vector2D(randx,0);
        bullets = new ArrayList<>();
        speed = 2;

        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/2.png"));

        coolDownCounter = new FrameCounter(25);
        frameCounterChangePicture = new FrameCounter(5);
        frameCounterStillStand = new FrameCounter(50);

        checkFire = true;

        bulletDisabled = true;
    }
    public void castBullets () {
        if (bulletDisabled && this.position.y >= randy) {
            if (coolDownCounter.run()){
                Vector2D newVector2d = new Vector2D(newPlayer.position.x - this.position.x, newPlayer.position.y - this.position.y);
                newVector2d = newVector2d.nomalize();
                for (double i = -Math.PI/9; i <= Math.PI/9; i += Math.PI/9) {
                    GameObject.add(new Bullets((float)(newVector2d.x*Math.cos(i) - newVector2d.y*Math.sin(i)),(float)(newVector2d.x*Math.sin(i) + newVector2d.y*Math.cos(i)), this.position));
                }
                bulletDisabled = false;
            }
        }
    }

    public void removeBullets(){
        Iterator<Bullets> iterator = bullets.iterator();
        while(iterator.hasNext()){
            Bullets bullet = iterator.next();
            if(bullet.position.x < 0 || bullet.position.x > 375 || bullet.position.y > 800 || bullet.position.y < 0){
                iterator.remove();
            }
        }
    }

    @Override
    public void updatePicture() {
        if (frameCounterChangePicture.run()) {
            if (renderer == imageRenderer1) renderer = imageRenderer2;
            else renderer = imageRenderer1;
            frameCounterChangePicture.reset();
        }
    }

    @Override
    public void run() {
        castBullets();
        removeBullets();
        if (position.y > randy){
           if (frameCounterStillStand.run()){
             switch (rand) {
                 case 0:
                     this.position.addUp(speed, 0);
                     break;
                 case 1:
                     this.position.addUp(-speed, 0);
                     break;
                 default:
                     break;
             }
           }
       }
       else{
            this.position.addUp(0,speed);
        }
    }

}
