package project.People.PeopleTesting;

import project.People.Ageable;

import java.util.Random;

public class Time implements Ageable {

    private int year;
    private int week;

    public Time(){
        Random rand = new Random();
        this.year = rand.nextInt(700);
        this.week = rand.nextInt(52);
    }

    public Season getSeason(){
        if(this.week > 8 && this.week <= 21){
            return new Season("Spring",0);
        }else if(this.week > 21 && this.week <= 34){
            return new Season("Summer",1);
        }else if(this.week > 34 && this.week <= 47){
            return new Season("Fall",2);
        }else{
            return new Season("Winter",3);
        }
    }

    @Override
    public void age() {

        this.week++;

        if(this.week >= 52){
            this.year++;
            this.week -= 52;
        }

    }

    @Override
    public void ageYear() {
        for (int i = 0; i < 52; i++){
            age();
        }
    }

    @Override
    public int getAge() {
        return this.year * 52 + this.week;
    }

    @Override
    public int getAgeInYears() {
        return this.year;
    }

    public static class Season{

        private String name;
        private double averageTemp;//fuck farenheit and celcius. We're going Kelvin

        public Season(String name, double averageTemp) {
            this.name = name;
            this.averageTemp = averageTemp;
        }

        public String getName() {
            return this.name;
        }

        public double getAverageTemp() {
            return this.averageTemp;
        }

    }

}
