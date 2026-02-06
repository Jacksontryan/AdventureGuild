package project.People;

import java.util.Random;

public class DNA {

    private int race;
    private double[] sideA;
    private double[] sideB;

    public DNA(int race){
        sideA = new double[race];
        sideB = new double[race];
        this.race = race;

        for(int i=0;i<race;i++){
            sideA[i] = Math.random();
            sideB[i] = Math.random();
        }
    }

    public DNA(int race, double[] sideA, double[] sideB){

        if(sideA.length!= race || sideB.length!= race){
            throw new IllegalArgumentException("Dimensions doesn't match");
        }

        this.race = race;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public int getRace(){
        return race;
    }

    public double[] getSideA(){
        return sideA;
    }

    public double[] getSideB(){
        return sideB;
    }

    public double[] getHalf(){
        double[] half = new double[race];
        Random rand = new Random();
        for(int i=0;i<race;i++){
            int side = rand.nextInt(2);
            half[i] = side == 0?sideA[i]:sideB[i];
            //System.out.println(half[i]);
        }
        return half;
    }

    public double getPercentageShared(DNA other){

        int shared = 0;

        for(int i=0;i<race;i++){
            if(sideA[i]==other.sideA[i]){
                shared++;
                if(sideB[i]==other.sideB[i]){
                    shared++;
                }
            }else if(sideA[i]==other.sideB[i]){
                shared++;
                if(sideB[i]==other.sideA[i]){
                    shared++;
                }
            }else if(sideB[i]==other.sideA[i]){
                shared++;
            }else if(sideB[i]==other.sideB[i]){
                shared++;
            }
        }

        return 1.0 * shared / (race * 2);
    }

    public DNA reproduce(DNA other){
        if(race!=other.race){
            throw new IllegalArgumentException("These Races Can Not Reproduce Together");
        }
        double[] sideA = getHalf();
        double[] sideB = other.getHalf();

        for(int i=0;i<race;i++){
            Random rand = new Random();
            int choice = rand.nextInt(2);
            if(choice==0){
                double temp = sideA[i];
                sideA[i] = sideB[i];
                sideB[i] = temp;
            }
        }
        return new DNA(race, sideA, sideB);
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof DNA)){
            return false;
        }
        if(this.race!=((DNA)other).race){
            return false;
        }
        DNA otherDNA = (DNA)other;
        for(int i=0;i<race;i++){
            if(this.sideA[i]!=otherDNA.sideA[i] || this.sideB[i]!=otherDNA.sideB[i]){
                return false;
            }
        }
        return true;
    }

    public void mutate(){
        Random rand = new Random();
        int chromosome = rand.nextInt(race);
        int side = rand.nextInt(2);
        if(side==0){
            sideA[chromosome] = Math.random();
        }else{
            sideB[chromosome] = Math.random();
        }
    }

    public static void main(String[] args){

        DNA dad = new DNA(100);
        DNA mom = new DNA(100);

        System.out.println("Husband DNA shared with wife: " + dad.getPercentageShared(mom));

        DNA child1 = dad.reproduce(mom);
        DNA child2 = dad.reproduce(mom);
        DNA child3 = dad.reproduce(mom);

        System.out.println("Child 1 DNA shared with dad: " + child1.getPercentageShared(dad));
        System.out.println("Child 1 DNA shared with mom: " + child1.getPercentageShared(mom));

        System.out.println("Child 2 DNA shared with dad: " + child2.getPercentageShared(dad));
        System.out.println("Child 2 DNA shared with mom: " + child2.getPercentageShared(mom));

        System.out.println("Child 1 DNA shared with child 2: " + child1.getPercentageShared(child2));
        System.out.println("Child 1 DNA shared with child 3: " + child1.getPercentageShared(child3));
        System.out.println("Child 2 DNA shared with child 3: " + child2.getPercentageShared(child3));
    }

}
