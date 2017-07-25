package game.player;

import game.Utils;
import game.bases.*;

import static game.background.Settings.frameCounterSpellImage;

/**
 * Created by Nttung PC on 7/11/2017.
 */
public class PlayerSpell extends GameObject{

    ImageRenderer imageRenderer;
    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;


    public PlayerSpell() {
        super();
        this.imageRenderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/0.png"));
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("player-spells/a/1.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("player-spells/a/2.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("player-spells/a/3.png"));
        renderer = imageRenderer;
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        indentify = 2;
    }

    @Override
    public void updatePicture() {
        if (frameCounterSpellImage.run()) {
            if (renderer == imageRenderer) renderer = imageRenderer1;
            else if (renderer == imageRenderer1) renderer = imageRenderer2;
            else if (renderer == imageRenderer2) renderer = imageRenderer3;
            else if (renderer == imageRenderer3) renderer = imageRenderer;
            frameCounterSpellImage.reset();
        }
    }
    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        this.position.addUp(0,-10);
    }


}
