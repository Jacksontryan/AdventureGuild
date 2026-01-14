package project;

import java.util.ArrayList;

public class EventPicker<E> {

    private E[] events;
    private double[] probability;

    public EventPicker(E[] events, double[] probability) {
        this.events = events;
        this.probability = probability;
        double totalProbability = 0.0;
        for (int i = 0; i < this.probability.length; i++) {
            totalProbability += this.probability[i];
        }
        if (totalProbability < .99 || totalProbability > 1.01) {
            throw new IllegalArgumentException();
        }
    }

    public EventPicker(E[] events) {
        this.events = events;
        this.probability = new double[events.length];
        for (int i = 0; i < events.length; i++) {
            probability[i] = 1.0 /  events.length;
        }
    }

    public E pick(){

        double pick = Math.random();
        System.out.println(pick);
        double floor = 0.0;
        int count = -1;

        while(floor < pick){
            count++;
            floor += probability[count];
        }

        return events[count];
    }

}
