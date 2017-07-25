package game.enemies;

import game.bases.GameObject;
import game.bases.Vector2D;

import static game.background.Settings.frameCounterBlueEnemy;
import static game.background.Settings.frameCounterblackEnemy;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class EnemySpawner extends GameObject {
    public BlueEnemy blueEnemies;
    public BlackEnemy blackEnemy;
    public static Vector2D ePosition;


    boolean enemiesDisabled;
    boolean blackEnemyDisabled;

    public EnemySpawner() {
        this.blackEnemy = new BlackEnemy();
        this.blackEnemyDisabled = false;
        blueEnemies = new BlueEnemy();
        ePosition = new Vector2D();
    }

    public void spawnBlue(){
        if (!enemiesDisabled&&!blackEnemyDisabled){
            blueEnemies = new BlueEnemy();
            GameObject.add(blueEnemies);
            enemiesDisabled = true;
        }
    }

    public void coolDown() {
        if(enemiesDisabled){
            boolean status = frameCounterBlueEnemy.run();
            if (status){
                enemiesDisabled = false;
                frameCounterBlueEnemy.reset();
            }
        }
    }

    public void spawnBlack(){
        if(!blackEnemyDisabled && frameCounterblackEnemy.run()){
            GameObject.add(blackEnemy);
            blackEnemyDisabled = true;
        }
    }
    @Override
    public void run(Vector2D parentPosition) {
        spawnBlue();
        coolDown();
        spawnBlack();
    }
}
