package project.People.PeopleTesting;

import project.People.*;

import java.util.ArrayList;
import java.util.Random;

public class Person implements Ageable {

    private int age;//age(), ageYear(), getAge(), getAgeInYears(), isChild(), isAdult(), isElderly()

    private Race race;//getRace()

    private DNA dna;//getDNA()

    private Sex sex;//getSex()

    private Family family;//getFamily(), setFamily()

    //getFirstParent()
    private Person parent1;//<- if straight or lesbian - Woman. if gay - Man
    //getSecondParent()
    private Person parent2;//<-if straight or gay - Man. if lesbian - Woman
    private ArrayList<Person> children;//getChildren(), addChild(), hasChildren(), getNumChildren

    private Person fatherOfCurrentChild;
    private int weeksPregnant;
    private ArrayList<Person>newChildren;//getNewChildren()

    private boolean isAlive;//isAlive(),die()
    private byte lastAte;//getLastAte(), age(), eat()

    private Job job;//getJob(), hire(), fire(), isWorking()

    private int money;//getMoney(), makeMoney(), spendMoney()

    private DispositionChart dispositionChart;//getDispositionChart(), getDisposition(), interact()

    private static final ArrayList<Race> Races = Race.instantiateRaces();
    private static final ArrayList<Job> Jobs = Job.instantiateJobs();

    public Person(){

        this.age = 0;

        this.race = EventPicker.pick(Races);

        this.dna = new DNA(this.race.getRaceNum());

        this.sex = EventPicker.pick(new Sex[]{Sex.Female, Sex.Male});

        this.family = new Family(this);

        this.parent1 = null;
        this.parent2 = null;

        this.children = new ArrayList<Person>();

        this.isAlive = true;
        this.lastAte = 0;

        this.job = Jobs.getLast();

        this.money = 0;

        this.dispositionChart = new DispositionChart(this);

    }

    public Person(Race race){

        this.age = 0;

        this.race = race;

        this.dna = new DNA(this.race.getRaceNum());

        this.sex = EventPicker.pick(new Sex[]{Sex.Female, Sex.Male});

        this.family = new Family(this);

        this.parent1 = null;

        this.parent2 = null;

        this.children = new ArrayList<Person>();

        this.isAlive = true;

        this.lastAte = 0;

        this.job = Jobs.getLast();

        this.money = 0;

        this.dispositionChart = new DispositionChart(this);

    }

    public Person(Race race, int age){

        this.age = age;

        this.race = race;

        this.dna = new DNA(this.race.getRaceNum());

        this.sex = EventPicker.pick(new Sex[]{Sex.Female, Sex.Male});

        this.family = new Family(this);

        this.parent1 = null;

        this.parent2 = null;

        this.children = new ArrayList<Person>();

        this.isAlive = true;

        this.lastAte = 0;

        this.job = Jobs.getLast();

        this.money = 0;

        this.dispositionChart = new DispositionChart(this);

    }

    public Person(Race race, DNA dna){

        if(race.getRaceNum() != dna.getRace()){
            throw new IllegalArgumentException("DNA does not match DNA type of its respective Race");
        }

        this.age = 0;

        this.race = race;

        this.dna = dna;

        this.sex = EventPicker.pick(new Sex[]{Sex.Female, Sex.Male});

        this.family = new Family(this);

        this.parent1 = null;

        this.parent2 = null;

        this.children = new ArrayList<Person>();

        this.isAlive = true;

        this.lastAte = 0;

        this.job = Jobs.getLast();

        this.money = 0;

        this.dispositionChart = new DispositionChart(this);

    }

    public Person(Race race, DNA dna, int age){

        if(this.race.getRaceNum() != dna.getRace()){
            throw new IllegalArgumentException("DNA does not match DNA type of its respective Race");
        }

        this.age = age;

        this.race = race;

        this.dna = dna;

        this.sex = EventPicker.pick(new Sex[]{Sex.Female, Sex.Male});

        this.family = new Family(this);

        this.parent1 = null;

        this.parent2 = null;

        this.children = new ArrayList<Person>();

        this.isAlive = true;

        this.lastAte = 0;

        this.job = Jobs.getLast();

        this.money = 0;

        this.dispositionChart = new DispositionChart(this);

    }

    public Person(Person parent1, Person parent2){
        if(!parent1.getRace().equals(parent2.getRace())){
            throw new IllegalArgumentException("Different Races Cannot Breed");
        }

        this.age = 0;

        this.race = parent1.getRace();

        this.dna = parent1.getDna().reproduce(parent2.getDna());

        this.sex = EventPicker.pick(new Sex[]{Sex.Female, Sex.Male});

        this.family = parent1.getSex() == Sex.Female ? parent1.getFamily() : parent2.getFamily();

        this.parent1 = parent1;

        this.parent2 = parent2;

        this.children = new ArrayList<Person>();

        this.isAlive = true;

        this.lastAte = 0;

        this.job = Jobs.getLast();

        this.money = 0;
        this.dispositionChart = new DispositionChart(this);
        dispositionChart.addRelationship(parent1, 100);
        dispositionChart.addRelationship(parent2, 100);

    }

    public Race getRace(){
        return race;
    }

    public DNA getDna(){
        return dna;
    }

    public Sex getSex(){
        return sex;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public boolean isChild(){
        return this.age < this.race.getReproductionAge();
    }

    public boolean isOrphan(){
        return (this.parent1 == null || !this.parent1.isAlive) && (this.parent2 == null || !this.parent2.isAlive) && this.isChild();
    }

    public boolean isAdult(){
        return this.age >= this.race.getReproductionAge() && this.age < this.race.getAverageLifespan();
    }

    public boolean isElderly(){
        return this.age >= this.race.getAverageLifespan();
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Person getFirstParent(){
        return parent1;
    }

    public Person getSecondParent(){
        return parent2;
    }

    public ArrayList<Person> getChildren(){
        return children;
    }

    public int getNumChildren(){
        return children.size();
    }

    public void addChild(Person child){
        this.children.add(child);
        dispositionChart.addRelationship(child, 100);
    }

    public boolean hasChildren(){
        return !this.children.isEmpty();
    }

    public int getMoney(){
        return this.money;
    }

    public void makeMoney(int money){
        if(money > 0){
            this.money+=money;
        }
    }

    public boolean spendMoney(int money){
        if(this.money >= money && money > 0){
            this.money -= money;
            return true;
        }
        return false;
    }

    public Job getJob(){
        return job;
    }

    public boolean isWorking(){
        return !this.job.equals(Jobs.getLast());
    }

    public boolean hire(Job job){
        if(this.job.equals(Jobs.getLast())){
            this.job = job;
            return true;
        }
        return false;
    }

    public void fire(){
        this.job = Jobs.getLast();
    }

    public byte getLastAte(){
        return this.lastAte;
    }

    public void eat(){
        this.lastAte = 0;
    }

    public boolean isStarving(){
        return this.lastAte >= 1;
    }

    public void die(){
        this.isAlive = false;
        for(Person child : this.children){
            if(child.isAlive() && child.getFirstParent().equals(this)){
                child.parent1 = null;
            }else if(child.isAlive() && child.getSecondParent().equals(this)){
                child.parent2 = null;
            }
        }
        if(parent1 != null && parent1.isAlive) {
            parent1.getChildren().remove(this);
        }
        if(parent2 != null && parent2.isAlive) {
            parent2.getChildren().remove(this);
        }
    }

    public boolean isPregnant(){
        return this.fatherOfCurrentChild != null;
    }

    public ArrayList<Person> getNewChildren(){
        return this.newChildren;
    }

    @Override
    public void age() {

        newChildren.clear();

        this.age++;
        this.lastAte++;

        if(isPregnant()){
            this.weeksPregnant++;
            if(this.weeksPregnant >= this.race.getGestationPeriod()){
                newChildren = new ArrayList<Person>();
                newChildren.add(new Person(this, fatherOfCurrentChild));
                this.addChild(newChildren.getFirst());
                fatherOfCurrentChild.addChild(newChildren.getFirst());
                this.weeksPregnant = 0;
                this.fatherOfCurrentChild = null;
            }
        }

        if(this.lastAte > 3){
            this.die();
        }

        if(family.canHaveChildren() && this.sex == Sex.Female){
            this.fatherOfCurrentChild = family.getFirstPartner().equals(this) ? family.getSecondPartner() : family.getFirstPartner();
        }

    }

    @Override
    public void ageYear() {

        for(int i = 0; i < 52; i++){
            age();
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

    public boolean isBirthday(){
        return this.age % 52 == 0;
    }

    public DispositionChart getDispositionChart() {
        return dispositionChart;
    }

    public int getDisposition(Person person){
        return this.dispositionChart.getDisposition(person);
    }

    public void interact(Person person, int disposition){
        dispositionChart.addRelationship(person, disposition);
    }

    public void interact(Person person){
        dispositionChart.addRelationship(person, 0);
        person.dispositionChart.addRelationship(this, 0);
    }

    public boolean askOut(Person person){
        if(person.getDisposition(this) >= 25 && !this.family.inRelationship() && !person.family.inRelationship()){
            this.family.mergeFamily(person.getFamily());
            this.interact(person, 5);
            person.interact(this, 5);
            return true;
        }
        this.interact(person, -5);
        person.interact(this, -5);
        return false;
    }

    public void throwParty(ArrayList<Person> guests){
        Random rand = new Random();
        for(int i = 0; i < guests.size(); i++){
            for(int j = i + 1; j < guests.size(); j++){
                this.interact(guests.get(i), rand.nextInt(-5,11));
                guests.get(i).interact(this, rand.nextInt(-5,11));
                guests.get(i).interact(guests.get(j), rand.nextInt(-5,11));
                guests.get(j).interact(guests.get(i), rand.nextInt(-5,11));
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person){
            Person person = (Person)obj;
            return this.age == person.getAge() && this.dna.equals(person.getDna()) && this.getSex() == person.getSex();
        }
        return false;
    }

}
