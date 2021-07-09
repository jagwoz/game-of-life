package AgentSim.SimEngine;

import AgentSim.LifeUpdateEvent;
import AgentSim.GameOfLife;
import AgentSim.Visualisation.Panel;

import javax.swing.*;
import java.util.ArrayList;

public class SimManager extends JPanel {
    private double currentSimTime;
    private double simEndTime;
    private SimManager simMgr;
    private SimCalendar simCalendar;
    private LifeUpdateEvent lifeUpdateEvent;
    private GameOfLife gameOfLife;
    private boolean simulationStarted;
    ArrayList<ArrayList<ArrayList<Integer>>> boardVisualisations;

    public SimManager(int x, int y){
        this.simMgr = this;
        this.simCalendar = new SimCalendar();
        this.gameOfLife = new GameOfLife(x, y);
        simulationStarted = false;
        boardVisualisations = new ArrayList<>();
    }

    public void registerSimEvent(SimEvent s){
        simCalendar.add(s);
    }

    public void startSimulation() {
        simulationStarted = true;
        this.lifeUpdateEvent = new LifeUpdateEvent(simMgr, 1, 1);
        updateVisualisation();
        nextEvent();
    }

    private void updateVisualisation() {
        int[][] tmp = gameOfLife.getBoardVisualisation();
        ArrayList<ArrayList<Integer>> toDraw = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            ArrayList<Integer> toDrawRow = new ArrayList<>();
            for (int j = 0; j < tmp[0].length; j++) {
                toDrawRow.add(tmp[i][j]);
            }
            toDraw.add(toDrawRow);
        }
        boardVisualisations.add(toDraw);
    }

    public void stopSimulation(){
        simulationStarted = false;
    }

    public void setIterations(double t){
       simEndTime = t;
    }

    public void nextEvent(){
        SimEvent actualEvent = simCalendar.getFirst();

        updateVisualisation();

        if(actualEvent.getRunTime() >= simEndTime + 1) {
            stopSimulation();
            JFrame window = new JFrame("GameOfLife");
            window.setContentPane(new Panel(boardVisualisations));
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.pack();
            window.setVisible(true);
            return;
        }

        currentSimTime = actualEvent.getRunTime();
        actualEvent.stateChange();
        nextEvent();
    }

    public GameOfLife getBoard(){return gameOfLife;}
    public double getCurrentSimTime(){return currentSimTime;}
    public void addClientEnterEvent(double t, int p){ lifeUpdateEvent = new LifeUpdateEvent(simMgr, t, p);}
}
