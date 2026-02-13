package project.People.PeopleTesting;

import project.People.Ageable;
import project.People.PeopleTesting.House;

import java.util.ArrayList;

public class Family implements Ageable {

    //getFirstPartner()
    private Person partner1;//<- if straight or lesbian - Woman. if gay - Man
    //getSecondPartner()
    private Person partner2;//<-if straight or gay - Man. if lesbian - Woman

    private int age;//age(), ageYear(), getAge(), getAgeInYears()

    private ArrayList<Person> children;//getChildren(), addChild(), hasChildren()

    private boolean married;//isMarried(), getMarried()

    private House house;//getHouse(), setHouse(), loseHouse(), isHomeless()

    private int[] food;//getExcessFood(), getTotalConsumption()

    private int money;//getMoney(), makeMoney(), spendMoney()

    public Family(Person partner1){
        this.partner1 = partner1;
        this.partner2 = null;

        this.children = new ArrayList<>();

        this.married = false;

        this.house = null;

        this.food = new int[]{0,0,0};

        this.money = this.partner1.getMoney();

        partner1.spendMoney(this.money);

    }

    public Family(Person partner1, ArrayList<Person> children){
        this.partner1 = partner1;
        this.partner2 = null;

        this.children = children;

        this.married = false;

        this.house = null;

        this.food = new int[]{0,0,0};

        this.money = this.partner1.getMoney();

        partner1.spendMoney(this.money);
    }

    public Family(Person partner1, Person partner2, boolean married){

        this.partner1 = partner1;
        this.partner2 = partner2;

        this.married = married;

        this.children = new ArrayList<>();

        this.house = null;

        this.food = new int[]{0,0,0};

        this.money = this.partner1.getMoney() + this.partner2.getMoney();
        partner1.spendMoney(partner1.getMoney());
        partner2.spendMoney(partner2.getMoney());

    }

    public Family(Person partner1, Person partner2, ArrayList<Person> children, boolean married){
        this.partner1 = partner1;
        this.partner2 = partner2;

        this.married = married;

        this.children = children;

        this.house = null;

        this.food = new int[]{0,0,0};

        this.money = this.partner1.getMoney() + this.partner2.getMoney();
        partner1.spendMoney(partner1.getMoney());
        partner2.spendMoney(partner2.getMoney());

    }

    public Person getFirstPartner() {
        return partner1;
    }

    public Person getSecondPartner() {
        return partner2;
    }

    public boolean inRelationship(){
        return this.partner2 != null;
    }

    public boolean isMarried() {
        return married;
    }

    public boolean getMarried(){
        if(partner2.getDisposition(partner1) >= 100){
            this.married = true;
            return true;
        }
        partner1.interact(partner2, -5);
        return false;
    }

    public boolean hasChildren(){
        return !this.children.isEmpty();
    }

    public ArrayList<Person> getChildren(){
        return this.children;
    }

    public void addChild(Person child){
        this.children.add(child);
    }

    public boolean mergeFamily(Family family){
        if(this.partner2 == null && family.partner2 == null){
            this.partner2 = family.partner1;
            if (family.hasChildren()) {
                for(Person child : family.getChildren()){
                    if(!children.contains(child) && child.isChild()){
                        children.add(child);
                        child.setFamily(this);
                    }
                }
            }
            this.partner2.setFamily(this);
            return true;
        }
        return false;
    }

    @Override
    public void age() {

        this.age++;

        ArrayList<Person> removedChildren = new ArrayList<>();

        for(Person child : this.children){
            if(!child.isChild()){
                removedChildren.add(child);
                child.setFamily(new Family(child));
            }else if(!child.isAlive()){
                removedChildren.add(child);
            }
        }

        children.removeAll(removedChildren);

        if((!partner1.isAlive() || partner1 == null) && (!partner2.isAlive() || partner2 == null)){
            for(Person child : this.children){
                Family newFamily = new Family(child);
                child.setFamily(newFamily);
            }
            children.clear();
        }else if((partner1 != null && partner1.isAlive()) && (!partner2.isAlive() || partner2 == null)){

            ArrayList<Person> childrenGivenUpForAdoption = new ArrayList<>();

            for(Person child : this.children){
                if(!partner1.getChildren().contains(child)){
                    childrenGivenUpForAdoption.add(child);
                    Family newFamily = new Family(child);
                }
            }

            children.removeAll(childrenGivenUpForAdoption);

        }else if((partner1 == null || !partner1.isAlive()) && (partner2 != null && partner2.isAlive())){

            ArrayList<Person> childrenGivenUpForAdoption = new ArrayList<>();

            for(Person child : this.children){
                if(!partner2.getChildren().contains(child)){
                    childrenGivenUpForAdoption.add(child);
                    Family newFamily = new Family(child);
                }
            }

            children.removeAll(childrenGivenUpForAdoption);
        }

        int excessFood = food[0] + food[1] + food[2];

        for(Person child : this.children){
            if(excessFood > 0){
                if(food[2] > 0){
                    food[2]--;
                    excessFood--;
                    child.eat();
                }else if(food[1] > 0){
                    food[1]--;
                    excessFood--;
                    child.eat();
                }else{
                    food[0]--;
                    excessFood--;
                    child.eat();
                }
            }
        }

        if(excessFood > 0){
            if(food[2] > 0){
                food[2]--;
                excessFood--;
                partner1.eat();
            }else if(food[1] > 0){
                food[1]--;
                excessFood--;
                partner1.eat();
            }else{
                food[0]--;
                excessFood--;
                partner1.eat();
            }
        }
        if(excessFood > 0){
            if(food[2] > 0){
                food[2]--;
                excessFood--;
                partner2.eat();
            }else if(food[1] > 0){
                food[1]--;
                excessFood--;
                partner2.eat();
            }else{
                food[0]--;
                excessFood--;
                partner2.eat();
            }
        }

        food[2] = food[1];
        food[1] = food[0];
        food[0] = 0;

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

    public int getMoney(){
        return this.money;
    }

    public void makeMoney(int money){
        if(money > 0){
            this.money += money;
        }
    }

    public boolean spendMoney(int money){
        if(this.money >= money && money > 0){
            this.money -= money;
            return true;
        }
        return false;
    }

    public void setHouse(House house){
        this.house = house;
    }

    public House getHouse(){
        return this.house;
    }

    public void loseHouse(){
        this.house = null;
    }

    public boolean isHomeless(){
        return this.house == null;
    }

    public int getExcessFood(){
        return this.food[0] + this.food[1] + this.food[2];
    }

    public int getTotalFoodConsumption(){
        return (this.children.size()) + (partner1 != null && partner1.isAlive() ? 1 : 0) + (partner2 != null && partner2.isAlive() ? 1 : 0);
    }

    public boolean canHaveChildren(){
        if(!married){
            return false;
        }else if(this.house == null){
            return false;
        }else if(!house.acceptingInhabitants()){
            return false;
        }else if((this.partner1 == null || !this.partner1.isAlive()) || (this.partner2 == null || !this.partner2.isAlive())){
            return false;
        }else if(this.partner1.getSex() == this.partner2.getSex()){
            return false;
        }else if(this.partner1.isElderly() || this.partner2.isElderly() || this.partner1.isChild() || this.partner2.isChild()){
            return false;
        }else if(this.partner1.isPregnant() || this.partner2.isPregnant()){
            return false;
        }
        return true;
    }

}
