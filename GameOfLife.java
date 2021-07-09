package AgentSim;

import java.util.*;

public class GameOfLife {
    private ArrayList<FieldElement> board;
    private int boardX;
    private int boardY;
    private int[][] boardVisualisation;

    public GameOfLife(int x, int y){
        this.boardVisualisation = new int[x][y];
        this.boardX = x;
        this.boardY = y;
        this.board = new ArrayList<>();
        updateBoard();
    }

    private void updateBoard() {
        for(int i = 0; i< boardX; i++){
            for(int j = 0; j< boardY; j++){
                FieldElement a = new FieldElement(this, i, j);
                board.add(a);
            }
        }
        updateBoardVisualisation();
    }

    private void updateBoardVisualisation() {
        for(FieldElement a : board){
            this.boardVisualisation[a.getPosition()[0]][a.getPosition()[1]] = a.getStatus();
        }
    }

    public void changeStatus(double simTime){
        ArrayList<FieldElement> tempBoard = board;
        for(FieldElement a : tempBoard){
            int nCount = 0;
            for(int[] nPos : a.getNeighborsPositions()){
                if(this.boardVisualisation[nPos[0]][nPos[1]] == 1) nCount++;
            }
            if(nCount == 3 && a.getStatus() == 0){
                a.updateStatus(1);
            }
            else if((nCount < 2 || nCount > 3) && a.getStatus() == 1){
                a.updateStatus(0);
            }
        }
        board = tempBoard;
        updateBoardVisualisation();
    }

    public int getWidth(){ return boardX; }
    public int getHeight(){ return boardY; }
    public int[][] getBoardVisualisation(){ return boardVisualisation; }
}
