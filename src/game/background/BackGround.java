package game.background;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class BackGround extends GameObject{
    public static float Width;
    public BackGround() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
        this.renderer.anchor.set(0,1);
        Width = this.renderer.image.getWidth();
    }


    @Override
    public void run(Vector2D parentPosition) {
        move();
    }

    public void move(){
        if ((this.screenPosition.y  - this.renderer.getHeight()) < 0){
            screenPosition.addUp(0, 2);
        }
    }
}
