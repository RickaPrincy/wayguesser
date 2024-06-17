package com.ricka.princy.wayguesser;

import java.util.*;

public record Marcheur(String name){
    private List<Rue> marcher(Carte carte, Lieu depart, Lieu destination, List<Rue> dejaPasser){
        var rues = this.getLieuRues(depart, carte, dejaPasser);

        if(depart.equals(destination) || rues.isEmpty()){
            return dejaPasser;
        }

        while(!rues.isEmpty()){
            var randomRue = rues.get(getRandomNumber(0, rues.size() - 1));
            dejaPasser.add(randomRue);

            var subTrajets = marcher(carte, getNextLieu(randomRue, depart), destination, dejaPasser);
            if(containsLieu(dejaPasser.getLast(), destination)){
                return dejaPasser;
            }

            dejaPasser.addAll(subTrajets.reversed());
            rues = rues.stream().filter(rue -> rue.equals(randomRue)).toList();
        }

        throw new Error("Unexpected way");
    }

    private Lieu getNextLieu(Rue rue, Lieu origin){
        return rue.getLieuA().equals(origin) ? rue.getLieuB() : rue.getLieuA();
    }

    public List<Rue> marcher(Carte carte, Lieu depart, Lieu destination){
        return marcher(carte, depart, destination, new ArrayList<>());
    }

    public boolean containsLieu(Rue rue, Lieu lieu){
        return rue.getLieuA().equals(lieu) || rue.getLieuB().equals(lieu);
    }

    private List<Rue> getLieuRues(Lieu lieu, Carte carte, List<Rue> excludes){
        return carte.rues().stream().filter(rue ->
            !excludes.contains(rue) && containsLieu(rue, lieu)
        ).toList();
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}