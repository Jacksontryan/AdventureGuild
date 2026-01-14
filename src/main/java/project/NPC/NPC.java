package project.NPC;

public abstract class NPC {

    String name;

    private int level;
    private int age; //in weeks from 0
    private Gender gender;

    private int maxHealth;
    private int health;

    private int attack;
    private int defence;

    private int magicAttack;
    private int magicDefence;

    private int evasion;
    private int accuracy;

    private int speed;

    public NPC(String name, int level, int age, Gender gender, int maxHealth, int attack, int defence, int magicAttack, int magicDefence, int evasion, int accuracy, int speed){
        this.name = name;

        this.level = level;
        this.age = age;
        this.gender = gender;

        this.maxHealth = maxHealth;
        this.health = maxHealth;

        this.attack = attack;
        this.defence = defence;

        this.magicAttack = magicAttack;
        this.magicDefence = magicDefence;

        this.evasion = evasion;
        this.accuracy = accuracy;

        this.speed = speed;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public int getAge(){
        return age;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public int getHealth(){
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

    public abstract void levelUp();

    public void age(){
        this.age++;
    }

}
