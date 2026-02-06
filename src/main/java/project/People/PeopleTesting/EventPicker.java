package project.People.PeopleTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventPicker {

    public static <E> E pick(List<E> list){
        if(list.isEmpty()){
            return null;
        }
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static <E> E pick(List<E> list, List<Integer> chances){
        if(list.isEmpty() || list.size() != chances.size()){
            return null;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.size(); i++){
            total += chances.get(i);
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(indexUncut < chances.get(i)){
                return list.get(i);
            }else{
                indexUncut -= chances.get(i);
            }
        }
        return list.get(index);
    }

    public static <E> E pick(E[] list){
        if(list.length == 0){
            return null;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static <E> E pick(E[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return null;
        }

        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static byte pick(byte[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static byte pick(byte[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static short pick(short[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static short pick(short[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static int pick(int[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static int pick(int[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static long pick(long[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static long pick(long[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static float pick(float[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static float pick(float[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static double pick(double[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static double pick(double[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static char pick(char[] list){
        if(list.length == 0){
            return 0;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static char pick(char[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return 0;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static boolean pick(boolean[] list){
        if(list.length == 0){
            return false;
        }
        Random rand = new Random();
        return list[rand.nextInt(list.length)];
    }

    public static boolean pick(boolean[] list, int[] chances){
        if(list.length == 0 || list.length != chances.length){
            return false;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < list.length; i++){
            total += chances[i];
        }
        int indexUncut = rand.nextInt(total);
        int index = 0;
        for(int i = 0; i < list.length; i++){
            if(indexUncut < chances[i]){
                return list[i];
            }else {
                indexUncut -= chances[i];
            }
        }
        return list[index];
    }

    public static void pick(ArrayList<Thread> threads) throws InterruptedException {
        if(threads.isEmpty()){
            return;
        }
        Random rand = new Random();
        int index = rand.nextInt(threads.size());
        threads.get(index).start();
        threads.get(index).join();
    }

    public static void pick(ArrayList<Thread> threads, ArrayList<Integer> chances) throws InterruptedException {
        if(threads.isEmpty()){
            return;
        }
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < threads.size(); i++){
            total += chances.get(i);
        }
        int index = rand.nextInt(total);
        for(int i = 0; i < threads.size(); i++){
            if(index < chances.get(i)){
                threads.get(i).start();
                threads.get(i).join();
                return;
            }else {
                index -= chances.get(i);
            }
        }
        threads.getLast().start();
        threads.getLast().join();
    }

    public static boolean flipCoin(){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    public static boolean flipWeightedCoin(int trueWeight, int falseWeight){
        Random rand = new Random();
        int total = rand.nextInt(trueWeight + falseWeight);
        if(total > trueWeight){
            return false;
        }else{
            return false;
        }
    }

}
