package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by Nttung PC on 7/19/2017.
 */
public class Bullets extends GameObject {
    public float dx;
    public float dy;

    public Bullets(float dx,float dy,Vector2D position) {
        this.position.set(position);
        renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/green.png"));
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void run() {
        this.position.addUp(2*dx,2*dy);
    }
}
