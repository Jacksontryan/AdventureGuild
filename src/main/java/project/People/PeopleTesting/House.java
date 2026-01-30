package project.People.PeopleTesting;

import java.util.ArrayList;

public class House {

    private Person owner;
    private Person ownersSpouse;
    private ArrayList<Person> children;

    public House(){
        this.owner = null;
        this.ownersSpouse = null;
        this.children = new ArrayList<>();
    }

    public House(Person owner){

    }

}
