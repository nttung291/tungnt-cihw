package game.sphere;

import game.Utils;
import game.bases.*;
import game.bases.renderer.ImageRenderer;
import game.inputs.InputManager;

import java.util.ArrayList;

import static game.background.Settings.coolDownSpellSphereCounter;

/**
 * Created by Nttung PC on 7/24/2017.
 */
public class Spheres extends GameObject{
    Vector2D velocity;
    public ArrayList< SphereBullet> sphereBullets;

    boolean spellDisabled;
    InputManager inputManager;

    public Spheres(float x, float y) {
        super();
        position.set(x,y);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("sphere/0.png"));
        velocity = new Vector2D();
        sphereBullets = new ArrayList<>();
    }

    public void coolDown() {
        if(spellDisabled){
            boolean status = coolDownSpellSphereCounter.run();
            if (status){
                spellDisabled = false;
                coolDownSpellSphereCounter.reset();
            }
        }
    }
    private void castSpell() {
        if (inputManager.xPressed){
            if (!spellDisabled) {
                SphereBullet sphereBullet = new SphereBullet();
                sphereBullet.position.set(this.screenPosition.add(0,-15));
                GameObject.add(sphereBullet);
                sphereBullets.add(sphereBullet);
                spellDisabled = true;
            }
        }
    }
    @Override
    public void run(Vector2D parentPosition) {
        castSpell();
        coolDown();
        super.run(parentPosition);
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
