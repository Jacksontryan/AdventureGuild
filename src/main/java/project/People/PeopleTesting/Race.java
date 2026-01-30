package project.People.PeopleTesting;

import project.People.Person;

import java.util.ArrayList;

public class Race{
    private final String name;
    private final int raceNum;
    private final int averageLifespan;
    private final int reproductionAge;
    private final int gestationPeriod;

    public Race(String name, int raceNum, int averageLifespan, int reproductionAge, int gestationPeriod) {
        this.name = name;
        this.raceNum = raceNum;
        this.averageLifespan = averageLifespan;
        this.reproductionAge = reproductionAge;
        this.gestationPeriod = gestationPeriod;
    }

    public String getName(){
        return this.name;
    }


    public int getRaceNum(){
        return this.raceNum;
    }

    public int getAverageLifespan(){
        return this.averageLifespan;
    }

    public int getReproductionAge(){
        return this.reproductionAge;
    }

    public int getGestationPeriod(){
        return this.gestationPeriod;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Race race = (Race) o;
        return this.raceNum == race.raceNum && this.name.equals(race.name);
    }

    public static ArrayList<Race> instantiateRaces(){

        Race human = new Race("Human", 100, 3744,936, 40);
        Race dwarf = new Race("Dwarf", 110, 4160, 1040, 45);
        Race elf = new Race("Elf", 120, 7800,2028, 80);
        Race orc = new Race("Orc", 130, 3120,832, 30);

        ArrayList<Race> races = new ArrayList<>();

        races.add(human);
        races.addLast(dwarf);
        races.addLast(elf);
        races.addLast(orc);

        return races;
    }
}
