package AgentSim.SimEngine;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SimCalendar {
    private PriorityQueue<Double> simEventQueue;
    private ArrayList<SimEvent> simEventList;

    public SimCalendar(){
        this.simEventList = new ArrayList<>();
        this.simEventQueue = new PriorityQueue<>();
    }

    public void add(SimEvent s){
        simEventList.add(s);
        simEventQueue.add(s.getRunTime());
    }

    public SimEvent getFirst(){
        int index = 0;
        int priority = Integer.MAX_VALUE;
        double simTime = simEventQueue.poll();
        for(int i=0; i<simEventList.size(); i++){
            if(simTime == simEventList.get(i).getRunTime() && priority > simEventList.get(i).getSimPriority()){
                index = i;
                priority = simEventList.get(i).getSimPriority();
            }
        }
        return simEventList.remove(index);
    }
}
