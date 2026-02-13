package project.People.PeopleTesting;

import project.People.Race;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Name {

    private String firstName;
    private String lastName;

    private static final char[] chars = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public Name(Race race){

    }

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    private void setLastName(String lastName){
        this.lastName = lastName;
    }

    public static ArrayList<String> getHumanMaleNames() throws FileNotFoundException {
        ArrayList<String> humanMaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\HumanMaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(!humanMaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")){
                humanMaleNames.add(name);
                char start = name.charAt(0);
                for(int i = 0; i < chars.length; i++){
                    if(start == chars[i]){
                        totals[i]++;
                    }
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return humanMaleNames;
    }

    public static ArrayList<String> getHumanFemaleNames() throws FileNotFoundException {
        ArrayList<String> humanFemaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\HumanFemaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(!humanFemaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                humanFemaleNames.add(name);
                char start = name.charAt(0);
                for(int i = 0; i < chars.length; i++){
                    if(start == chars[i]){
                        totals[i]++;
                    }
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return humanFemaleNames;
    }

    public static ArrayList<String> getHumanLastNames() throws FileNotFoundException {
        ArrayList<String> humanLastNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\HumanLastNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!humanLastNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                humanLastNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return humanLastNames;
    }

    public static ArrayList<String> getElfMaleNames() throws FileNotFoundException {
        ArrayList<String> elfMaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\ElfMaleNames2");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!elfMaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                elfMaleNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return elfMaleNames;
    }

    public static ArrayList<String> getElfFemaleNames() throws FileNotFoundException {
        ArrayList<String> elfFemaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\ElfFemaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!elfFemaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                elfFemaleNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return elfFemaleNames;
    }

    public static ArrayList<String> getElfLastNames() throws FileNotFoundException {
        ArrayList<String> elfLastNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\ElfLastNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!elfLastNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                elfLastNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return elfLastNames;
    }

    public static ArrayList<String> getDwarfMaleNames() throws FileNotFoundException {
        ArrayList<String> dwarfMaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\DwarfMaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!dwarfMaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                dwarfMaleNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return dwarfMaleNames;
    }

    public static ArrayList<String> getDwarfFemaleNames() throws FileNotFoundException {
        ArrayList<String> dwarfFemaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\DwarfFemaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!dwarfFemaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                dwarfFemaleNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return dwarfFemaleNames;
    }

    public static ArrayList<String> getDwarfLastNames() throws FileNotFoundException {
        ArrayList<String> dwarfLastNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\DwarfLastNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!dwarfLastNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                dwarfLastNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return dwarfLastNames;
    }

    public static ArrayList<String> getOrcMaleNames() throws FileNotFoundException {
        ArrayList<String> orcMaleNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\OrcMaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!orcMaleNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                orcMaleNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return orcMaleNames;
    }

    public static ArrayList<String> getOrcFemaleNames() throws FileNotFoundException {
        ArrayList<String> orcFemale = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\OrcFemaleNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!orcFemale.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                orcFemale.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return orcFemale;
    }

    public static ArrayList<String> getOrcLastNames() throws FileNotFoundException {
        ArrayList<String> orcLastNames = new ArrayList<>();
        int[] totals = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        File file = new File("src\\main\\java\\project\\People\\PeopleTesting\\OrcLastNames");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if(name.isEmpty()){
                name = scanner.nextLine();
            }
            if(!orcLastNames.contains(name) && !name.isEmpty() && !name.contains(" ")) {
                orcLastNames.add(name);
            }
            char start = name.charAt(0);
            for(int i = 0; i < chars.length; i++){
                if(start == chars[i]){
                    totals[i]++;
                }
            }
        }
        for(int i = 0; i < chars.length; i++){
            if(totals[i] == 0){
                System.out.println("Letter " + chars[i] + " does not have any names");
            }
        }
        return orcLastNames;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(getHumanMaleNames().size());
        System.out.println(getHumanFemaleNames().size());
        System.out.println(getHumanLastNames().size());
        System.out.println(getElfMaleNames().size());
        System.out.println(getElfFemaleNames().size());
        System.out.println(getElfLastNames().size());
        System.out.println(getDwarfMaleNames().size());
        System.out.println(getDwarfFemaleNames().size());
        System.out.println(getDwarfLastNames().size());
        System.out.println(getOrcMaleNames().size());
        System.out.println(getOrcFemaleNames().size());
        System.out.println(getOrcLastNames().size());
    }

}
/*

Please generate a text file for each of the following:
1000 fantasy human male first names
1000 fantasy human female first names
500 fantasy human last names
1000 fantasy dwarf male first names
1000 fantasy dwarf female first names
500 fantasy dwarf last names
1000 fantasy elf male first names
1000 fantasy elf female first names
500 fantasy elf last names
1000 fantasy orc male first names
1000 fantasy orc female first names
500 fantasy orc last names

attempt to get 2 names starting with each letter for each race. There may be no more than 250 duplicate names between each of the male and female files.
 */