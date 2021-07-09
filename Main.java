package AgentSim;

import AgentSim.SimEngine.SimManager;
import javax.swing.JPanel;

public class Main extends JPanel{
    public static void main(String[] args){
        SimManager simMgr = new SimManager(50, 50);
        simMgr.setIterations(100);
        simMgr.startSimulation();
    }
}
