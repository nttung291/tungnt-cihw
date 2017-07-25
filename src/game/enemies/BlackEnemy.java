package game.enemies;

import game.Utils;
import game.bases.*;
import game.player.Player;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Nttung PC on 7/25/2017.
 */
public class BlackEnemy extends GameObject{
    ArrayList<EnemyBullet> bullets;

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
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        indentify=4;
    }

    public void castBullets1 () {
        if (!bulletDisabled){
            if (coolDownCounter.run()){
                Vector2D target = Player.instance.screenPosition;
                Vector2D bulletVelocity = target.subtract(position).nomalize().multiply(5);
                for (double i = 0; i < 2*Math.PI; i += Math.PI/25) {
                    EnemyBullet newBullet = new EnemyBullet();
                    newBullet.velocity.set(bulletVelocity.turn((float) i));
                    newBullet.position.set(this.position.add(0,15));
                    GameObject.add(newBullet);
                }
                bulletDisabled = true;
                coolDownCounter.reset();
            }
        }
    }
    public void castBullets2 () {
        if (!bulletDisabled){
            if (coolDownCounter.run()){
                Vector2D target = Player.instance.screenPosition;
                Vector2D bulletVelocity = target.subtract(position).nomalize().multiply(5);
                for (float i = 4; i >= 3.5; i -= 0.05) {
                    EnemyBullet newBullet = new EnemyBullet();
                    newBullet.velocity.set(bulletVelocity.multiply(i));
                    newBullet.position.set(this.position.add(0,15));
                    GameObject.add(newBullet);
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
        EnemySpawner.ePosition= position;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
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
