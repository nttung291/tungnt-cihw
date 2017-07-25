package game.bases;

import java.util.Vector;

import static game.background.Settings.blackLife;

/**
 * Created by Nttung PC on 7/24/2017.
 */
public class OverLap extends GameObject{
    public static void checkOverLap(Vector<GameObject> gameObjects,Vector<GameObject> removeGameObjects){
        for(int i=0;i<gameObjects.size();i++){
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject g1 = gameObjects.get(i);
                GameObject g2 = gameObjects.get(j);
                if (g1.boxCollider!=null && g2.boxCollider!=null){
                    if(g1.boxCollider.collideWith(g2.boxCollider)){
                       if(g1.indentify != g2.indentify){
                           if (g1.indentify != 4&&g2.indentify != 4){
                               removeGameObjects.add(g1);
                               removeGameObjects.add(g2);
                           }
                           else{
                               if(g1.indentify == 2) removeGameObjects.add(g1);
                               else removeGameObjects.add(g2);
                               blackLife-=1;
                               System.out.println(blackLife);
                               if (blackLife == 0){
                                   removeGameObjects.add(g1);
                                   removeGameObjects.add(g2);
                               }
                           }
                       }
                    }
                }
            }
        }
    }
}
