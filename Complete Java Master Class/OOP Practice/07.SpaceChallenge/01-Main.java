package com.georgiev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MainSpace {
    private static LinkedHashMap<String, Set<HeavenlyBodies>> heavenlyBodiesMap = new LinkedHashMap<>();
    //private static Set<HeavenlyBodies> planets = new HashSet<>();
    //private static Set<HeavenlyBodies> moons = new HashSet<>();
    private static Set<HeavenlyBodies> moons = new HashSet<>();
    private static Set<HeavenlyBodies> planets = new HashSet<>();
    private static Set<HeavenlyBodies> stars = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to GalaxyMap v0.0000000001 Alpha");
        mainMenu(reader);
    }

    private static void mainMenu(BufferedReader reader) throws IOException {
        System.out.println("======================================");
        System.out.println("Please choose from the following menu:");
        System.out.println("Press 1 to add celestial bodies.\nPress 2 to list the results.");
        int selection = Integer.parseInt(reader.readLine());
        switch (selection) {
            case 1:
                addBody();
                break;

            case 2:
                listResults(reader);
                break;

            default:
                System.out.println("Please use a valid command!");
                mainMenu(reader);
                break;
        }
    }

    private static void addBody() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int random = ThreadLocalRandom.current().nextInt(20, 5000);
        String heavenlyType = selectType(reader);
        System.out.print("Enter name of body: ");
        String body = reader.readLine();
        switch (heavenlyType) {
            case "stars":
                if (checkForDups(heavenlyType, body)) return;
                HeavenlyBodies heavenlyBodies = new HeavenlyBodies(body, random, "star");
                HeavenlyBodies.Star star = heavenlyBodies.new Star(heavenlyBodies.getName(),
                        heavenlyBodies.getEarthDays(), heavenlyBodies.getBodyType());
                addStar(star);
                break;

            case "moons":
                if (checkForDups(heavenlyType, body)) return;
                heavenlyBodies = new HeavenlyBodies(body, random, "moon");
                HeavenlyBodies.Moon moon = heavenlyBodies.new Moon(heavenlyBodies.getName(),
                        heavenlyBodies.getEarthDays(), heavenlyBodies.getBodyType());
                addMoon(moon);
                break;

            case "planets":
                if (checkForDups(heavenlyType, body)) return;
                heavenlyBodies = new HeavenlyBodies(body, random, "planet");
                HeavenlyBodies.Planet planet = heavenlyBodies.new Planet(heavenlyBodies.getName(),
                        heavenlyBodies.getEarthDays(), heavenlyBodies.getBodyType());
                addPlanet(planet);
                break;
        }
        addMore(reader);
    }

    private static boolean checkForDups(String heavenlyType, String body) throws IOException {
        if (heavenlyBodiesMap.get(heavenlyType) != null) {
            Set<HeavenlyBodies> check = heavenlyBodiesMap.get(heavenlyType);
            if (!check.isEmpty()) {
                for (HeavenlyBodies heavenlyBodies : check) {
                    if (heavenlyBodies.getName().equals(body)) {
                        System.out.println("Object already exists in this category! Please try again.");
                        addBody();
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private static String selectType(BufferedReader reader) throws IOException {
        System.out.println("Please select the type of object you want to add:");
        System.out.println("1: Star\n2: Planet\n3: Moon");
        int type = Integer.parseInt(reader.readLine());
        String bodyType = "";
        switch (type) {
            case 1:
                bodyType = "stars";
                break;
            case 2:
                bodyType = "planets";
                break;
            case 3:
                bodyType = "moons";
                break;

        }
        return bodyType;
    }

    private static void addPlanet(HeavenlyBodies.Planet planet) {
        planets.add(planet);
        heavenlyBodiesMap.put("planets", planets);
        System.out.println(planet.getName() + " added successfully to Planets.");
    }

    private static void addMoon(HeavenlyBodies.Moon moon) {
        moons.add(moon);
        heavenlyBodiesMap.put("moons", moons);
        System.out.println(moon.getName() + " added successfully to Moons.");
    }

    private static void addStar(HeavenlyBodies.Star star) {
        stars.add(star);
        heavenlyBodiesMap.put("stars", stars);
        System.out.println(star.getName() + " added successfully to Stars.");
    }


    private static void addMore(BufferedReader reader) throws IOException {
        System.out.println("Do you want to add more?");
        System.out.print("Y / N: ");
        String answer = reader.readLine().toUpperCase();
        switch (answer) {
            case "Y":
                addBody();
                return;
            case "N":
                System.out.println("Exiting to main menu.");
                mainMenu(reader);
                break;
            default:
                System.out.println("Please give a valid answer of \"Y\" or \"N\"");
                addMore(reader);
                break;
        }
    }

    private static void listResults(BufferedReader reader) throws IOException {
        if (heavenlyBodiesMap.isEmpty()) {
            System.out.println("No bodies in the database! Add some before you can list them.");
            mainMenu(reader);
        }
        System.out.println("Do you want to list all Planets or all Moons?");
        System.out.println("Press 1 for Planets, 2 for Moons and 3 for Stars");
        int answer = Integer.parseInt(reader.readLine());
        switch (answer) {
            case 1:
                listBodies("planets");
                mainMenu(reader);
                break;
            case 2:
                listBodies("moons");
                mainMenu(reader);
                break;

            case 3:
                listBodies("stars");
                mainMenu(reader);
                break;
            case 4:
                listAll();
                mainMenu(reader);
                break;
            default:
                System.out.println("Please give a valid input.");
                listResults(reader);
                mainMenu(reader);
                break;
        }
    }

    private static void listBodies(String type) {
        System.out.println(type + ":");
        Set<HeavenlyBodies> planetsToList = heavenlyBodiesMap.get(type);
        for (HeavenlyBodies heavenlyBodies : planetsToList) {
            System.out.println("\t" + heavenlyBodies.getName() + " - 1 earth year is: " +
                    heavenlyBodies.getEarthDays() + " Earth days.");
        }
    }

    private static void listAll(){
        LinkedHashMap<String, Set<HeavenlyBodies>> sorted = sortedHashHell();
        for (Map.Entry<String, Set<HeavenlyBodies>> stringSetEntry : sorted.entrySet()) {
            System.out.println(stringSetEntry.getKey() + ": " + stringSetEntry.getValue().size());
            List<HeavenlyBodies> bodies = new ArrayList<>(stringSetEntry.getValue());
            for (int i = 0; i < stringSetEntry.getValue().size(); i++) {
                System.out.println("\t" + bodies.get(i).getName() + " - 1 earth year is: " + bodies.get(i).getEarthDays());
            }
        }

    }

    private static LinkedHashMap<String, Set<HeavenlyBodies>> sortedHashHell() {

        return heavenlyBodiesMap.entrySet().stream().sorted((e1, e2) -> {
            Integer result = Integer.compare(e2.getValue().size(), e1.getValue().size());

            if (result == 0) {
                result = e1.getKey().compareTo(e2.getKey());
            }
            return result;
        })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x,y) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));
    }
}
