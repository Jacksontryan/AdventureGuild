package project.People.PeopleTesting;

import java.util.ArrayList;
import java.util.Random;

public class City {

    private Population humans;
    private Population dwarves;
    private Population elves;
    private Population orcs;

    private Person leader;

    private int cityGold;

    public City(int humansSize, int dwarvesSize, int elvesSize, int orcsSize){

        ArrayList<Race> races = Race.instantiateRaces();

        Race human = races.get(0);
        Race dwarf = races.get(1);
        Race elf = races.get(2);
        Race orc = races.get(3);

        humans = new Population(human, humansSize);
        dwarves = new Population(dwarf, dwarvesSize);
        elves = new Population(elf, elvesSize);
        orcs = new Population(orc, orcsSize);

        cityGold = 0;

        if(humansSize +dwarvesSize + elvesSize + orcsSize == 0){
            leader = null;
        }else{

            leader = null;

            Random rand = new Random();

            while(leader == null) {

                int leaderNumber = rand.nextInt(humansSize + dwarvesSize + elvesSize + orcsSize);

                Person candidate = null;

                if (leaderNumber < humansSize) {
                    candidate = humans.getPerson(leaderNumber);
                }else if (leaderNumber < dwarvesSize) {
                    candidate = dwarves.getPerson(leaderNumber - humansSize);
                }else if (leaderNumber < elvesSize) {
                    candidate = elves.getPerson(leaderNumber - humansSize - dwarvesSize);
                }else{
                    candidate = orcs.getPerson(leaderNumber - humansSize - dwarvesSize - elvesSize);
                }

                if(candidate != null && candidate.getAge() > candidate.getRace().getReproductionAge()){
                    leader = candidate;
                }

            }

        }

    }

    public Person getLeader(){
        return leader;
    }

    public Population getHumans(){
        return humans;
    }

    public boolean hasHumans(){
        return humans.getSize() != 0;
    }

    public Population getDwarves(){
        return dwarves;
    }

    public boolean hasDwarves(){
        return dwarves.getSize() != 0;
    }

    public Population getElves(){
        return elves;
    }

    public boolean hasElves(){
        return elves.getSize() != 0;
    }

    public Population getOrcs(){
        return orcs;
    }

    public boolean hasOrcs(){
        return orcs.getSize() != 0;
    }

    public int getCityGold(){
        return cityGold;
    }

    public void age(){
        humans.age();
        dwarves.age();
        elves.age();
        orcs.age();
        cityGold = cityGold + orcs.getExcessGold() + elves.getExcessGold() + humans.getExcessGold() + dwarves.getExcessGold();
    }

    public abstract static class Tributary extends Entity{

        protected int level;
        protected int maxLevel;
        protected int exp;
        protected int nextExp;
        protected int outputPerWorker;

        /*public Tributary(int maxNumWorkers){
            super(maxNumWorkers);
            //this.maxNumWorkers = maxNumWorkers;
            //workers = new ArrayList<>();
            this.level = 1;
            this.maxLevel = 1;
            this.exp = 0;
            this.nextExp = 0;
        }*/

        public Tributary(int maxNumWorkers, int maxLevel, int outputPerWorker){
            super(maxNumWorkers);
            //this.maxNumWorkers = maxNumWorkers;
            //workers = new ArrayList<>();
            this.level = 1;
            this.maxLevel = maxLevel;
            this.exp = 0;
            this.nextExp = 10 * level;
        }

        /*public int getNumWorkers(){
            return workers.size();
        }*/

        //public abstract boolean acceptingWorkers();

        //public abstract boolean addWorker(Person worker);

        /*public boolean fireWorker(Person worker){
            if(workers.contains(worker)){
                workers.remove(worker);
                worker.fire();
                return true;
            }
            return false;
        }*/

        /*public void resize(int newSize){
            if(newSize < maxNumWorkers){
                while(getNumWorkers() > newSize){
                    fireWorker(workers.getLast());
                }
            }
            maxNumWorkers = newSize;
        }*/

        /*public int getMaxNumWorkers(){
            return maxNumWorkers;
        }*/

        //public abstract void age();

        public abstract int getOutputPerWorker();

        public void levelUp(){
            if(this.level < this.maxLevel && this.exp >= this.nextExp){
                this.exp-=this.nextExp;
                this.level++;
                if(this.level == this.maxLevel){
                    this.nextExp = 0;
                }else {
                    this.nextExp = this.level * 10;
                }
            }
        }

    }

    public abstract static class Entity{

        protected int maxNumWorkers;
        protected ArrayList<Person> workers;

        public Entity(int maxNumWorkers){
            this.maxNumWorkers = maxNumWorkers;
            workers = new ArrayList<>();
        }

        public abstract boolean acceptingWorkers();

        public abstract boolean addWorker(Person worker);

        public boolean fireWorker(Person worker){
            if(workers.contains(worker)){
                workers.remove(worker);
                worker.fire();
                return true;
            }

            return false;
        }

        public int getMaxNumWorkers(){
            return this.maxNumWorkers;
        }

        public ArrayList<Person> getWorkers(){
            return workers;
        }

        public int getNumWorkers(){
            return workers.size();
        }

        public void resize(int newSize){
            if(newSize < maxNumWorkers){
                while(getNumWorkers() > newSize){
                    fireWorker(workers.getLast());
                }
            }
            maxNumWorkers = newSize;
        }

        public abstract void age();

    }

    public class Farm extends Tributary{

        private Person owner;
        private ArrayList<House> houses;

        public Farm(){
            super(30, 10, 10);
            this.owner = City.this.leader;
            this.houses = new ArrayList<>();
            houses.add(new House());
            if(!owner.isHoused()){
                houses.getFirst().setOwner(owner);
            }
        }

        public Farm(Person owner) {
            super(30, 10, 10);
            this.owner = owner;
            houses = new ArrayList<>();
            houses.add(new House());
            if(!owner.isHoused()){
                houses.getFirst().setOwner(owner);
            }
        }

        public int getOutputPerWorker(){
            return this.level * this.outputPerWorker / this.maxLevel;
        }

        public Person getOwner(){
            return owner;
        }

        public int getOutput(){
            return getOutputPerWorker() * getNumWorkers();
        }

        @Override
        public int getMaxNumWorkers(){
            return this.maxNumWorkers * this.level / this.maxLevel;
        }

        @Override
        public boolean acceptingWorkers(){
            return this.getNumWorkers() >= this.getMaxNumWorkers();
        }

        @Override
        public boolean addWorker(Person worker){
            if(this.acceptingWorkers() || worker.hasJob()){
                return false;
            }
            workers.add(worker);
            return true;
        }

        public int getMaxNumHouses(){
            return level * 3;
        }

        public ArrayList<House> getHouses(){
            return houses;
        }

        public int getNumHouses(){
            return houses.size();
        }

        public boolean acceptingHouses(){
            return getNumHouses() < getMaxNumHouses();
        }

        public boolean addHouse(){
            if(!acceptingHouses()){
                return false;
            }
            houses.add(new House());
            return true;
        }

        @Override
        public void age() {

            ArrayList<Person> workersToRemove = new ArrayList<>();

            for(Person worker : super.workers){
                if(!worker.isAlive()){
                    workersToRemove.add(worker);
                }
            }

            super.workers.removeAll(workersToRemove);

            if(level < this.maxLevel) {
                this.exp += getNumWorkers();
            }

            levelUp();

            for(House house : houses){
                house.age();
                if(!super.workers.contains(house.getOwner()) && house.getOwner() != null){
                    house.kickInhabitants();
                }else if(house.getOwner() == null){
                    int i = 0;
                    boolean found = false;
                    while(i < super.workers.size() && !found){
                        if(!super.workers.get(i).isHoused()){
                            house.setOwner(super.workers.get(i));
                            found = true;
                        }
                        i++;
                    }
                }
            }

            if(!owner.isAlive() && !super.workers.isEmpty()){
                this.owner = super.workers.get(0);
            }else{
                this.owner = City.this.leader;
            }

        }
    }

    public class Mine extends Tributary{

        private Person owner;

        public Mine() {
            super(150,10, 100);
            this.owner = City.this.leader;
        }

        @Override
        public int getMaxNumWorkers(){
            return this.level * super.getMaxNumWorkers() / this.maxLevel;
        }

        @Override
        public boolean acceptingWorkers(){
            return getNumWorkers() >= getMaxNumWorkers();
        }

        @Override
        public boolean addWorker(Person worker) {
            if(!acceptingWorkers() || worker.hasJob()){
                return false;
            }
            workers.add(worker);
            worker.hire();
            return true;
        }

        public int getOutputPerWorker(){
            return this.outputPerWorker * this.level / this.maxLevel;
        }

        @Override
        public void age() {

        }
    }

    public class Orphanage extends Entity{

        private ArrayList<Person> orphans;

        private House orphanageHall;

        public Orphanage(ArrayList<Person> orphans) {
            super(orphans.size() / 5);
            orphanageHall = new House();
            this.orphans = orphans;
            ArrayList<Person> adultOrphans = new ArrayList<>();
            for(int i = 0; i < orphans.size(); i++){
                if(orphans.get(i).isAdult()){
                    adultOrphans.add(orphans.get(i));
                }
            }
            this.orphans.removeAll(adultOrphans);
        }

        @Override
        public int getMaxNumWorkers(){
            return orphans.size() / 5;
        }

        @Override
        public boolean acceptingWorkers(){
            return getNumWorkers() >= getMaxNumWorkers();
        }

        @Override
        public boolean addWorker(Person worker){
            if(!this.acceptingWorkers() || worker.hasJob()){
                return false;
            }
            this.workers.add(worker);
            worker.hire();
            return true;
        }

        @Override
        public void age() {

            ArrayList<Person> adultOrphans = new ArrayList<>();

            for(int i = 0; i < orphans.size(); i++){
                if(orphans.get(i).isAdult()){
                    adultOrphans.add(orphans.get(i));
                    orphans.get(i).loseHouse();
                }
            }

            orphans.removeAll(adultOrphans);

            ArrayList<Person> newOrphans = new ArrayList<>();

            for(Person person : City.this.humans.getPeople()){
                if(person.getAge() < person.getRace().getReproductionAge() && !person.isHoused() && person.isAlive()){
                    newOrphans.add(person);
                    person.occupyHouse(orphanageHall);
                }
            }for(Person person : City.this.dwarves.getPeople()){
                if(person.getAge() < person.getRace().getReproductionAge() && !person.isHoused() && person.isAlive()){
                    newOrphans.add(person);
                    person.occupyHouse(orphanageHall);
                }
            }for(Person person : City.this.elves.getPeople()){
                if(person.getAge() <  person.getRace().getReproductionAge() && !person.isHoused() && person.isAlive()){
                    newOrphans.add(person);
                    person.occupyHouse(orphanageHall);
                }
            }for(Person person : City.this.orcs.getPeople()){
                if(person.getAge() < person.getRace().getReproductionAge() && !person.isHoused() && person.isAlive()){
                    newOrphans.add(person);
                    person.occupyHouse(orphanageHall);
                }
            }
            orphans.addAll(newOrphans);
            int workersSize = orphans.size() / 5;
            if(workersSize != getMaxNumWorkers()){
                resize(workersSize);
            }
        }
    }

}
/*
O(1) Constant
O(log(n)) logrithmic
O(log^k(n)) poly log
O(N^c) sub linear
O(N) Linear
O(N^2) Quadratic
O(N^3) Cubic
O(N^k) polynomial
O(C^N) exponential
O(N!) factorial
 */