package game.sphere;

import game.Utils;
import game.background.Settings;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.bases.renderer.ImageRenderer;
import game.enemies.BlackEnemy;
import game.enemies.BlueEnemy;
import game.enemies.EnemyExplosion;
import game.enemies.EnemySpawner;

/**
 * Created by Nttung PC on 7/24/2017.
 */
public class SphereBullet extends GameObject  implements PhysicBody {
    public Vector2D velocity;
    BoxCollider boxCollider;
    public SphereBullet() {
        super();
        this.renderer =  new ImageRenderer(Utils.loadAssetImage("sphere-bullets/0.png"));
        this.velocity = new Vector2D();
        boxCollider = new BoxCollider(15,15);
        this.children.add(boxCollider);
    }

    public void newBullet() {
        Vector2D target = EnemySpawner.ePosition;
        this.velocity.addUp(0,-5);
        if (target!=null){
            Vector2D bulletVelocity = target.subtract(position).nomalize().multiply(10);
            this.velocity.set(bulletVelocity);
        }
    }
    public void hitEnemy(){
        BlueEnemy hitEnemy  = Physics.bodyInRect(this.boxCollider,BlueEnemy.class);
        BlackEnemy hitBlack = Physics.bodyInRect(this.boxCollider,BlackEnemy.class);
        if (this.isActive && hitBlack!=null){
            hitBlack.life-=1;
            this.isActive = false;
            if (hitBlack.life <=0){
                hitBlack.isActive = false;
            }
        }
        if (hitEnemy != null && hitEnemy.isActive){
            EnemyExplosion explosion = GameObjectPool.recycle(EnemyExplosion.class);
            explosion.position.set(hitEnemy.position);
            hitEnemy.isActive = false;
            this.isActive = false;
        }
    }
    @Override
    public void run(Vector2D parentPosition) {
        newBullet();
        hitEnemy();
        if (screenPosition.x < 0 || screenPosition.x > Settings.windownWidth-10 || screenPosition.y < 0){
            this.setActive(false);
        }
        super.run(parentPosition);
        this.position.addUp(velocity);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
