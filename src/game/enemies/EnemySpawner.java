package game.enemies;

import game.bases.FrameCounter;
import game.bases.GameObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class EnemySpawner extends GameObject {
    public ArrayList<PinkEnemies>  pinkEnemies;
    public BlackEnemy blackEnemy;

    FrameCounter coolDownCounter;
    FrameCounter frameCounterblackEnemy;
    boolean enemiesDisabled;
    boolean blackEnemyDisabled;

    public EnemySpawner() {
        this.blackEnemy = new BlackEnemy();
        this.pinkEnemies = new ArrayList<>();
        this.coolDownCounter = new FrameCounter(50);
        this.frameCounterblackEnemy = new FrameCounter(500);
        this.blackEnemyDisabled = false;
    }

    public void spawnBlue(){
        if (!enemiesDisabled&&!blackEnemyDisabled){
            PinkEnemies pinkEnemy = new PinkEnemies();
            pinkEnemies.add(pinkEnemy);
            GameObject.add(pinkEnemy);
            enemiesDisabled = true;
        }
    }

    public void coolDown() {
        if(enemiesDisabled){
            boolean status = coolDownCounter.run();
            if (status){
                enemiesDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

    public void spawnBlack(){
        if(!blackEnemyDisabled ){
            GameObject.add(blackEnemy);
            blackEnemyDisabled = true;
        }
    }
    public void remove(){
        Iterator<PinkEnemies>  iterator = pinkEnemies.iterator();
        while(iterator.hasNext()){
            PinkEnemies pinkEnemy = iterator.next();
            if(pinkEnemy.position.x < 0 || pinkEnemy.position.x > 375 || pinkEnemy.position.y > 800 || pinkEnemy.position.y < 0){
                iterator.remove();
            }
        }
    }
    @Override
    public void run() {
        spawnBlue();
        coolDown();
        spawnBlack();
        remove();
    }

}
