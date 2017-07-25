package game.bases;

import game.background.BackGround;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class GameObject {
    public Vector2D position; // Relative
    public Vector2D screenPosition; //Screen
    public  ImageRenderer renderer;
    public BoxCollider boxCollider;
    public int indentify;

    public Vector<GameObject> children;

    private static Vector<GameObject> gameObjects = new Vector<>();
    public  static Vector<GameObject> newGameObjects = new Vector<>();
    public static Vector<GameObject> removeGameObjects = new Vector<>();

    public static void add(GameObject gameObject){
        newGameObjects.add(gameObject);
    }
    public static void renderALL(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            gameObject.render(g2d);
        }
    }
    public static void runall(){
        for (GameObject gameObject : gameObjects){
            gameObject.run(Vector2D.ZERO);
        }
        OverLap.checkOverLap(gameObjects,removeGameObjects);
        gameObjects.removeAll(removeGameObjects);
        removeGameObjects.clear();
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }
    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.renderer = null;
    }

    public void render(Graphics2D g2d){
        if (renderer != null && this.position.x <= BackGround.Width-8){
            renderer.render(g2d,this.screenPosition);
        }
        for (GameObject child : this.children){
            child.render(g2d);
        }
    }

    public void updatePicture() {

    }

    public static void updateAllPicture() {
        for (GameObject gameObject: gameObjects) {
            gameObject.updatePicture();
        }
    }

    public void run(Vector2D parentPosition){
       this.screenPosition = parentPosition.add(position);
       for (GameObject child : children){
           child.run(this.screenPosition);
       }
    }

}
