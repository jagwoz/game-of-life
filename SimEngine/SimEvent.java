package AgentSim.SimEngine;

public abstract class SimEvent {
    private double runTime;
    private int simPriority;
    private SimManager simMgr;

    public SimEvent(SimManager m, double r, int s){
        this.simMgr = m;
        this.simPriority = s;
        this.runTime = r;
        m.registerSimEvent(this);
    }

    public void stateChange(){}
    public int getSimPriority(){ return simPriority;}
    public double getRunTime(){ return runTime;}
    public SimManager getSimMgr(){ return simMgr;}
}
