package project.NPC;

public class Attack {

    private final int damage;
    private final DamageType damageType;

    public Attack(int damage, DamageType damageType) {
        this.damage = damage;
        this.damageType = damageType;
    }

    public int getDamage() {
        return damage;
    }

    public DamageType getDamageType() {
        return damageType;
    }

}
