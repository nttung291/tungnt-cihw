package game.background;

import game.Utils;
import game.bases.GameObject;
import game.bases.renderer.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class BackGround extends GameObject{
    private ImageRenderer imageRenderer;
    public BackGround() {
        super();
        imageRenderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
        this.imageRenderer.anchor.set(0,1);
        this.renderer = imageRenderer;
    }


    @Override
    public void run(Vector2D parentPosition) {
        move();
    }

    public void move(){
        if ((this.screenPosition.y  - this.imageRenderer.getHeight()) < 0){
            screenPosition.addUp(0, 2);
        }
    }
}
