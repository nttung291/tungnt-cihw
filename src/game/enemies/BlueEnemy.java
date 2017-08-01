package game.enemies;

import game.Utils;
import game.background.Settings;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.renderer.Animation;
import game.player.Player;

import java.util.Random;

import static game.background.Settings.frameCounterStillStand;

/**
 * Created by Nttung PC on 7/23/2017.
 */
public class BlueEnemy extends GameObject implements PhysicBody{

    public Vector2D velocity;
    boolean bulletDisabled;
    BoxCollider boxCollider;

    int randy =  new Random().nextInt(200)+100;
    int randx =  new Random().nextInt(380);
    int rand =  new Random().nextInt(2);

    public BlueEnemy() {
        super();
        this.position.set(randx,0);
        this.renderer = new Animation(
                Utils.loadAssetImage("enemies/level0/blue/0.png"),
                Utils.loadAssetImage("enemies/level0/blue/1.png"),
                Utils.loadAssetImage("enemies/level0/blue/2.png"),
                Utils.loadAssetImage("enemies/level0/blue/3.png")
        );
        velocity = new Vector2D();
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
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
        EnemySpawner.ePosition= position;
    }
    private void shoot() {
       if (!bulletDisabled && this.position.y > randy){
           Vector2D target = Player.instance.position;
           Vector2D bulletVelocity = target.subtract(position).nomalize().multiply(5);
           for (double i = -Math.PI/9; i <= Math.PI/9; i += Math.PI/9) {
               EnemyBullet newBullet = GameObjectPool.recycle(EnemyBullet.class);
               newBullet.velocity.set(bulletVelocity.turn((float) i));
               newBullet.position.set(this.position.add(0,15));
           }
           bulletDisabled = true;
       }
    }

    public void getHit(int damage){
        this.isActive = false;
        EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
        enemyExplosion.position.set(this.position);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        shoot();
        if (this.screenPosition.x < 0 || this.screenPosition.x > Settings.windownWidth-10){
            this.setActive(false);
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
