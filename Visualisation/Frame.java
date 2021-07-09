package AgentSim.Visualisation;

import java.awt.*;
import java.util.ArrayList;

public class Frame {
    private ArrayList<ArrayList<ArrayList<Integer>>> visualisationsArray;
    private int[][] actualVisualisation;
    private int i;
    private int width;
    private int height;
    private int x;
    private int y;

    public Frame(ArrayList<ArrayList<ArrayList<Integer>>> visualisation){
        this.i = 0;
        this.visualisationsArray = visualisation;
        this.x = visualisation.get(i).size();
        this.y = visualisation.get(i).get(0).size();
        this.width = Panel.WIDTH / y;
        this.height = Panel.HEIGHT / x;
    }

    public void update(){
        if(this.i < this.visualisationsArray.size()){
            ArrayList<ArrayList<Integer>> a = this.visualisationsArray.get(this.i);
            actualVisualisation = new int[x][y];
            for (int j = 0; j<x; j++)
                for (int k=0; k<y; k++)
                    actualVisualisation[j][k] = a.get(j).get(k);
            this.i++;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 255,255));
        g.fillRect(0, 0, Panel.WIDTH, Panel.HEIGHT);
        for(int i=0; i<actualVisualisation.length; i++)
            for(int j=0; j<actualVisualisation[0].length; j++){
                if(actualVisualisation[i][j] == 1){
                    g.setColor(new Color(81, 81, 81));
                }
                else{
                    g.setColor(new Color(255, 255, 255));
                }
                g.fillRect(j * width,i * height, width, height);
                g.setColor(new Color(255, 255,255));
                g.drawRect(j * width,i * height, width, height);
            }
    }
}