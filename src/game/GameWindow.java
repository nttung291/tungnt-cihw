package game;

import game.background.BackGround;
import game.background.Settings;
import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.BlackEnemy;
import game.enemies.EnemySpawner;
import game.inputs.InputManager;
import game.player.Player;
import javafx.scene.media.MediaPlayer;
import tklibs.AudioUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Nttung PC on 7/9/2017.
 */
public class GameWindow extends JFrame{
    public BackGround backGround;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphic2D;

    InputManager inputManager = new InputManager();

    public GameWindow() {
        setupWindow();
        addBackGround();
        addPlayer();
        setupImput();
        addEnemySpwaner();
        this.setVisible(true);
        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();
    }

    public void addEnemySpwaner(){
        GameObject.add(new EnemySpawner());
    }

    public void addBackGround(){
        backGround = new BackGround();
        backGround.screenPosition.y = this.getHeight();
        GameObject.add(backGround);
    }

    private void addPlayer(){
        Player player = new Player();
        player.setContraints(new Contraints(50,this.getHeight()-30,30,Settings.windownWidth-30));
        player.setInputManager(inputManager);
        player.position.set(Settings.windownWidth/2,this.getHeight()-50);
        GameObject.add(player);
    }
    private void setupImput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
               inputManager.keyReleased(e);
            }
        });
    }

    long lastUpdateTime;
    public void loop(){
        AudioUtils.initialize();
        MediaPlayer mediaPlayer = AudioUtils.playMedia("assets/music/1.mp3");
        mediaPlayer.play();
        while(true){
            mediaPlayer.setAutoPlay(true);
            long curentTime = System.currentTimeMillis();
            if (curentTime - lastUpdateTime > 17){
                lastUpdateTime = curentTime;
                Run();
                Render();
            }
            if (Player.instance.life == 0 || BlackEnemy.life ==0){
                break;
            }
        }
    }

    private void Run() {
        GameObject.runall();
    }

    private void Render() {
        backBufferGraphic2D.setColor(Color.black);
        backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());
        GameObject.renderALL(backBufferGraphic2D);
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(backBufferImage,0,0,null);
    }

    private void setupWindow() {
        this.setSize(Settings.gamePlayWidth,Settings.gamePlayHeight);
        this.setResizable(false);
        this.setTitle("Touhou - Remade by tungNT");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                super.windowClosing(e);
            }
        });
    }

    @Override
    public void paint(Graphics g) {

    }
}
