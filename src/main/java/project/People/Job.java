package project.People;

import java.util.ArrayList;

public class Job {

    private final String name;
    private final int weeklyPay;

    public Job(String name, int weeklyPay){
        this.name = name;
        this.weeklyPay = weeklyPay;
    }

    public String getName(){
        return this.name;
    }

    public int getWeeklyPay(){
        return this.weeklyPay;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Job job)){
            return false;
        }
        if(obj == this){
            return true;
        }
        String name = job.getName();
        int weeklyPay = job.getWeeklyPay();
        return this.name.equals(name) && this.weeklyPay == weeklyPay;
    }

    public static ArrayList<Job> instantiateJobs(){
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(new Job("Orphanage Worker", 10));
        jobs.addLast(new Job("Farmer", 15));
        jobs.addLast(new Job("Miner", 20));
        jobs.addLast(new Job("Leader", 0));
        jobs.addLast(new Job("Adventurer",0));
        jobs.addLast(new Job("Construction Worker",15));
        jobs.addLast(new Job("Soldier",15));
        jobs.addLast(new Job("Blacksmith",15));
        jobs.addLast(new Job("Unemployed",0));
        return jobs;
    }

}
