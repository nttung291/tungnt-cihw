package game.bases;

import game.bases.physics.PhysicBody;
import game.bases.physics.Physics;
import game.bases.renderer.Renderer;
import game.enemies.BlackEnemy;
import game.player.Player;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class GameObject {
    public Vector2D position; // Relative
    public Vector2D screenPosition; //Screen
    public Renderer renderer;
    public boolean isActive;

    public Vector<GameObject> children;

    public static Vector<GameObject> gameObjects = new Vector<>();
    public  static Vector<GameObject> newGameObjects = new Vector<>();

    public static void add(GameObject gameObject){
        newGameObjects.add(gameObject);
        if (gameObject instanceof PhysicBody){
            Physics.add((PhysicBody)gameObject);
        }
    }
    public static void renderALL(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive)
                gameObject.render(g2d);
        }
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("serif",Font.BOLD,20));
        g2d.drawString("Boss Health :" + BlackEnemy.life,400,100);
        g2d.drawString("Player Health : "+ Player.instance.life,400,150);
        if (Player.instance.life == 0){
            g2d.setFont(new Font("serif",Font.BOLD,30));
            g2d.drawString("You Lose",200,500);
        }
        if (BlackEnemy.life == 0){
            g2d.setFont(new Font("serif",Font.BOLD,30));
            g2d.drawString("You Win",150,500);
        }
    }
    public static void runall(){
        for (GameObject gameObject : gameObjects){
            gameObject.run(Vector2D.ZERO);
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }
    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.renderer = null;
        isActive = true;
    }

    public void render(Graphics2D g2d){
        if (renderer != null){
            renderer.render(g2d,this.screenPosition);
        }
        for (GameObject child : this.children){
            child.render(g2d);
        }
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean acvtive) {
        this.isActive = acvtive;
    }


    public void run(Vector2D parentPosition){
       this.screenPosition = parentPosition.add(position);
       for (GameObject child : children){
           child.run(this.screenPosition);
       }
    }

    public void  refresh(){
        isActive = true;

    }
}
