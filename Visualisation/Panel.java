package AgentSim.Visualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Panel extends JPanel implements Runnable{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private ArrayList<ArrayList<ArrayList<Integer>>> visualisation;
    private Thread thread;
    private boolean running;
    private int FPS = 10;
    private long targetTime = 1000 / FPS;
    private BufferedImage image;
    private Graphics2D g;
    private Frame frame;

    public Panel(ArrayList<ArrayList<ArrayList<Integer>>> boardVisualisations) {
        super();
        visualisation = boardVisualisations;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void init() throws ExecutionException, InterruptedException {
        image = new BufferedImage(
                WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );
        g = (Graphics2D) image.getGraphics();
        running = true;
        frame = new Frame(visualisation);
    }

    public void run() {
        try {
            init();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long start;
        long elapsed;
        long wait;
        while(running) {
            start = System.nanoTime();
            update();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 1;
            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update(){
        frame.update();
        draw();
        drawToScreen();
    }

    private void draw(){
        frame.draw(g);
    }

    private void drawToScreen() {
        Graphics gDraw = getGraphics();
        gDraw.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        gDraw.dispose();
    }
}

















