package project.People.PeopleTesting;

public class Beliefs {

    private Person self;

    private int optimalMarriageAge;//age in weeks after reaching reproduction age that this person wants to be married by
    private int optimalPartnerAge;//average age of person this person is attracted to
    private byte maxKids;//max number of kids this person wants to have
    private int ageToKickOutKids;

    private byte opinionHumans;//{-10 : -7 : -3 : 3 : 7 : 10} -10 : -8: Kill or enslave on sight, -7 : -4: Extreme racism, segregation, city "purity", -3 : 3: General Racism. -3 represents getting into verbal or physical fights, 3 represents talking about other races behind their backs, 4 : 7: peaceful coexistence, possibly some closeted racism at 4, 8 : 10: willing to stick up for rights of race or even potentially marry
    private byte opinionDwarves;
    private byte opinionElves;
    private byte opinionOrcs;

    private boolean proHumanSlavery;
    private boolean proDwarfSlavery;
    private boolean proElfSlavery;
    private boolean proOrcSlavery;

    private char sexuality;//a = asexual, b = bisexual, g = gay/lesbian s = straight

    private byte greed;
    private byte anger;

    private boolean divorce;//pro divorce when falling out of love, not desire to divorce partner
    private boolean abortion;//true = pro choice, false = pro life
    private boolean adoption;//true = willing to adopt step children and orphans, false = evil step parent

    private byte mentalStability; //0 - 10

    public Beliefs(Person self){
        this.self = self;
        this.optimalMarriageAge = this.self.getRace().getReproductionAge();
        this.optimalPartnerAge = this.self.getRace().getReproductionAge();

    }

}
