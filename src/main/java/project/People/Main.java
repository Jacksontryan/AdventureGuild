package project.People;

public class Main {

    public static void main(String[] args) {

        // Create a city with a few people
        City city = new City(5, 0, 0, 0);

        // Randomize population so adults exist
        city.randomizePopulationArray();

        // Pick one adult
        Person worker = null;
        for (Person p : city.getHumans().getPeople()) {
            if (p.isAdult()) {
                worker = p;
                break;
            }
        }

        if (worker == null) {
            System.out.println("No adults found — test aborted.");
            return;
        }

        System.out.println("Testing job assignment for: " + worker.getName());
        System.out.println("Adult? " + worker.isAdult());
        System.out.println("Has job? " + worker.hasJob());

        // Test each organization manually
        System.out.println("\n--- FARM TEST ---");
        for (City.Farm farm : city.getFarms()) {
            System.out.println("Farm acceptingWorkers(): " + farm.acceptingWorkers());
            System.out.println("Farm addWorker(): " + farm.addWorker(worker));
        }

        System.out.println("\n--- MINE TEST ---");
        for (City.Mine mine : city.getMines()) {
            System.out.println("Mine acceptingWorkers(): " + mine.acceptingWorkers());
            System.out.println("Mine addWorker(): " + mine.addWorker(worker));
        }

        System.out.println("\n--- BLACKSMITH TEST ---");
        for (City.Blacksmith b : city.getBlacksmiths()) {
            System.out.println("Blacksmith acceptingWorkers(): " + b.acceptingWorkers());
            System.out.println("Blacksmith addWorker(): " + b.addWorker(worker));
        }

        System.out.println("\n--- ORPHANAGE TEST ---");
        System.out.println("Orphanage acceptingWorkers(): " + city.getOrphanage().acceptingWorkers());
        System.out.println("Orphanage addWorker(): " + city.getOrphanage().addWorker(worker));

        System.out.println("\n--- BARRACKS TEST ---");
        System.out.println("Barracks acceptingWorkers(): " + city.getBarracks().acceptingWorkers());
        System.out.println("Barracks addWorker(): " + city.getBarracks().addWorker(worker));

        System.out.println("\n--- BUILDERS GUILD TEST ---");
        System.out.println("BuildersGuild acceptingWorkers(): " + city.getBuildersGuild().acceptingWorkers());
        System.out.println("BuildersGuild addWorker(): " + city.getBuildersGuild().addWorker(worker));

        System.out.println("\n--- PICKJOB TEST ---");
        worker.fire(); // ensure unemployed
        //city.pickJob(worker);
        System.out.println("After pickJob(), worker job = " + worker.getJob().getName());
    }
}