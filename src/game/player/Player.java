package game.player;

import game.Utils;
import game.bases.Contraints;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.renderer.ImageRenderer;
import game.enemies.BlueEnemy;
import game.inputs.InputManager;
import game.sphere.Spheres;
import tklibs.AudioUtils;

import static game.background.Settings.coolDownCounterPlayer;

/**
 * Created by Nttung PC on 7/11/2017.
 */
public class Player extends GameObject implements PhysicBody{

    Contraints contraints;
    InputManager inputManager;

    Spheres leftSphere;
    Spheres rightSphere;

    public static Player instance;
    public int life = 100;

    boolean spellDisabled;

    BoxCollider boxCollider;

    Vector2D verlocity;
    public Player() {
        this.position = new Vector2D();
        this.verlocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        instance = this;
        leftSphere = new Spheres(-20,0);
        rightSphere = new Spheres(20,0);
        this.children.add(leftSphere);
        this.children.add(rightSphere);
        boxCollider = new BoxCollider(20,30);
        children.add(boxCollider);
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
        explosion();
    }

    private void castSpell() {
        if (inputManager.xPressed){
            if (!spellDisabled) {
                AudioUtils.playMedia("assets/music/sfx/player-shoot.wav");
                PlayerSpell playerSpell = GameObjectPool.recycle(PlayerSpell.class);
                playerSpell.position.set(this.position.add(0, -40));
                spellDisabled = true;
            }
        }
    }

    private void explosion(){
        if (inputManager.zPressed){
            for (GameObject gameObject : gameObjects){
                if (gameObject.getClass() == BlueEnemy.class && gameObject.isActive){
                    Explosion explosion = new Explosion();
                    explosion.position.set(gameObject.position);
                    GameObject.add(explosion);
                    gameObject.setActive(false);
                }
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

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
