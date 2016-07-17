/**
 * Created by Rahul Basu on 7/18/2016.
 */
import javax.swing.*;
import java.awt.*;

public class Buffered_Anim_Driver
{
    public static void main(String args[])
    {
        Buffered_Anim_Driver bad = new Buffered_Anim_Driver();
        bad.run();
    }

    private Animation a;
    private BufferedScreen bs;
    private Image bg;

    private static final DisplayMode modes1[] = {
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,32,0),
            new DisplayMode(640,480,32,0),
            new DisplayMode(640,480,32,0),
            new DisplayMode(640,480,32,0)
    }; //storing 6 (very common) display modes in an arraylist

    public void loadImages()
    {
        bg = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Shia.jpg").getImage();
        Image faceJens1 = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Jens1.jpg").getImage();
        Image faceJens2 = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Jens2.jpg").getImage();
        Image faceLeo1 = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Leo1.jpg").getImage();
        Image faceLeo2 = new ImageIcon("C:\\Users\\Rahul Basu\\Downloads\\New Downloads\\Leo2.jpg").getImage();

        a = new Animation();
        a.addScene(faceJens1,250);
        a.addScene(faceJens2,250);
        a.addScene(faceLeo1,250);
        a.addScene(faceLeo2,250);
    }

    public void run()
    {
        bs = new BufferedScreen();
        try
        {
           DisplayMode displayMode = bs.findFirstCompatibleMode(modes1);
            bs.setFullScreen(displayMode);
            loadImages();
            playMovie();
        }
        finally
        {
            bs.restoreScreen();
        }
    }

    //to play the movie
    public void playMovie()
    {
        long startingTime = System.currentTimeMillis();
        long cumulTime = startingTime;

        while (cumulTime - startingTime < 6000) //keep the animation running for 5 seconds (for now)
        // total time is subtracted from the starting time of that particular movie loop
        {
            long timePassed = System.currentTimeMillis() - cumulTime;
            cumulTime += timePassed; //keeps track of the total time
            a.update(timePassed);

            Graphics2D g = bs.getGraphics();
            draw(g);
            g.dispose();
            bs.update();
            try
            {
                Thread.sleep(20);
            }
            catch (Exception ex){}
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(bg, 50, 50, null); //for the background
        g.drawImage(a.getImage(), 30, 30, null); //gets the current image from the animation class
    }
}
