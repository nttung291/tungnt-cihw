package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by Nttung PC on 7/23/2017.
 */
public class EnemyBullet extends GameObject {
    public Vector2D velocity;

    public EnemyBullet() {
        super();
        this.renderer =  new ImageRenderer(Utils.loadAssetImage("enemies/bullets/white.png"));
        this.velocity = new Vector2D();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
    }
}
