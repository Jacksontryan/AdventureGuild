package project.People;

import java.util.ArrayList;

public class Metal {

    private String name;
    private double weightPerCubicFoot;
    private int hardness;

    public Metal(String name, double weightPerCubicFoot, int hardness) {
        this.name = name;
        this.weightPerCubicFoot = weightPerCubicFoot;
        this.hardness = hardness;
    }

    public static ArrayList<Metal> getMetalTypes(){

        Metal bronze = new Metal("Bronze",550, 40);
        Metal iron = new Metal("Iron",500, 50);
        Metal steel = new Metal("Steel",500, 75);

        Metal silver = new Metal("Silver",665, 20);
        Metal gold = new Metal("Gold",1200, 30);

        ArrayList<Metal> metals = new ArrayList<Metal>();

        metals.add(bronze);
        metals.addLast(iron);
        metals.addLast(steel);
        metals.addLast(silver);
        metals.addLast(gold);

        return metals;
    }

}
