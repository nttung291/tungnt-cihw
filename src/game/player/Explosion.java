package game.player;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderer.Animation;

/**
 * Created by Nttung PC on 7/27/2017.
 */
public class Explosion extends GameObject{
    public Explosion() {
        super();
        this.renderer = new Animation(
                Utils.loadAssetImage("players/explosions/0.png"),
                Utils.loadAssetImage("players/explosions/1.png"),
                Utils.loadAssetImage("players/explosions/2.png"),
                Utils.loadAssetImage("players/explosions/3.png"),
                Utils.loadAssetImage("players/explosions/4.png"),
                Utils.loadAssetImage("players/explosions/5.png"),
                Utils.loadAssetImage("players/explosions/6.png"),
                Utils.loadAssetImage("players/explosions/7.png")
        );
    }

    public void norender(){
        if (renderer.getImageIndex()){
            this.setActive(false);
        }
    }
    @Override
    public void run(Vector2D parentPosition) {
        norender();
        super.run(parentPosition);
    }
}
