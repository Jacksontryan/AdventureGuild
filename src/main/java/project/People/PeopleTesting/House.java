package project.People.PeopleTesting;

import project.People.Ageable;

public class House implements Ageable {

    private Family family;
    private int maxNumInhabitants;
    private int age;
    private boolean habitable;

    public House(int maxNumInhabitants, boolean habitable) {
        this.maxNumInhabitants = maxNumInhabitants;
        this.habitable = habitable;
        this.age = 0;
        this.family = null;
    }

    public House(Family family, int maxNumInhabitants){

        this.habitable = true;
        this.maxNumInhabitants = maxNumInhabitants;
        this.family = family;
        this.age = 0;

    }

    @Override
    public void age() {
        if(isHabitable()) {
            this.age++;
            if ((this.family != null) && (this.family.getFirstPartner() == null || !this.family.getFirstPartner().isAlive()) && (this.family.getSecondPartner() == null || !this.family.getSecondPartner().isAlive())) {
                family = null;
            }
        }else{
            this.family = null;
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

    public boolean isHabitable() {
        return this.habitable;
    }

    public Family getFamily() {
        return this.family;
    }

    public boolean setFamily(Family family) {
        if(this.family != null || !this.habitable){
            return false;
        }
        this.family = family;
        return true;
    }

    public int getMaxNumInhabitants() {
        return this.maxNumInhabitants;
    }

    public boolean acceptingInhabitants() {

        return (family.getFirstPartner() == null ? 0 : 1) + (family.getSecondPartner() == null ? 0 : 1) + family.getChildren().size() < this.maxNumInhabitants;

    }
}
