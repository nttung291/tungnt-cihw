package game.background;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class BackGround extends GameObject{

    public BackGround() {
        position = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
    }

    public void setPosition(float x,float y){
        position.x = x;
        position.y = y;
    }

    @Override
    public void run() {
        move();
    }

    public void move(){
        if(position.y <= 0){
            position.addUp(0,3);
        }
    }
}
