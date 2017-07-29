package game.enemies;

import game.Utils;
import game.background.Settings;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.bases.renderer.ImageRenderer;
import game.player.Player;

/**
 * Created by Nttung PC on 7/23/2017.
 */
public class EnemyBullet extends GameObject implements PhysicBody{
    public Vector2D velocity;
    public ImageRenderer imageRenderer1;
    public ImageRenderer imageRenderer2;
    public ImageRenderer imageRenderer3;

    BoxCollider boxCollider;
    public EnemyBullet() {
        super();
        imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/white.png"));
        imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/cyan.png"));
        imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/red.png"));
        this.renderer = imageRenderer1;
        this.velocity = new Vector2D();
        boxCollider = new BoxCollider(14,14);
        children.add(boxCollider);
    }

    public void hitPlayer(){
        Player hitPlayer  = Physics.bodyInRect(this.boxCollider,Player.class);
        if (this.isActive && hitPlayer!=null){
            hitPlayer.life-=1;
            this.isActive = false;
            if (hitPlayer.life <=0){
                hitPlayer.isActive = false;
            }
        }
    }
    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (screenPosition.x < 0 || screenPosition.x > Settings.windownWidth-10 || screenPosition.y > Settings.gamePlayHeight){
            this.setActive(false);
        }else{
            this.position.addUp(velocity);
        }
        hitPlayer();
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
