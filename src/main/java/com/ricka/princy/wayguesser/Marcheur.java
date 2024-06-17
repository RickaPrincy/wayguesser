package com.ricka.princy.wayguesser;

import java.util.*;

public record Marcheur(String name){
    public List<Rue> marcher(Carte carte, Lieu depart, Lieu destination){
        var rues = this.getLieuRues(depart, carte);
        var trajets = new ArrayList<Rue>();

        if(depart.equals(destination) || rues.isEmpty()){
            return trajets;
        }

        while(!rues.isEmpty()){
            var randomRue = rues.get(getRandomNumber(0, rues.size() - 1));
            trajets.add(randomRue);
            carte.deleteRue(randomRue);

            var subTrajets = marcher(carte, getNextLieu(randomRue, depart), destination);
            trajets.addAll(subTrajets);
            if(containsLieu(trajets.getLast(), destination)){
                return trajets;
            }
            rues = rues.stream().filter(rue -> !rue.equals(randomRue)).toList();
        }
        return trajets;
    }

    private Lieu getNextLieu(Rue rue, Lieu origin){
        return rue.getLieuA().equals(origin) ? rue.getLieuB() : rue.getLieuA();
    }

    public boolean containsLieu(Rue rue, Lieu lieu){
        return rue.getLieuA().equals(lieu) || rue.getLieuB().equals(lieu);
    }

    private List<Rue> getLieuRues(Lieu lieu, Carte carte){
        return carte.rues().stream().filter(rue ->containsLieu(rue, lieu)).toList();
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}