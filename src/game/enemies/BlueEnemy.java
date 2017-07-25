package game.enemies;

import game.Utils;
import game.bases.*;
import game.player.Player;

import java.util.Random;

import static game.background.Settings.frameCounterBlueImage;
import static game.background.Settings.frameCounterStillStand;

/**
 * Created by Nttung PC on 7/23/2017.
 */
public class BlueEnemy extends GameObject {

    public Vector2D velocity;
    boolean bulletDisabled;
    public static Vector2D ePosition;

    private ImageRenderer imageRenderer1;
    private ImageRenderer imageRenderer2;
    private ImageRenderer imageRenderer3;
    private ImageRenderer imageRenderer4;

    int randy =  new Random().nextInt(200)+100;
    int randx =  new Random().nextInt(380);
    int rand =  new Random().nextInt(2);

    public BlueEnemy() {
        super();
        this.position.set(randx,0);
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/3.png"));
        velocity = new Vector2D();
        ePosition = new Vector2D();
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        indentify = 3;
    }

    public void move(){
        this.position.addUp(0,3);
        if (position.y > randy){
            if (frameCounterStillStand.run()){
                switch (rand) {
                    case 0:
                        this.position.addUp(3, 0);
                        break;
                    case 1:
                        this.position.addUp(-3, 0);
                        break;
                    default:
                        break;
                }
            }
        }
        ePosition = this.position;
    }
    private void shoot() {
       if (!bulletDisabled && this.position.y > randy){
           Vector2D target = Player.instance.position;
           Vector2D bulletVelocity = target.subtract(position).nomalize().multiply(5);
           for (double i = -Math.PI/9; i <= Math.PI/9; i += Math.PI/9) {
               EnemyBullet newBullet = new EnemyBullet();
               newBullet.velocity.set(bulletVelocity.turn((float) i));
               newBullet.position.set(this.position.add(0,15));
               GameObject.add(newBullet);
           }
           bulletDisabled = true;
       }
    }

    @Override
    public void updatePicture() {
        if ( frameCounterBlueImage.run()) {
            if (renderer == imageRenderer1) renderer = imageRenderer2;
            else if (renderer == imageRenderer2) renderer = imageRenderer3;
            else if (renderer == imageRenderer3) renderer = imageRenderer4;
            else  renderer = imageRenderer1;
            frameCounterBlueImage.reset();
        }
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        shoot();
    }

}
