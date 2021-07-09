package AgentSim;

import AgentSim.SimEngine.SimEvent;
import AgentSim.SimEngine.SimManager;

public class LifeUpdateEvent extends SimEvent {
    public LifeUpdateEvent(SimManager m, double r, int s) {
        super(m, r, s);
    }

    public void stateChange(){
        getSimMgr().getBoard().changeStatus(getSimMgr().getCurrentSimTime());
        getSimMgr().addClientEnterEvent(getSimMgr().getCurrentSimTime() + 1, 1);
    }
}
