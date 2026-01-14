package project.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Map {

    public class Point{

        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public double distance(Point o){
            return Math.sqrt(Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2));
        }

        public String toString(){
            return "(" + x + " " + y + ")";
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public ArrayList<Point> getNeighbors(){
            ArrayList<Point> neighbors = new ArrayList<Point>();

            if(!(x == 0)){
                neighbors.add(new Point(x-1, y));
            }if(!(x == Map.this.grid.length - 1)){
                neighbors.add(new Point(x+1, y));
            }if(!(y == 0)){
                neighbors.add(new Point(x, y-1));
            }if(!(y == Map.this.grid[0].length - 1)){
                neighbors.add(new Point(x, y+1));
            }

            return neighbors;
        }

        public ArrayList<Point> getLandNeighbors(){
            ArrayList<Point> neighbors = getNeighbors();
            ArrayList<Point> landNeighbors = new ArrayList<Point>();

            for(int i = 0; i < neighbors.size(); i++){
                if(Map.this.map[neighbors.get(i).getX()][neighbors.get(i).getY()] == 'l' || Map.this.map[neighbors.get(i).getX()][neighbors.get(i).getY()] == 'c'){
                    landNeighbors.add(neighbors.get(i));
                }
            }

            return landNeighbors;
        }

        public ArrayList<Point> getWaterNeighbors() {
            ArrayList<Point> neighbors = getNeighbors();
            ArrayList<Point> waterNeighbors = new ArrayList<Point>();

            for (int i = 0; i < neighbors.size(); i++) {
                if (Map.this.map[neighbors.get(i).getX()][neighbors.get(i).getY()] == 'w') {
                    waterNeighbors.add(neighbors.get(i));
                }
            }
            return waterNeighbors;
        }
    }

    public class Location{
        ArrayList<Point> points;

        public Location(ArrayList<Point> points){
            this.points = points;
        }

        public ArrayList<Point> getPoints(){
            return points;
        }

        public ArrayList<Point> getNeighbors() {
            ArrayList<Point> neighbors = new ArrayList<>();

            for (Point p : points) {
                for (Point n : p.getNeighbors()) {
                    if (!points.contains(n) && !neighbors.contains(n)) {
                        neighbors.add(n);
                    }
                }
            }
            return neighbors;
        }

    }

    public class City extends Location{

        private String name;

        private final Point center;

        public City(String name, Point center){
            super(new ArrayList<Point>());
            this.center = center;
            points.add(center);
        }

        public City(String name, Point center, ArrayList<Point> points){
            super(points);
            this.center = center;
        }

        public String getName(){
            return name;
        }

        public Point getCenter(){
            return this.center;
        }

        public ArrayList<Point> getPoints(){
            return this.points;
        }

        public String toString(){
            String str = "";
            for(int i = 0; i < points.size(); i++){
                str += points.get(i).toString() + " ";
            }
            return str;
        }

    }

    public class Island extends Location{

        public Island(ArrayList<Point> points){
            super(points);
        }

        public ArrayList<Point> getPoints(){
            return super.points;
        }

        public int getSize(){
            return super.points.size();
        }

        public void fillIsland(){
            for(int i = 0; i < this.points.size(); i++){
                ArrayList<Point> waterNeighbors = points.get(i).getWaterNeighbors();

                grid[points.get(i).x][points.get(i).y] = threshold - 1;
                map[points.get(i).x][points.get(i).y] = 'w';

            }
        }

    }

    public class Lake extends Location {

        public Lake(ArrayList<Point> points){
            super(points);
        }

        public ArrayList<Point> getPoints(){
            return super.points;
        }

        public int getSize(){
            return super.points.size();
        }

        public void fillLake(){
            for(int i = 0; i < this.points.size(); i++){
                grid[points.get(i).x][points.get(i).y] = threshold + 1;
                map[points.get(i).x][points.get(i).y] = 'l';
            }
        }

    }

    private double[][] grid;
    private char[][] map;
    private boolean[][] originalOcean;
    private double threshold;
    private ArrayList<City> cities =  new ArrayList<City>();
    private ArrayList<Island> islands;
    private ArrayList<Lake> lakes;

    public Map(int width, int height, double threshold){
        this.grid = new double[width][height];
        //this.grid = WorleyNoise.generate(width,height,300);
        this.threshold = threshold;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.grid[i][j] = Math.random();
            }
        }
        instantiateMap();
        islands = getAllIslands(map);
        lakes = getAllLakes(map);
    }

    public void fillAllIslands() {
        boolean changed;

        do {
            changed = false;

            // 1. Identify the mainland (largest island)
            int biggest = 0;
            Island mainland = null;

            for (Island island : islands) {
                if (island.getSize() > biggest) {
                    biggest = island.getSize();
                    mainland = island;
                }
            }

            // Safety: if no islands exist, stop
            if (mainland == null) {
                return;
            }

            // 2. Build a fast lookup set of mainland tiles
            HashSet<Point> mainlandTiles = new HashSet<>(mainland.getPoints());

            // 3. Remove all islands that are NOT the mainland
            int i = 0;
            while (i < islands.size()) {
                Island island = islands.get(i);

                // Skip the mainland island entirely
                if (island == mainland) {
                    i++;
                    continue;
                }

                // Extra safety: ensure no overlap with mainland
                boolean overlapsMainland = false;
                for (Point p : island.getPoints()) {
                    if (mainlandTiles.contains(p)) {
                        overlapsMainland = true;
                        break;
                    }
                }

                // If it's a true island, fill it
                if (!overlapsMainland) {
                    island.fillIsland();
                    islands.remove(i);
                    changed = true;
                } else {
                    i++;
                }
            }

            // 4. Recompute islands if anything changed
            if (changed) {
                islands = getAllIslands(map);
            }

        } while (changed);

        lakes = getAllLakes(map);
        islands = getAllIslands(map);

        System.out.println("Num Islands: " + islands.size());
        System.out.println("Num Lakes: " + lakes.size());
    }

    public void fillAllLakes() {
        boolean changed;

        do {
            changed = false;
            int biggest = 0;

            for (Lake lake : lakes) {
                if (lake.getSize() > biggest) {
                    biggest = lake.getSize();
                }
            }

            int i = 0;
            while (i < lakes.size()) {
                if (lakes.get(i).getSize() != biggest) {
                    lakes.get(i).fillLake();
                    lakes.remove(i);
                    changed = true;
                } else {
                    i++;
                }
            }

            if (changed) {
                lakes = getAllLakes(map);
            }

        } while (changed);

        lakes = getAllLakes(map);
        islands = getAllIslands(map);
        System.out.println("Num Lakes: " + lakes.size());
        System.out.println("Num Islands: " + islands.size());
    }

    public char[][] getMap(){
        return this.map;
    }

    public double getThreshold(){
        return this.threshold;
    }

    public void setThreshold(double threshold){
        this.threshold = threshold;
        this.instantiateMap();
    }

    public ArrayList<City> getCities(){
        return this.cities;
    }


    public ArrayList<Island> getAllIslands(char[][] map) {
        int width = map.length;
        int height = map[0].length;

        boolean[][] visited = new boolean[width][height];
        ArrayList<Island> islands = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (map[x][y] == 'l' && !visited[x][y]) {
                    ArrayList<Point> island = islandFloodCollect(x, y, map, visited);
                    Island i = new Island(island);
                    islands.add(i);
                }
            }
        }

        return islands;
    }

    private ArrayList<Point> islandFloodCollect(int sx, int sy, char[][] map, boolean[][] visited) {
        ArrayList<Point> island = new ArrayList<>();
        Stack<Point> stack = new Stack<>();

        int width = map.length;
        int height = map[0].length;

        int[] dx = {-1,0,0,1};
        int[] dy = {0,-1,1,0};

        stack.push(new Point(sx, sy));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height)
                continue;

            if (visited[x][y])
                continue;

            if (map[x][y] != 'l')
                continue;

            visited[x][y] = true;
            island.add(p);

            for (int i = 0; i < 4; i++) {
                stack.push(new Point(x + dx[i], y + dy[i]));
            }
        }

        return island;
    }

    private boolean touchesBorder(ArrayList<Point> region, int width, int height) {
        for (Point p : region) {
            if (p.x == 0 || p.x == width - 1 || p.y == 0 || p.y == height - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean[][] markOcean(char[][] map) {
        int width = map.length;
        int height = map[0].length;

        boolean[][] ocean = new boolean[width][height];
        Stack<Point> stack = new Stack<>();

        // Push all border water tiles
        for (int x = 0; x < width; x++) {
            if (map[x][0] == 'w') stack.push(new Point(x, 0));
            if (map[x][height - 1] == 'w') stack.push(new Point(x, height - 1));
        }
        for (int y = 0; y < height; y++) {
            if (map[0][y] == 'w') stack.push(new Point(0, y));
            if (map[width - 1][y] == 'w') stack.push(new Point(width - 1, y));
        }

        // Flood fill ocean
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height) continue;
            if (ocean[x][y]) continue;
            if (map[x][y] != 'w') continue;

            ocean[x][y] = true;

            stack.push(new Point(x + 1, y));
            stack.push(new Point(x - 1, y));
            stack.push(new Point(x, y + 1));
            stack.push(new Point(x, y - 1));
        }

        return ocean;
    }

    public ArrayList<Point> getCoast(){
        ArrayList<Point> coast = new ArrayList<>();
        for(int i = 0; i < this.map.length; i++){
            for(int j = 0; j < this.map[0].length; j++){
                if((this.map[i][j] == 'l' || this.map[i][j] == 'c')){
                    for(Point neighbor: new Point(i,j).getNeighbors()){
                        if(this.map[neighbor.x][neighbor.y] == 'w'){
                            coast.add(neighbor);
                        }
                        break;
                    }
                }
            }
        }
        return coast;
    }

    public ArrayList<Lake> getAllLakes(char[][] map) {
        int width = map.length;
        int height = map[0].length;

        boolean[][] visited = new boolean[width][height];
        boolean[][] ocean = originalOcean;

        ArrayList<Lake> lakes = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                // Skip ocean water entirely
                if (map[x][y] == 'w' && !ocean[x][y] && !visited[x][y]) {

                    ArrayList<Point> points = lakeFloodCollect(x, y, map, visited);

                    // This is guaranteed to be a lake
                    lakes.add(new Lake(points));
                }
            }
        }

        return lakes;
    }

    private ArrayList<Point> lakeFloodCollect(int sx, int sy, char[][] map, boolean[][] visited) {
        ArrayList<Point> lake = new ArrayList<>();
        Stack<Point> stack = new Stack<>();

        stack.push(new Point(sx, sy));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int x = p.x;
            int y = p.y;

            // bounds check
            if (x < 0 || x >= map.length || y < 0 || y >= map[0].length)
                continue;

            // already processed
            if (visited[x][y])
                continue;

            // ❗ FIXED: must be water, not land
            if (map[x][y] != 'w')
                continue;

            visited[x][y] = true;
            lake.add(p);

            // explore neighbors
            stack.push(new Point(x + 1, y));
            stack.push(new Point(x - 1, y));
            stack.push(new Point(x, y + 1));
            stack.push(new Point(x, y - 1));
        }

        return lake;
    }


    public void instantiateMap(){
        int width = grid.length;
        int height = grid[0].length;
        map = new char[width][height];
        originalOcean = markOcean(map);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {


                double nx = (i - width / 2.0) / (width / 2.0);
                double ny = (j - height / 2.0) / (height / 2.0);
                double distance = Math.sqrt(nx * nx + ny * ny);

                //double value = grid[i][j] - distance;

                double falloff = Math.pow(distance, 2);
                double value = grid[i][j] - falloff;


                if (value > threshold) {
                    map[i][j] = 'l';
                } else {
                    map[i][j] = 'w';
                }
            }
        }
        if(!cities.isEmpty()) {

            int count = 0;
            while(count < cities.size()) {
                City c = cities.get(count);
                ArrayList<Point> points = c.getPoints();
                for(int i = 0; i < points.size(); i++){
                    Point p = points.get(i);
                    if(map[p.x][p.y] == 'l'){
                        map[p.x][p.y] = 'c';
                    }else{
                        points.remove(p);
                        i--;
                        if(c.getPoints().isEmpty()) {
                            cities.remove(c);
                            count--;
                        }
                    }
                }
                count++;
            }

        }
        lakes = getAllLakes(map);
        islands = getAllIslands(map);
    }

    public void blur(int iterations) {
        int w = grid.length;
        int h = grid[0].length;

        for (int it = 0; it < iterations; it++) {
            double[][] temp = new double[w][h];

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {

                    double sum = 0;
                    int count = 0;

                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;

                            if (nx >= 0 && nx < w && ny >= 0 && ny < h) {
                                sum += grid[nx][ny];
                                count++;
                            }
                        }
                    }
                    temp[x][y] = sum / count;
                }
            }
            grid = temp;
        }
        instantiateMap();
    }

    public void addCities(int numCities) {

        //cities = new ArrayList<>();
        Random rand = new Random();
        int size =  numCities + cities.size();

        while (cities.size() < size) {

            int x = rand.nextInt(map.length);
            int y = rand.nextInt(map[0].length);

            if (map[x][y] != 'l') continue;

            Point p = new Point(x, y);

            boolean valid = cities.stream().allMatch(c -> c.getCenter().distance(p) > 50);

            if (valid) {
                createCity("TEMP", x, y);
            }
        }
    }

    private void createCity(String name, int x, int y) {
        Point center = new Point(x, y);
        ArrayList<Point> points = new ArrayList<Point>();
        for(int width = -5; width <= 5; width++){
            for(int height = -5; height <= 5; height++){
                if(map[x+width][y+height] == 'l'){
                    points.add(new Point(x + width, y + height));
                    map[x+width][y+height] = 'c';
                }
            }
        }
        cities.add(new City(name, center, points));
    }

    public int getLeftMostCoordinate() {
        int width = map.length;
        int height = map[0].length;

        int leftMost = width; // start beyond right edge

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] == 'l') {
                    leftMost = x;
                    return leftMost; // earliest possible, so return immediately
                }
            }
        }
        return leftMost;
    }

    public int getRightMostCoordinate() {
        int width = map.length;
        int height = map[0].length;

        int rightMost = 0;

        for (int x = width - 1; x >= 0; x--) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] == 'l') {
                    rightMost = x;
                    return rightMost;
                }
            }
        }
        return rightMost;
    }

    public int getTopMostCoordinate() {
        int width = map.length;
        int height = map[0].length;

        int topMost = height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[x][y] == 'l') {
                    topMost = y;
                    return topMost;
                }
            }
        }
        return topMost;
    }

    public int getBottomMostCoordinate() {
        int width = map.length;
        int height = map[0].length;

        int bottomMost = 0;

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (map[x][y] == 'l') {
                    bottomMost = y;
                    return bottomMost;
                }
            }
        }
        return bottomMost;
    }

    public void saveMap() throws FileNotFoundException {
        File directory = new File("src\\main\\java\\project\\Map\\Maps");
        int fileNumber = directory.list().length;
        File file = new File("src\\main\\java\\project\\Map\\Maps\\map" +  fileNumber + ".txt");
        PrintWriter pw = new PrintWriter(file);

        for(int x = 0; x < map.length; x++){
            pw.print(map[x][0]);
            for(int y = 1; y < map[0].length; y++){
                pw.print(map[x][y]);
            }
            pw.println();
        }

        pw.close();
    }

    public void trimMap() {
        int leftMost = getLeftMostCoordinate() - 50;
        //System.out.println("Left most coordinate: " + leftMost);
        int rightMost = getRightMostCoordinate() + 50;
        //System.out.println("Right most coordinate: " + rightMost);
        int topMost = getTopMostCoordinate() - 50;
        //System.out.println("Top most coordinate: " + topMost);
        int bottomMost = getBottomMostCoordinate() + 50;
        //System.out.println("Bottom most coordinate: " + bottomMost);

        int newWidth = rightMost - leftMost + 1;    // inclusive
        int newHeight = bottomMost - topMost + 1;   // inclusive

        char[][] temp = new char[newWidth][newHeight];
        double[][] temp2 = new double[newWidth][newHeight];

        // x = old x, y = old y
        for (int x = leftMost; x <= rightMost; x++) {
            // copy the y‑segment [topMost..bottomMost] from this column
            System.arraycopy(map[x], topMost, temp[x - leftMost], 0, newHeight);
            System.arraycopy(grid[x], topMost, temp2[x - leftMost], 0, newHeight);
        }

        map = temp;
        grid = temp2;
    }

}
