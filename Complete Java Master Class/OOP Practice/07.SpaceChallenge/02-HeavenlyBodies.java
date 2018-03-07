package com.georgiev;

import java.util.*;

public class HeavenlyBodies {
    private final String name;
    private final int earthDays;
    private Set<HeavenlyBodies> satellites;
    private String bodyType;


    public HeavenlyBodies(String name, int earthDays, String bodyType) {
        this.name = name;
        this.earthDays = earthDays;
        this.bodyType = bodyType;
        this.satellites = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public int getEarthDays() {
        return earthDays;
    }

    public Set<HeavenlyBodies> getSatellites() {
        return satellites;
    }

    public String getBodyType() {
        return bodyType;
    }

    public class Planet extends HeavenlyBodies {

        public Planet(String name, int earthDays, String bodyType) {
            super(name, earthDays, bodyType);
        }

        public String getName() {
            return name;
        }

        public int getEarthDays() {
            return earthDays;
        }
    }

    public class Moon extends HeavenlyBodies {

        public Moon(String name, int earthDays, String bodyType) {
            super(name, earthDays, bodyType);
        }

        public String getName() {
            return name;
        }

        public int getEarthDays() {
            return earthDays;
        }
    }

    public class Star extends HeavenlyBodies {

        public Star(String name, int earthDays, String bodyType) {
            super(name, earthDays, bodyType);
        }

        public String getName() {
            return name;
        }

        public int getEarthDays() {
            return earthDays;
        }
    }
}
