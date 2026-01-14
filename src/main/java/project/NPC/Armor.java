package project.NPC;

public class Armor {

    private final String name;

    private final int blunt;
    private final int pierce;
    private final int slash;

    private final int fire;
    private final int lightning;
    private final int ice;
    private final int magic;
    private final int holy;

    private final boolean[] covers;//head,body,arms,legs

    public Armor(String name, int blunt, int pierce, int slash, int fire, int lightning, int ice, int magic, int holy, boolean[] covers) {
        this.name = name;
        this.blunt = blunt;
        this.pierce = pierce;
        this.slash = slash;
        this.fire = fire;
        this.lightning = lightning;
        this.ice = ice;
        this.magic = magic;
        this.holy = holy;
        this.covers = covers;
        if(covers.length!=4){
            throw new IllegalArgumentException("The length of the covers array should be 4");
        }
    }

    public String getName() {
        return name;
    }

    public int getBlunt() {
        return blunt;
    }

    public int getPierce() {
        return pierce;
    }

    public int getSlash() {
        return slash;
    }

    public int getFire() {
        return fire;
    }

    public int getLightning() {
        return lightning;
    }

    public int getIce() {
        return ice;
    }

    public int getMagic() {
        return magic;
    }

    public int getHoly() {
        return holy;
    }

    public boolean[] getCovers() {
        return covers;
    }

}
