import game.GameWindow;
import game.bases.physics.BoxCollider;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        BoxCollider box1 = new BoxCollider(10,20);
//        BoxCollider box2  = new BoxCollider(20,10);
//        box1.screenPosition.set(200,200);
//        box1.screenPosition.set(200,200);
//        System.out.println(box1.collideWith(box2));
        GameWindow gameWindow = new GameWindow();
        gameWindow.loop();
    }
}

