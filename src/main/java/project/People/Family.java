package project.People;

import java.util.ArrayList;

public class Family implements Ageable{

    private Person partner1;//the person to ask out person2
    private Person partner2;//the person who got asked out

    private ArrayList<Person> children;

    private boolean married;

    private int age;

    public Family(Person partner1, ArrayList<Person> children){
        this.partner1 = partner1;
        this.married = false;
        this.children = children;
    }

    public Family(Person partner1, Person partner2) {
        this.partner1 = partner1;
        this.partner2 = partner2;
        this.married = false;
        this.children = new ArrayList<>();
    }

    @Override
    public void age() {

        this.age++;

        ArrayList<Person> adultChildren = new ArrayList<>();

        for (Person child : this.children) {
            if(child.getAge() >= child.getRace().getReproductionAge()){
                adultChildren.add(child);
            }
        }

        this.children.removeAll(adultChildren);

    }

    @Override
    public void ageYear() {
        for (int i = 0; i < 52; i++){
            age();
        }
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public int getAgeInYears() {
        return this.age/52;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public ArrayList<Family> splitFamily(){
        ArrayList<Family> familyList = new ArrayList<Family>();
        ArrayList<Person> children1 = new ArrayList<>();
        ArrayList<Person> children2 = new ArrayList<>();
        for (Person child : this.children) {
            if(child.getFather().equals(partner1) || child.getMother().equals(partner1)){
                children1.add(child);
            }if(child.getFather().equals(partner2) || child.getMother().equals(partner2)){
                children2.add(child);
            }
        }
        Family family1 = new Family(this.partner1, children1);
        Family family2 = new Family(this.partner2, children2);
        familyList.add(family1);
        familyList.add(family2);
        return familyList;
    }
}
