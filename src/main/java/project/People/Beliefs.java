package project.People;

public class Beliefs {

    private int optimalMarriageAge;//age in weeks after reaching reproduction age that this person wants to be married by
    private int optimalPartnerAge;//average age of person this person is attracted to
    private byte maxKids;//max number of kids this person wants to have
    private int ageToKickOutKids;

    private byte opinionHumans;//{-10 : -7 : -4 : 4 : 7 : 10} -10 : -8: Kill or enslave on sight, -7 : -4: Extreme racism, segregation, city "purity", -3 : 3: General Racism. -3 represents getting into verbal or physical fights, 3 represents closeted racism, 4 : 7: willing to stick up for races rights, 8 : 10: willing to marry
    private byte opinionDwarves;
    private byte opinionElves;
    private byte opinionOrcs;

    private boolean proHumanSlavery;
    private boolean proDwarfSlavery;
    private boolean proElfSlavery;
    private boolean proOrcSlavery;

    private byte isGay;//+# = gay, 0 = bi, -# = straight

    private byte greed;
    private byte anger;

    private boolean divorce;//pro divorce when falling out of love, not desire to divorce partner
    private boolean abortion;//true = pro choice, false = pro life
    private boolean adoption;//true = willing to adopt step children and orphans, false = evil step parent

    private byte mentalStability; //0 - 10



}
