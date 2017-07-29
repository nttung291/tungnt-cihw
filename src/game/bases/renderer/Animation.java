package game.bases.renderer;

import game.bases.FrameCounter;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nttung PC on 7/27/2017.
 */
public class Animation implements Renderer{
    private List<BufferedImage> images;
    public int imageIndex=0;
    private FrameCounter frameCounter;
    public Animation(int delayFrame,BufferedImage... imagesArr){
        this.images = Arrays.asList(imagesArr);
        frameCounter = new FrameCounter(delayFrame);
    }

    public Animation(BufferedImage... imageArr) {
        this(5,imageArr);
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        if(frameCounter.run()){
            changeIndex();
            frameCounter.reset();
        }
        BufferedImage image = images.get(imageIndex);
        g2d.drawImage(image, (int) (position.x - image.getWidth()/2),
                (int) (position.y - image.getHeight()/2),
                null);
    }

    @Override
    public boolean getImageIndex() {
        return imageIndex >= images.size()-1;
    }

    private void changeIndex() {
        imageIndex++;
        if (imageIndex >= images.size()){
            imageIndex = 0;
        }
    }

    public void setImageIndex(){
        imageIndex = 0;
    }

}
