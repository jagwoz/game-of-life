package AgentSim;

import java.util.ArrayList;
import java.util.Random;

public class FieldElement {
    private static int number = 0;
    private int agentId;
    private int[] position;
    private GameOfLife board;
    private ArrayList<int[]> neighborsPositions;
    private int status;

    public FieldElement(GameOfLife g, int x, int y){
        Random r = new Random();
        this.status = r.nextInt(2);
        this.board = g;
        this.agentId = ++number;
        this.neighborsPositions = new ArrayList<>();
        updateNeighborsPosition(g, x, y);
        int tmpArray[] = { x, y };
        this.position = tmpArray;
    }

    private void updateNeighborsPosition(GameOfLife g, int x, int y) {
        for(int i = -1; i <= 1; i++){
            if(x + i >= 0 && x + i < g.getWidth())
                for(int j = -1; j <= 1; j++){
                    if(y + j >= 0 && y + j < g.getHeight() && !(i == 0 && j == 0)){
                        this.neighborsPositions.add(new int[]{ x + i, y + j });
                    }
                }
        }
    }

    public void updateStatus(int s){
        this.status = s;
    }

    public int[] getPosition(){
        return position;
    }

    public ArrayList<int[]> getNeighborsPositions(){
        return neighborsPositions;
    }

    public int getStatus(){
        return status;
    }
}
