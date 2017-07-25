package game.background;

import game.bases.FrameCounter;

/**
 * Created by Nttung PC on 7/23/2017.
 */
public class Settings {
    public static int gamePlayWidth = 600;
    public static int gamePlayHeight = 800;
    public static int windownWidth = 384;
    public static int windownHeigh = 3109;
    public static FrameCounter  frameCounterStillStand = new FrameCounter(50);
    public static FrameCounter frameCounterBlueEnemy = new FrameCounter(18);;
    public static FrameCounter frameCounterblackEnemy = new FrameCounter(500);;
    public static FrameCounter coolDownCounterPlayer = new FrameCounter(10);
    public static FrameCounter frameCounterSpellImage = new FrameCounter(5);;
    public static FrameCounter frameCounterBlueImage = new FrameCounter(4);
    public static FrameCounter coolDownSpellSphereCounter = new FrameCounter(50);
    public static int playerLife = 100;
    public static int blackLife = 100;

}

