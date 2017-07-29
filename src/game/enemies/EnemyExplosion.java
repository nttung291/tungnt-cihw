package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderer.Animation;

/**
 * Created by Nttung PC on 7/27/2017.
 */
public class EnemyExplosion extends GameObject{
    public EnemyExplosion() {
        super();
        this.renderer = new Animation(
                Utils.loadAssetImage("enemies/explosion/0.png"),
                Utils.loadAssetImage("enemies/explosion/1.png"),
                Utils.loadAssetImage("enemies/explosion/2.png"),
                Utils.loadAssetImage("enemies/explosion/3.png"),
                Utils.loadAssetImage("enemies/explosion/4.png"),
                Utils.loadAssetImage("enemies/explosion/5.png"),
                Utils.loadAssetImage("enemies/explosion/6.png"),
                Utils.loadAssetImage("enemies/explosion/7.png")
        );
    }

    public void norender(){
        if (renderer.getImageIndex()){
            this.setActive(false);
            renderer.setImageIndex();
        }
    }
    @Override
    public void run(Vector2D parentPosition) {
        norender();
        super.run(parentPosition);
    }
}
