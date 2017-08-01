package game.player;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderer.Animation;

/**
 * Created by Nttung PC on 7/27/2017.
 */
public class PlayerExplosion extends GameObject{
    private Animation animation;
    public PlayerExplosion() {
        super();
        this.animation = new Animation(5,false,
                Utils.loadAssetImage("players/explosions/0.png"),
                Utils.loadAssetImage("players/explosions/1.png"),
                Utils.loadAssetImage("players/explosions/2.png"),
                Utils.loadAssetImage("players/explosions/3.png"),
                Utils.loadAssetImage("players/explosions/4.png"),
                Utils.loadAssetImage("players/explosions/5.png"),
                Utils.loadAssetImage("players/explosions/6.png"),
                Utils.loadAssetImage("players/explosions/7.png")
        );
        this.renderer = animation;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.animation.isFinished()){
            this.isActive = false;
        }
    }
}
