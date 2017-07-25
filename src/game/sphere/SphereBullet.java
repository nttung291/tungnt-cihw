package game.sphere;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.enemies.BlueEnemy;

/**
 * Created by Nttung PC on 7/24/2017.
 */
public class SphereBullet extends GameObject {
    public Vector2D velocity;
    public SphereBullet() {
        super();
        this.renderer =  new ImageRenderer(Utils.loadAssetImage("sphere-bullets/0.png"));
        this.velocity = new Vector2D();
        boxCollider = new BoxCollider(15,15);
        this.children.add(boxCollider);
    }

    public void newBullet() {
        Vector2D target = BlueEnemy.ePosition;
        Vector2D bulletVelocity = target.subtract(position).nomalize().multiply(10);
        this.velocity.set(bulletVelocity);
        indentify = 2;
    }
    @Override
    public void run(Vector2D parentPosition) {
        newBullet();
        super.run(parentPosition);
        this.position.addUp(velocity);
    }
}
