package game.player;

import game.Utils;
import game.bases.*;
import game.inputs.InputManager;
import game.sphere.Spheres;

import java.util.ArrayList;

import static game.background.Settings.coolDownCounterPlayer;

/**
 * Created by Nttung PC on 7/11/2017.
 */
public class Player extends GameObject{

    Contraints contraints;
    InputManager inputManager;

    Spheres leftSphere;
    Spheres rightSphere;
    ArrayList<PlayerSpell> playerSpells;

    public static Player instance;


    boolean spellDisabled;

    Vector2D verlocity;
    public Player() {
        this.playerSpells = new ArrayList<>();
        this.position = new Vector2D();
        this.verlocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        instance = this;
        leftSphere = new Spheres(-20,0);
        rightSphere = new Spheres(20,0);
        this.children.add(leftSphere);
        this.children.add(rightSphere);
        indentify = 1;
    }


    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        castSpell();
        coolDown();
    }

    private void castSpell() {
        if (inputManager.xPressed){
            if (!spellDisabled) {
                PlayerSpell playerSpell = new PlayerSpell();
                playerSpell.position.set(this.position.add(0, -40));
                GameObject.add(playerSpell);
                playerSpells.add(playerSpell);
                spellDisabled = true;
            }
        }
    }


    private void move() {
        this.verlocity.set(0,0);
        if (inputManager.leftPressed){
            this.verlocity.x-=5;
        }
        if (inputManager.rightPressed){
            this.verlocity.x+=5;
        }
        if (inputManager.upPressed){
            this.verlocity.y -=5;
        }
        if (inputManager.downPressed){
            this.verlocity.y +=5;
        }
        this.position.addUp(verlocity);
        this.contraints.make(this.position);
    }

    @Override
    public void updatePicture() {

    }

    public void coolDown() {
        if(spellDisabled){
            boolean status = coolDownCounterPlayer.run();
            if (status){
                spellDisabled = false;
                coolDownCounterPlayer.reset();
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        leftSphere.setInputManager(inputManager);
        rightSphere.setInputManager(inputManager);
        this.inputManager = inputManager;
    }
}
