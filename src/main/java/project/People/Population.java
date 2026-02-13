package project.People;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Population implements Ageable{

    private ArrayList<Person> people;
    private Race race;
    private long numDead;

    private double growthPercentage;
    private int excessGold;
    private int ageNum = 0;

    private ArrayList<Person> newPeopleThisWeek = new ArrayList<>();
    private ArrayList<Person> diedThisWeek = new ArrayList<>();

    public Population(Race race, int startSize){

        this.race = race;

        this.people = new ArrayList<>();

        this.numDead = 0;

        while(people.size() < startSize){

            people.add(new Person("TEMP", this.race));

        }

        this.excessGold = 0;

    }

    public Population(ArrayList<Person> people){
        this.people = people;
        this.race = this.people.getFirst().getRace();
        this.numDead = 0;
        this.excessGold = 0;
    }

    public Race getRace(){
        return this.race;
    }

    public ArrayList<Person> getPeople(){
        return this.people;
    }

    public int getNumMen(){
        int numMen = 0;
        for(Person p: people){
            if(p.getSex().equals(Sex.Male)){
                numMen++;
            }
        }
        return numMen;
    }

    public int getNumWomen(){
        int numWomen = 0;
        for(Person p: people){
            if(p.getSex().equals(Sex.Female)){
                numWomen++;
            }
        }
        return numWomen;
    }

    public double getAverageNumChildren(){

        int numChildren = 0;
        int numAdults = 0;


        for(Person p: people){
            if(p.isAdult() && p.hasChildren()){
                numAdults++;
                numChildren+=p.getNumberOfChildren();
            }
        }

        return 1.0 * numChildren / numAdults;
    }

    public int getNumChildren(){
        int numChildren = 0;
        for(Person p: people){
            if(p.getAge() < p.getRace().getReproductionAge()){
                numChildren++;
            }
        }
        return numChildren;
    }

    public int getNumElderly(){
        int numElderly = 0;
        for(Person p: people){
            if(p.getAge() > p.getRace().getAverageLifespan()){
                numElderly++;
            }
        }
        return numElderly;
    }

    public long getNumDead(){
        return numDead;
    }

    public int getSize(){
        return this.people.size();
    }

    public int getAgeNum(){
        return this.ageNum;
    }

    public int getExcessGold(){
        int e = this.excessGold;
        this.excessGold = 0;
        return e;
    }

    public boolean addPerson(Person person){

        if(person.getRace().equals(this.race)){

            this.people.add(person);
            return true;

        }

        return false;

    }

    public Person getOldestPerson(){

        int oldest = 0;
        Person oldestPerson = null;

        for(Person person : people){

            if(person.getAge()>oldest){

                oldest = person.getAge();
                oldestPerson = person;

            }

        }

        return oldestPerson;

    }

    public Person getPerson(int i){
        if(i >= people.size() || i < 0){
            throw new IndexOutOfBoundsException();
        }
        return people.get(i);
    }

    public ArrayList<Integer> getAgeGroups(){

        if(this.people.isEmpty()){
            return new ArrayList<>();
        }

        ArrayList<Integer> ageGroups = new ArrayList<>();
        int oldest = getOldestPerson().getAge();

        for(int i = 0; i < oldest; i++){
            ageGroups.add(i,0);
        }

        for(Person person : people){
            int age = person.getAgeInYears();
            ageGroups.set(age,ageGroups.get(age)+1);
        }

        return ageGroups;

    }

    public boolean mergePopulations(Population other){

        if(race.equals(other.race)){

            this.people.addAll(other.people);
            return true;

        }

        return false;

    }

    public ArrayList<Person> getPeopleDiedThisWeek(){
        return this.diedThisWeek;
    }

    public double getGrowthPercentage(){
        return this.growthPercentage;
    }

    public void age(){

        newPeopleThisWeek.clear();

        Random rand = new Random();
        double deathToll = 0;
        if(race.equals(Race.instantiateRaces().get(0))){
            deathToll = rand.nextDouble(0.0001,0.0015);
        }else if(race.equals(Race.instantiateRaces().get(1))){
            deathToll = rand.nextDouble(0.0001,0.0015);
        }else if(race.equals(Race.instantiateRaces().get(2))){
            deathToll = rand.nextDouble(0.000025,0.0005);
        }else if(race.equals(Race.instantiateRaces().get(3))){
            deathToll = rand.nextDouble(0.00015,0.002);
        }
        newPeopleThisWeek = new ArrayList<>();
        diedThisWeek = new ArrayList<>();

        int initialSize = people.size();

        for(Person person: people){

            person.age();
            ArrayList<Person> babies = person.getNewKids();

            for(Person baby : babies) {
                if (baby != null) {
                    newPeopleThisWeek.add(baby);
                }
            }

            double deathChance = Math.random();

            if(deathChance < deathToll || person.getAge() >= race.getAverageLifespan() * 1.5){
                diedThisWeek.add(person);
            }

            if(!person.isMarried()){
                Person potentialCandidate = people.get(rand.nextInt(people.size()));
                boolean ageCompatible = person.getAge() >= potentialCandidate.getAge() / 2 && potentialCandidate.getAge() >= person.getAge() / 2;

                if(potentialCandidate.getAge() >= race.getReproductionAge() && !potentialCandidate.isMarried() && ageCompatible && !diedThisWeek.contains(potentialCandidate) && !diedThisWeek.contains(person)){
                    person.getMarried(potentialCandidate);
                }

            }else if(person.getSex().equals(Sex.Female) && !person.isPregnant() && person.getNumberOfChildren() < person.getRace().getPreferredNumKids() && person.getPartner().getNumberOfChildren() < person.getRace().getPreferredNumKids()){

                person.getPregnant();

            }

        }

        people.removeAll(diedThisWeek);
        people.addAll(newPeopleThisWeek);

        for(Person person : diedThisWeek){
            person.die();
            excessGold += person.getMoney();
            person.spendMoney(person.getMoney());
        }

        //newPeople.clear();
        numDead+=diedThisWeek.size();
        //deadPeople.clear();

        int finalSize =  people.size();

        growthPercentage = (1.0 * finalSize - initialSize) / initialSize * 100.0;
        ageNum++;

    }

    public ArrayList<Person> getNewPeopleThisWeek(){
        ArrayList<Person> temp = new ArrayList<>(newPeopleThisWeek);
        newPeopleThisWeek.clear();
        return temp;
    }

    public void killPerson(Person person){
        person.die();
        people.remove(person);
    }

    public void ageYear(){
        for(int i = 0; i < 52; i++){
            age();
        }
    }

    @Override
    public int getAge() {
        return this.ageNum;
    }

    @Override
    public int getAgeInYears() {
        return this.ageNum / 52;
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Population humans = new Population(Race.instantiateRaces().get(0), 175);
        Population dwarves = new Population(Race.instantiateRaces().get(1), 175);
        Population elves = new Population(Race.instantiateRaces().get(2), 175);
        Population orcs = new Population(Race.instantiateRaces().get(3), 175);

        int week = 0;

        File humanFile = new File("src\\main\\java\\project\\People\\PeopleTesting\\HumanPopulation.txt");
        File dwarfFile = new File("src\\main\\java\\project\\People\\PeopleTesting\\DwarfPopulation.txt");
        File elvesFile = new File("src\\main\\java\\project\\People\\PeopleTesting\\ElfPopulation.txt");
        File orcsFile = new File("src\\main\\java\\project\\People\\PeopleTesting\\OrcPopulation.txt");

        PrintWriter humanWriter = new PrintWriter(humanFile);
        PrintWriter dwarfWriter = new PrintWriter(dwarfFile);
        PrintWriter elfWriter = new PrintWriter(elvesFile);
        PrintWriter orcsWriter = new PrintWriter(orcsFile);

        while(week < 100000){

            humanWriter.println("Race: " + humans.getRace().getName() + ", Week " + week + " size: " + humans.getSize() + ", Growth Percentage: " + Math.round(humans.getGrowthPercentage() * 100.0)/100.0 + "%, Number of Dead: " + humans.getNumDead() + " Average Number of Children per adult: " + humans.getAverageNumChildren());

            dwarfWriter.println("Race: " + dwarves.getRace().getName() + ", Week " + week + " size: " + dwarves.getSize() + ", Growth Percentage: " + Math.round(dwarves.getGrowthPercentage() * 100.0)/100.0 + "%, Number of Dead: " + dwarves.getNumDead() + " Average Number of Children per adult: " + dwarves.getAverageNumChildren());

            elfWriter.println("Race: " + elves.getRace().getName() + ", Week " + week + " size: " + elves.getSize() + ", Growth Percentage: " + Math.round(elves.getGrowthPercentage() * 100.0)/100.0 + "%, Number of Dead: " + elves.getNumDead() + " Average Number of Children per adult: " + elves.getAverageNumChildren());

            orcsWriter.println("Race: " + orcs.getRace().getName() + ", Week " + week + " size: " + orcs.getSize() + ", Growth Percentage: " + Math.round(orcs.getGrowthPercentage() * 100.0)/100.0 + "%, Number of Dead: " + orcs.getNumDead() + " Average Number of Children per adult: " + orcs.getAverageNumChildren());

            week++;

            Thread thread1 = new Thread(new Runnable() {
                public void run() {
                    humans.age();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    dwarves.age();
                }
            });
            Thread thread3 = new Thread(new Runnable() {
                public void run() {
                    elves.age();
                }
            });
            Thread thread4 = new Thread(new Runnable() {
                public void run() {
                    orcs.age();
                }
            });

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            System.out.println(week);

        }

        humanWriter.close();
        dwarfWriter.close();
        elfWriter.close();
        orcsWriter.close();
    }

    /*
    NOTES

    1000 Humans reaches population == 200,000 at week num 7933

    1000 Dwarves reaches population == 200,000 at week num 10116

    1000 Elves reaches population == 200,000 at week num

     */

}
