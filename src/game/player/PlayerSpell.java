package game.player;

import game.Utils;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.bases.renderer.Animation;
import game.enemies.BlackEnemy;
import game.enemies.BlueEnemy;
import game.enemies.EnemyExplosion;
import tklibs.AudioUtils;

/**
 * Created by Nttung PC on 7/11/2017.
 */
public class PlayerSpell extends GameObject implements PhysicBody{

    private BoxCollider boxCollider;
    public PlayerSpell() {
        this.renderer = new Animation(
                Utils.loadAssetImage("player-spells/a/0.png"),
                Utils.loadAssetImage("player-spells/a/1.png"),
                Utils.loadAssetImage("player-spells/a/2.png"),
                Utils.loadAssetImage("player-spells/a/3.png")
        );
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }
    public void hitEnemy(){
        BlueEnemy hitEnemy  = Physics.bodyInRect(this.boxCollider,BlueEnemy.class);
        BlackEnemy hitBlack = Physics.bodyInRect(this.boxCollider,BlackEnemy.class);
        if (hitBlack!=null){
            hitBlack.life-=1;
            this.isActive = false;
            if (hitBlack.life <=0){
                hitBlack.isActive = false;
            }
        }
        if (hitEnemy != null){
            AudioUtils.playMedia("assets/music/sfx/enemy-explosion.wav");
            EnemyExplosion explosion = GameObjectPool.recycle(EnemyExplosion.class);
            explosion.position.set(hitEnemy.position);
            hitEnemy.isActive = false;
            this.isActive = false;
        }
    }
    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        this.position.addUp(0,-10);
        hitEnemy();
        if (this.position.y < 0){
            this.isActive = false;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
