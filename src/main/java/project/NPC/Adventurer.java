package project.NPC;

import java.io.*;
import java.util.Random;

public class Adventurer {

    private final String name;

    private int level;

    private int maxHealth;
    private int health;

    private int attack;
    private int defence;

    private int magicAttack;
    private int magicDefence;

    private int evasion;
    private int accuracy;

    private int speed;

    private File levelFile;
    private PrintWriter levelWriter;

    private Classes classification;

    enum Classes{
        Adventurer,

        Merchant,

        Fighter,

        Scholar,

        Rogue
    }

    public Adventurer(String name) throws FileNotFoundException {
        this.name = name;
        this.level = 1;

        this.maxHealth = new Random().nextInt(10,20);
        this.health = maxHealth;

        this.attack = new Random().nextInt(5,10);
        this.defence = new Random().nextInt(5,10);

        this.magicAttack = new Random().nextInt(5,10);
        this.magicDefence = new Random().nextInt(5,10);

        this.evasion = new Random().nextInt(5, 10);
        this.accuracy = new Random().nextInt(5,10);

        this.speed = new Random().nextInt(5,10);
        this.classification = Classes.Adventurer;

        this.levelFile = new File("src\\main\\java\\project\\Adventurers\\" + name + ".txt");
        this.levelWriter = new PrintWriter(levelFile);
        levelWriter.println(this.toString());
        levelWriter.println();
        levelWriter.close();
    }

    public void choseClass(){
        int total = attack + defence + magicAttack + magicDefence + evasion + accuracy + speed;
        int random = new Random().nextInt(1, total);
        if(random <= attack + defence){
            this.classification = Classes.Fighter;
        }else if(random <= attack + defence + magicAttack + magicDefence){
            this.classification = Classes.Scholar;
        }else if(random <= attack + defence + magicAttack +  magicDefence + evasion + accuracy){
            this.classification = Classes.Rogue;
        }else{
            this.classification = Classes.Merchant;
        }
    }

    public void levelUp() throws IOException {
        FileWriter fw = new FileWriter(levelFile, true);

        levelWriter = new PrintWriter(fw);

        if(this.level > 99){
            return;
        }

        int roof = 0;

        int hpFloor = 0;

        int atkFloor = 0;
        int defFloor = 0;

        int matkFloor = 0;
        int mdefFloor = 0;

        int evaFloor = 0;
        int accFloor = 0;

        int spdFloor = 0;

        if(this.classification == Classes.Merchant){
            evaFloor = 1;
            spdFloor = 1;
        }

        else if(this.classification == Classes.Fighter){
            atkFloor = 1;
            defFloor = 1;
        }

        else if(this.classification == Classes.Scholar){
            matkFloor = 1;
            mdefFloor = 1;
        }

        else if(this.classification == Classes.Rogue){
            evaFloor = 1;
            accFloor = 1;
        }

        if(level <= 25){
            roof = 2;
        }else if(level <= 50){
            roof = 4;
        }else if(level <= 75){
            roof = 6;
        }else {
            roof = 8;
        }

        roof++;

        level++;

        int hpGrowth = new Random().nextInt(hpFloor, 2 * roof);

        int atkGrowth = new Random().nextInt(atkFloor, roof);
        int defGrowth = new Random().nextInt(defFloor, roof);

        int matkGrowth = new Random().nextInt(matkFloor, roof);
        int mdefGrowth = new Random().nextInt(mdefFloor, roof);

        int evaGrowth = new Random().nextInt(evaFloor, roof);
        int accGrowth = new Random().nextInt(accFloor, roof);

        int spdGrowth = new Random().nextInt(spdFloor, roof);

        this.maxHealth += hpGrowth;
        this.health += hpGrowth;

        this.attack += atkGrowth;
        this.defence += defGrowth;

        this.magicAttack += matkGrowth;
        this.magicDefence += mdefGrowth;

        this.evasion += evaGrowth;
        this.accuracy += accGrowth;

        this.speed += spdGrowth;

        if(this.level == 25){
            choseClass();
        }

        levelWriter.println("Health Growth: " + hpGrowth + " Attack Growth: " + atkGrowth + " Defence Growth: " + defGrowth + " Magic Attack Growth: " + matkGrowth + " Magic Defence Growth: " + mdefGrowth + " Evasion Growth: " + evaGrowth + " Accuracy: " + accGrowth + " Speed: " + spdGrowth);

        levelWriter.println();

        levelWriter.println(this.toString());

        levelWriter.println();

        levelWriter.close();

    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefence(){
        return defence;
    }

    public int getMagicAttack(){
        return magicAttack;
    }

    public int getMagicDefence(){
        return magicDefence;
    }

    public int getEvasion(){
        return evasion;
    }

    public int getAccuracy(){
        return accuracy;
    }

    public int getSpeed(){
        return speed;
    }

    public Classes getClassification(){
        return classification;
    }

    public String toString(){
        return "Name: " + getName() + " Level: " + getLevel() + " Max Health: " + getMaxHealth() + " Attack: " + getAttack() + " Defence: " + getDefence() + " Magic Attack: " + getMagicAttack() + " Magic Defence: " + getMagicDefence() + " Evasion: " + getEvasion() + " Accuracy: " + getAccuracy() + " Speed: " + getSpeed() +  " Class: " + getClassification();
    }

    public static void main(String[] args) throws IOException {
        int numMerchants = 0;
        int numFighters = 0;
        int numScholars = 0;
        int numRogue = 0;

        for(int j = 0; j <= 100; j++) {
            Adventurer adventurer = new Adventurer("Adventurer" + j);
            for (int i = 1; i < 100; i++) {
                adventurer.levelUp();
            }
            if(adventurer.getClassification().equals(Classes.Fighter)){
                numFighters++;
            }else if(adventurer.getClassification().equals(Classes.Merchant)){
                numMerchants++;
            }else if(adventurer.getClassification().equals(Classes.Scholar)){
                numScholars++;
            }else if(adventurer.getClassification().equals(Classes.Rogue)){
                numRogue++;
            }
            //System.out.println();
        }

        System.out.println("Fighters: " + numFighters);
        System.out.println("Merchants: " + numMerchants);
        System.out.println("Scholars: " + numScholars);
        System.out.println("Rogues: " + numRogue);
    }

}
