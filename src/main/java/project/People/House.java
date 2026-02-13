package project.People;

import java.util.ArrayList;
import java.util.Scanner;

public class House extends City.Entity {

    private Person owner;
    private Person ownersSpouse;
    private ArrayList<Person> children;
    private int age = 0;
    private boolean livable;

    public House(boolean livable){
        super(null, null);
        this.owner = null;
        this.ownersSpouse = null;
        this.children = new ArrayList<>();
        this.livable = livable;
    }

    public House(Person owner){
        super(owner, null);
        if(owner.isHoused()){
            throw new IllegalArgumentException("This owner is already housed");
        }else if(owner.getPartner() != null && owner.getPartner().isHoused()){
            owner.occupyHouse(owner.getPartner().getHouse());
            throw new IllegalArgumentException("Fatal Error: Partner is already housed, however Person is not housed");
        }
        this.owner = owner;

        for(Person child : owner.getChildren()){
            addChildOccupant(child);
        }

        if(owner.getPartner() == null){
            ownersSpouse = null;
        }else {
            ownersSpouse = owner.getPartner();
            for(Person child : ownersSpouse.getChildren()){
                addChildOccupant(child);
            }

        }

        this.livable = true;

    }

    public Person getOwner(){
        return this.owner;
    }

    public boolean getLivable(){
        return this.livable;
    }

    public void setLivable(boolean livable){
        this.livable = livable;
    }

    public boolean setOwner(Person owner){
        if(this.owner != null || !this.livable){
            return false;
        }
        this.owner = owner;
        owner.occupyHouse(this);
        for(Person child : owner.getChildren()){
            if(child.getAge() <= child.getRace().getReproductionAge()) {
                addChildOccupant(child);
                child.occupyHouse(this);
            }
        }
        if(this.owner.getPartner() != null){
            this.ownersSpouse = this.owner.getPartner();
            this.ownersSpouse.occupyHouse(this);
            for(Person child : this.ownersSpouse.getChildren()){
                if(child.getAge() <= child.getRace().getReproductionAge()) {
                    addChildOccupant(child);
                    child.occupyHouse(this);
                }
            }
        }
        return true;
    }

    public boolean addCity(City city){

        if(this.city != null){
            return false;
        }

        this.city = city;
        return true;
    }

    public boolean addChildOccupant(Person child){
        if(this.children.contains(child) || !this.livable){
            return false;
        }
        children.add(child);
        child.occupyHouse(this);
        return true;
    }

    public void ageYear(){
        for(int i = 0; i < 52; i++){
            age();
        }
    }

    /*public void age(){
        if((owner != null) && (ownersSpouse != null) && (!owner.isAlive() && !ownersSpouse.isAlive())){

            this.owner.loseHouse();
            this.ownersSpouse.loseHouse();

            Person newOwner = this.owner.getOldestChild();

            this.owner = null;
            this.ownersSpouse = null;

            for(Person child : children){
                child.loseHouse();
            }

            children.clear();

            this.owner = newOwner;
            this.owner.occupyHouse(this);

            for(Person child : owner.getChildren()){
                addChildOccupant(child);
            }

            if(this.owner.getPartner() != null) {
                this.ownersSpouse = newOwner.getPartner();
                this.ownersSpouse.occupyHouse(this);
                for(Person child : ownersSpouse.getChildren()){
                    addChildOccupant(child);
                }
            }

        }else if((owner!=null) && (ownersSpouse != null) && !owner.isAlive() && ownersSpouse.isAlive()){

            this.owner.loseHouse();
            this.owner = ownersSpouse;
            this.ownersSpouse = null;

            ArrayList<Person> childrenSentToOrphanage = new ArrayList<>();

            for(Person child : children){
                if(!owner.getChildren().contains(child)){
                    child.loseHouse();
                    childrenSentToOrphanage.add(child);
                }
            }

            children.removeAll(childrenSentToOrphanage);

        }else if((owner != null) && (ownersSpouse != null) && owner.isAlive() && !ownersSpouse.isAlive()){

            this.ownersSpouse.loseHouse();
            this.ownersSpouse = null;

            ArrayList<Person> childrenSentToOrphanage = new ArrayList<>();

            for(Person child : children){
                if(!owner.getChildren().contains(child)){
                    child.loseHouse();
                    childrenSentToOrphanage.add(child);
                }
            }

            children.removeAll(childrenSentToOrphanage);

        }else if(owner!=null && !owner.isAlive()){

            Person newOwner = this.owner.getOldestChild();

            for(Person child : owner.getChildren()){
                if(!child.isAlive()){
                    children.remove(child);
                    child.loseHouse();
                }
            }
            this.owner.loseHouse();
            this.owner = newOwner;
            if(this.ownersSpouse != null){
                this.ownersSpouse.loseHouse();
            }
            this.ownersSpouse = owner.getPartner();
            if(this.owner.getPartner() != null) {
                this.ownersSpouse.occupyHouse(this);
            }
        }

        ArrayList<Person> adultChildren = new ArrayList<>();

        for (Person child : children){
            if(child.getAge() > child.getRace().getReproductionAge()){
                child.loseHouse();
                adultChildren.add(child);
            }
        }

        children.removeAll(adultChildren);

    }*/

    public int getAge(){
        return age;
    }

    @Override
    public int getAgeInYears() {
        return this.age / 52;
    }

    public void age() {

        // ------------------------------------------------------------
        // STEP 1 — Clean up children (dead or adult)
        // ------------------------------------------------------------
        ArrayList<Person> toRemove = new ArrayList<>();

        for (Person child : children) {
            boolean dead = !child.isAlive();
            boolean adult = child.getAge() > child.getRace().getReproductionAge();

            if (dead || adult) {
                child.loseHouse();
                toRemove.add(child);
            }
        }

        children.removeAll(toRemove);


        // ------------------------------------------------------------
        // STEP 2 — Determine adult survival state
        // ------------------------------------------------------------
        boolean ownerAlive = owner != null && owner.isAlive();
        boolean spouseAlive = ownersSpouse != null && ownersSpouse.isAlive();


        // ------------------------------------------------------------
        // STEP 3 — Handle inheritance / ownership transitions
        // ------------------------------------------------------------

        // CASE A — Owner alive
        if (ownerAlive) {

            // Spouse died this tick
            if (!spouseAlive && ownersSpouse != null) {
                ownersSpouse.loseHouse();
                ownersSpouse = null;

                // Remove step-children (children not belonging to owner)
                ArrayList<Person> stepKids = new ArrayList<>();
                for (Person child : children) {
                    if (!owner.getChildren().contains(child)) {
                        child.loseHouse();
                        stepKids.add(child);
                    }
                }
                children.removeAll(stepKids);
            }

            return; // Nothing else to do if owner is alive
        }


        // CASE B — Owner dead
        // First remove owner from house
        if (owner != null) {
            owner.loseHouse();
        }


        // CASE B1 — Spouse alive → spouse becomes new owner
        if (spouseAlive) {
            owner = ownersSpouse;
            ownersSpouse = null; // spouse is now owner

            // Remove step-children (children not belonging to new owner)
            ArrayList<Person> stepKids = new ArrayList<>();
            for (Person child : children) {
                if (!owner.getChildren().contains(child)) {
                    child.loseHouse();
                    stepKids.add(child);
                }
            }
            children.removeAll(stepKids);

            owner.occupyHouse(this);
            return;
        }


        // CASE B2 — Both owner and spouse dead → children may inherit
        Person heir = null;

        if (owner != null) {
            Person candidate = owner.getOldestChild();

            if (candidate != null &&
                    candidate.isAlive() &&
                    candidate.getAge() >= candidate.getRace().getReproductionAge()) {

                heir = candidate;
            }
        }

        // Clear spouse reference (they are dead)
        if (ownersSpouse != null) {
            ownersSpouse.loseHouse();
        }
        ownersSpouse = null;


        // ------------------------------------------------------------
        // STEP 4 — Apply inheritance result
        // ------------------------------------------------------------

        if (heir == null) {
            // No eligible heir → house becomes empty
            owner = null;

            // Remove all children occupants
            for (Person child : children) {
                child.loseHouse();
            }
            children.clear();

            return;
        }

        // Heir inherits
        owner = heir;
        owner.occupyHouse(this);

        // Rebuild children list from scratch
        children.clear();
        for (Person child : owner.getChildren()) {
            if (child.isAlive() &&
                    child.getAge() <= child.getRace().getReproductionAge()) {

                addChildOccupant(child);
            }
        }

        // Add spouse if alive
        if (owner.getPartner() != null && owner.getPartner().isAlive()) {
            ownersSpouse = owner.getPartner();
            ownersSpouse.occupyHouse(this);

            for (Person child : ownersSpouse.getChildren()) {
                if (child.isAlive() &&
                        child.getAge() <= child.getRace().getReproductionAge()) {

                    addChildOccupant(child);
                }
            }
        }
        age++;
    }

    public void kickInhabitants(){
        if(this.owner != null){
            this.owner.loseHouse();
            this.owner = null;
        }if(this.ownersSpouse != null){
            this.ownersSpouse.loseHouse();
            this.ownersSpouse = null;
        }

        for(Person child : children){
            child.loseHouse();
        }

        children.clear();
    }

    public static void main(String[]args){
        Race human = Race.instantiateRaces().getFirst();
        House house = new House(true);
        Person owner = new Person("Owner", human, new DNA(human.getRaceNum()), Sex.Male, human.getReproductionAge());
        Person ownersWife = new Person("Owners Wife", human, new DNA(human.getRaceNum()), Sex.Female, human.getReproductionAge());

        owner.getMarried(ownersWife);

        System.out.println(owner.getPartner().getName());
        System.out.println(ownersWife.getPartner().getName());

        ArrayList<Person> people = new ArrayList<>();
        people.add(owner);
        people.add(ownersWife);

        Population p = new Population(people);

        boolean repeat = true;
        Scanner sc = new Scanner(System.in);

        while(repeat){
            String s = sc.next();

            if(s.length() > 1){
                repeat = false;
            }else if(s.equals("A")){
                p.age();
                house.age();
            }else if(s.equals("H")){
                house.setOwner(owner);
            }else if(s.equals("Y")){
                p.ageYear();
                house.ageYear();
            }

            if(!owner.isAlive()){
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Owner is dead");
            }

            if(!ownersWife.isAlive()){
                System.out.println("Owners wife is dead!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }

            for(Person person : p.getPeople()){
                System.out.println(person.getName() + " Age " + person.getAgeInYears() + (person.isMarried() ? " is married" : " is not married"));
                System.out.println(person.isHoused());
            }

            if(house.owner != null) {
                System.out.println(house.owner.getName() + " owns the house");
            }else{
                System.out.println("House does not have an owner");
            }

            if(p.getPeople().size() == 0){
                System.out.println("Everyone is dead");
                System.out.println(house.owner);
                System.out.println(house.ownersSpouse);
                System.out.println(house.children.size());
            }

        }
    }

}
