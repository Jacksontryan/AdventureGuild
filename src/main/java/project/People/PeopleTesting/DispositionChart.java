package project.People.PeopleTesting;

import project.People.Ageable;

import java.util.ArrayList;

public class DispositionChart implements Ageable {

    private Person person;

    private final ArrayList<Person> relationships;
    private final ArrayList<Integer> dispositions;

    public DispositionChart(){
        this.relationships = new ArrayList<>();
        this.dispositions = new ArrayList<>();
    }

    public boolean hasRelationship(Person person){
        return this.relationships.contains(person);
    }

    public int getDisposition(Person person){
        if(hasRelationship(person)){
            return this.dispositions.get(this.relationships.indexOf(person));
        }
        return 0;
    }

    public void addRelationship(Person person, int disposition){
        if(hasRelationship(person)){
            dispositions.set(this.relationships.indexOf(person),dispositions.get(this.relationships.indexOf(person)) + disposition);
        }else{
            this.relationships.add(person);
            this.dispositions.add(disposition);
        }
    }

    @Override
    public void age() {

        for(int i = 0; i < this.relationships.size(); i++){

            if(!this.relationships.get(i).isAlive()){
                this.relationships.remove(i);
                this.dispositions.remove(i);
            }

        }

        if(dispositions.size() != relationships.size()){
            dispositions.clear();
            relationships.clear();
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
        return person.getAge();
    }

    @Override
    public int getAgeInYears() {
        return person.getAgeInYears();
    }
}
