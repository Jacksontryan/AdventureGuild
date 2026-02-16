package project.People.PeopleTesting;

import project.People.Ageable;
import project.People.Race;
import project.People.Sex;

import java.util.ArrayList;
import java.util.Random;

public class Population implements Ageable {

    private ArrayList<Person> people;

    private long numDead;

    private double growthPercentage;
    private int age = 0;

    private ArrayList<Person> newPeopleThisWeek = new ArrayList<>();
    private ArrayList<Person> diedThisWeek = new ArrayList<>();
    private ArrayList<Person> starving = new ArrayList<>();

    private ArrayList<Race> Races = Race.instantiateRaces();

    private int[] statistics = new int[25];

    public Population(int numHumans, int numDwarves, int numElves, int numOrcs){

        Random rand = new Random();

        this.people = new ArrayList<>();
        this.numDead = 0;
        this.growthPercentage = 0.0;
        this.age = 0;

        for(int i = 0; i < numHumans; i++){
            people.add(new Person(Races.getFirst(),rand.nextInt(Races.getFirst().getAverageLifespan())));
        }for(int i = 0; i < numDwarves; i++){
            people.add(new Person(Races.get(1),rand.nextInt(Races.get(1).getAverageLifespan())));
        }for(int i = 0; i < numElves; i++){
            people.add(new Person(Races.get(2),rand.nextInt(Races.get(2).getAverageLifespan())));
        }for(int i = 0; i < numOrcs; i++){
            people.add(new Person(Races.get(3),rand.nextInt(Races.get(3).getAverageLifespan())));
        }

    }

    public ArrayList<Person> getPeople(){
        return people;
    }

    public long getNumDead(){
        return numDead;
    }

    public double getGrowthPercentage(){
        return growthPercentage;
    }

    public ArrayList<Person> getNewPeopleThisWeek(){
        return newPeopleThisWeek;
    }

    public ArrayList<Person> getDiedThisWeek(){
        return diedThisWeek;
    }

    public ArrayList<Person> getStarving(){
        return starving;
    }

    public int getNumHumanFemaleChildren(){
        return statistics[0];
    }

    public int getNumHumanMaleChildren(){
        return statistics[1];
    }

    public int getNumHumanChildren(){
        return getNumHumanFemaleChildren() + getNumHumanMaleChildren();
    }

    public int getNumHumanFemaleAdults(){
        return statistics[2];
    }

    public int getNumHumanMaleAdults(){
        return statistics[3];
    }

    public int getNumHumanAdults(){
        return getNumHumanFemaleAdults() + getNumHumanMaleAdults();
    }

    public int getNumHumanFemaleElderly(){
        return statistics[4];
    }

    public int getNumHumanMaleElderly(){
        return statistics[5];
    }

    public int getNumHumanElderly(){
        return getNumHumanFemaleElderly() + getNumHumanMaleElderly();
    }

    public int getNumHumans(){
        return getNumHumanChildren() + getNumHumanAdults() + getNumHumanElderly();
    }

    public int getNumDwarfFemaleChildren(){
        return statistics[6];
    }

    public int getNumDwarfMaleChildren(){
        return statistics[7];
    }

    public int getNumDwarfChildren(){
        return getNumDwarfFemaleChildren() + getNumDwarfMaleChildren();
    }

    public int getNumDwarfFemaleAdults(){
        return statistics[8];
    }

    public int getNumDwarfMaleAdults(){
        return statistics[9];
    }

    public int getNumDwarfAdults(){
        return getNumDwarfFemaleAdults() + getNumDwarfMaleAdults();
    }

    public int getNumDwarfFemaleElderly(){
        return statistics[10];
    }

    public int getNumDwarfMaleElderly(){
        return statistics[11];
    }

    public int getNumDwarfElderly(){
        return getNumDwarfFemaleElderly() + getNumDwarfMaleElderly();
    }

    public int getNumDwarves(){
        return getNumDwarfChildren() + getNumDwarfAdults() + getNumDwarfElderly();
    }

    public int getNumElfFemaleChildren(){
        return statistics[12];
    }

    public int getNumElfMaleChildren(){
        return statistics[13];
    }

    public int getNumElfChildren(){
        return getNumElfFemaleChildren() + getNumElfMaleChildren();
    }

    public int getNumElfFemaleAdults(){
        return statistics[14];
    }

    public int getNumElfMaleAdults(){
        return statistics[15];
    }

    public int getNumElfAdults(){
        return getNumElfFemaleAdults() + getNumElfMaleAdults();
    }

    public int getNumElfFemaleElderly(){
        return statistics[16];
    }

    public int getNumElfMaleElderly(){
        return statistics[17];
    }

    public int getNumElfElderly(){
        return getNumElfFemaleElderly() + getNumElfMaleElderly();
    }

    public int getNumElves(){
        return getNumElfChildren() + getNumElfAdults() + getNumElfElderly();
    }

    public int getNumOrcFemaleChildren(){
        return statistics[18];
    }

    public int getNumOrcMaleChildren(){
        return statistics[19];
    }

    public int getNumOrcChildren(){
        return getNumOrcFemaleChildren() + getNumOrcMaleChildren();
    }

    public int getNumOrcFemaleAdults(){
        return statistics[20];
    }

    public int getNumOrcMaleAdults(){
        return statistics[21];
    }

    public int getNumOrcAdults(){
        return getNumOrcFemaleAdults() + getNumOrcMaleAdults();
    }

    public int getNumOrcFemaleElderly(){
        return statistics[22];
    }

    public int getNumOrcMaleElderly(){
        return statistics[23];
    }

    public int getNumOrcElderly(){
        return getNumOrcFemaleElderly() + getNumOrcMaleElderly();
    }

    public int getNumOrcs(){
        return getNumOrcChildren() + getNumOrcAdults() + getNumOrcElderly();
    }

    @Override
    public void age() {

        newPeopleThisWeek.clear();
        diedThisWeek.clear();

        int initialSize = people.size();

        for(Person p : people){
            p.age();
            if(!p.getNewChildren().isEmpty() && p.isAlive()){
                newPeopleThisWeek.addAll(p.getNewChildren());
            }else if(!p.isAlive()){
                diedThisWeek.add(p);
            }
        }

        this.people.removeAll(diedThisWeek);
        this.people.addAll(newPeopleThisWeek);

        this.numDead += diedThisWeek.size();

        int finalSize = people.size();

        growthPercentage = 1.0 * (finalSize - initialSize) / initialSize;

        statistics = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//num human child females, num human child males, num human adult females, num human adult males, num human elderly females, num human elderly males, num dwarf child females, num dwarf child males, num dwarf adult females, num dwarf adult males, num dwarf elderly females, num dwarf elderly males, num elf child females, num elf child males, num elf adult females, num elf adult males, num elf elderly females, num elf elderly males, num orc child females, num orc child males, num orc adult females, num orc adult males, num orc elderly males, num orc elderly females, num starving

        for(Person p : people){
            if(p.getRace().equals(Races.getFirst()) && p.isChild() && p.getSex() == Sex.Female){
                statistics[0]++;
            }else if(p.getRace().equals(Races.getFirst()) && p.isChild() && p.getSex() == Sex.Male){
                statistics[1]++;
            }else if(p.getRace().equals(Races.getFirst()) && p.isAdult() && p.getSex() == Sex.Female){
                statistics[2]++;
            }else if(p.getRace().equals(Races.getFirst()) && p.isAdult() && p.getSex() == Sex.Male){
                statistics[3]++;
            }else if(p.getRace().equals(Races.getFirst()) && p.isElderly() && p.getSex() == Sex.Female){
                statistics[4]++;
            }else if(p.getRace().equals(Races.getFirst()) && p.isElderly() && p.getSex() == Sex.Male){
                statistics[5]++;
            }else if(p.getRace().equals(Races.get(1)) && p.isChild() && p.getSex() == Sex.Female){
                statistics[6]++;
            }else if(p.getRace().equals(Races.get(1)) && p.isChild() && p.getSex() == Sex.Male){
                statistics[7]++;
            }else if(p.getRace().equals(Races.get(1)) && p.isAdult() && p.getSex() == Sex.Female){
                statistics[8]++;
            }else if(p.getRace().equals(Races.get(1)) && p.isAdult() && p.getSex() == Sex.Male){
                statistics[9]++;
            }else if(p.getRace().equals(Races.get(1)) && p.isElderly() && p.getSex() == Sex.Female){
                statistics[10]++;
            }else if(p.getRace().equals(Races.get(1)) && p.isElderly() && p.getSex() == Sex.Male){
                statistics[11]++;
            }else if(p.getRace().equals(Races.get(2)) && p.isChild() && p.getSex() == Sex.Female){
                statistics[12]++;
            }else if(p.getRace().equals(Races.get(2)) && p.isChild() && p.getSex() == Sex.Male){
                statistics[13]++;
            }else if(p.getRace().equals(Races.get(2)) && p.isAdult() && p.getSex() == Sex.Female){
                statistics[14]++;
            }else if(p.getRace().equals(Races.get(2)) && p.isAdult() && p.getSex() == Sex.Male){
                statistics[15]++;
            }else if(p.getRace().equals(Races.get(2)) && p.isElderly() && p.getSex() == Sex.Female){
                statistics[16]++;
            }else if(p.getRace().equals(Races.get(2)) && p.isElderly() && p.getSex() == Sex.Male){
                statistics[17]++;
            }else if(p.getRace().equals(Races.get(3)) && p.isChild() && p.getSex() == Sex.Female){
                statistics[18]++;
            }else if(p.getRace().equals(Races.get(3)) && p.isChild() && p.getSex() == Sex.Male){
                statistics[19]++;
            }else if(p.getRace().equals(Races.get(3)) && p.isAdult() && p.getSex() == Sex.Female){
                statistics[20]++;
            }else if(p.getRace().equals(Races.get(3)) && p.isAdult() && p.getSex() == Sex.Male){
                statistics[21]++;
            }else if(p.getRace().equals(Races.get(3)) && p.isElderly() && p.getSex() == Sex.Female){
                statistics[22]++;
            }else if(p.getRace().equals(Races.get(3)) && p.isElderly() && p.getSex() == Sex.Male){
                statistics[23]++;
            }

            if(p.isStarving()){
                statistics[24]++;
                starving.add(p);
            }
        }

    }

    @Override
    public void ageYear() {
        for(int i = 0; i < 52; i++){
            this.age();
        }
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public int getAgeInYears() {
        return this.age / 52;
    }
}
