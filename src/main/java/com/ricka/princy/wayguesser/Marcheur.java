package com.ricka.princy.wayguesser;

import java.util.*;

public record Marcheur(String name){
    public List<Rue> marcher(Carte carte, Lieu depart, Lieu destination){
        return marcher(carte, depart, destination, new HashSet<>());
    }

    private List<Rue> marcher(Carte carte, Lieu depart, Lieu destination, Set<Rue> rueDejaVisiter){
        var rues = getLieuRues(depart, carte, rueDejaVisiter);
        var trajets = new ArrayList<Rue>();

        if(depart.equals(destination) || rues.isEmpty()){
            return trajets;
        }

        while(!rues.isEmpty()){
            var randomRue = rues.get(getRandomNumber(0, rues.size() - 1));
            trajets.add(randomRue);
            rueDejaVisiter.add(randomRue);

            var subTrajets = marcher(carte, getNextDestination(randomRue, depart), destination, rueDejaVisiter);
            trajets.addAll(subTrajets);
            if(containsLieu(trajets.getLast(), destination)){
                return trajets;
            }

            trajets.add(randomRue);
            rues = rues.stream().filter(rue -> !rue.equals(randomRue)).toList();
        }
        return trajets;
    }

    private static Lieu getNextDestination(Rue rue, Lieu origin){
        return rue.getLieuA().equals(origin) ? rue.getLieuB() : rue.getLieuA();
    }

    public static boolean containsLieu(Rue rue, Lieu lieu) {
        return rue.getLieuA().equals(lieu) || rue.getLieuB().equals(lieu);
    }

    private static List<Rue> getLieuRues(Lieu lieu, Carte carte,Set<Rue> excludes ){
        return carte.rues().stream().filter(rue -> !excludes.contains(rue)  && containsLieu(rue, lieu)).toList();
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}