package project.People;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Person {

    private String name;
    private final DNA dna;
    private final Sex sex;
    public Race race;
    private int age;
    private boolean pregnant = false;
    private int weeksPregnant = 0;
    private Person partner = null;
    private Person fatherOfChild = null;
    private ArrayList<Person> children = new ArrayList<Person>();
    private Person father = null;
    private Person mother = null;
    private boolean housed;
    private boolean isAlive;
    private boolean isWorking;

    public Person(String name, DNA dna, Race race, Sex sex) {
        if(race.raceNum != dna.getRace()){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.dna = dna;
        this.race = race;
        this.sex = sex;
        this.age = 0;
        housed = false;
        isAlive = true;
        isWorking = false;
    }

    public Person(String name, DNA dna, Race race, Sex sex, int age) {
        if(race.raceNum != dna.getRace()){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.dna = dna;
        this.race = race;
        this.sex = sex;
        this.age = age;
        housed = false;
        isAlive = true;
        isWorking = false;
    }

    public Person(String name, Race race, Sex sex){
        this.name = name;
        this.race = race;
        this.sex = sex;
        this.dna = new DNA(race.getRaceNum());
        this.age = 0;
        housed = false;
        isAlive = true;
        isWorking = false;
    }

    public String getName() {
        return name;
    }

    public boolean isHoused(){
        return housed;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public DNA getDna() {
        return dna;
    }

    public boolean getHoused(){
        return housed;
    }

    public void setHoused(boolean housed){
        this.housed = housed;
    }

    public Race getRace() {
        return race;
    }

    public Sex getSex(){
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Person age() {
        age++;

        if (pregnant) {
            weeksPregnant++;

            if (weeksPregnant >= race.gestationPeriod) {
                pregnant = false;
                weeksPregnant = 0;

                // Create baby
                Random rand = new Random();
                Sex babySex = rand.nextInt(2) == 0 ? Sex.FEMALE : Sex.MALE;
                String name = "TEMP";
                DNA babyDNA = this.dna.reproduce(fatherOfChild.dna);

                Person baby = new Person(name, babyDNA, this.race, babySex);
                baby.father = partner;
                baby.mother = this;
                this.addChild(baby);
                fatherOfChild.addChild(baby);
                fatherOfChild = null;
                return baby;
            }
        }

        return null;
    }

    public void getPregnant(Person other) {
        if (sex != Sex.FEMALE || other.sex != Sex.MALE) {
            throw new IllegalArgumentException("Only male → female pregnancy allowed");
        }
        if (pregnant) return;

        pregnant = true;
        weeksPregnant = 0;
        fatherOfChild = other;
    }

    public Person getPartner() {
        return partner;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public Person reproduce(){
        if(this.race!=partner.race || this.sex == partner.sex){
            throw new IllegalArgumentException();
        }
        if(this.dna.getPercentageShared(partner.getDna()) > .125){
            throw new IllegalArgumentException("Too related");
        }
        Random rand = new Random();
        Sex sex = rand.nextInt(2) == 0?Sex.FEMALE:Sex.MALE;
        String name = "TEMP";
        DNA dna = this.dna.reproduce(partner.dna);
        return new Person(name, dna, this.race, sex);
    }

    public void addChild(Person child){
        children.addLast(child);
    }

    public void marry(Person partner){
        this.partner = partner;
        partner.partner = this;
    }

    public int getAgeInYears(){
        return age/52;
    }

    public void die(){
        if(partner != null) {
            partner.partner = null;
            partner = null;
        }
        if(father != null){
            father.children.remove(this);
        }if(mother != null){
            mother.children.remove(this);
        }
        isAlive = false;
    }

    public boolean isWorking(){
        return isWorking;
    }

    public void Hire(){
        isWorking = true;
    }

    public void Fire(){
        isWorking = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person other)) return false;

        return Objects.equals(this.name, other.name)
                && Objects.equals(this.dna, other.dna)
                && this.race == other.race;
    }

    public ArrayList<Person> getChildren(){
        return children;
    }

    public static class Race{
        private final String name;
        private final int raceNum;
        private int averageLifespan;
        private int reproductionAge;
        private int gestationPeriod;

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

        public static ArrayList<Race> instantiateRaces(){

            Race human = new Race("Human", 100, 3744,936, 40);
            Race dwarf = new Race("Dwarf", 110, 4160, 1040, 45);
            Race elf = new Race("Elf", 120, 7800,2028, 80);
            Race orc = new Race("Orc", 130, 3120,832, 30);

            ArrayList<Race> races = new ArrayList<>();

            races.add(human);
            races.add(dwarf);
            races.add(elf);
            races.add(orc);

            return races;
        }
    }

    public enum Sex{
        MALE,
        FEMALE
    }

}
