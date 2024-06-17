package com.ricka.princy.wayguesser;

import java.util.*;
import java.util.stream.Collectors;

public record Marcheur(String name){
    public List<Lieu> marcher(Carte carte, Lieu depart, Lieu destination){
        return marcher(carte, depart, destination, new HashSet<>());
    }

    private List<Lieu> marcher(Carte carte, Lieu depart, Lieu destination, Set<Rue> rueDejaVisiter){
        var trajets = new ArrayList<>(Set.of(depart));
        var ruePossibles = obtenirRuesPossibles(carte, depart, rueDejaVisiter);

        if(depart.equals(destination)){
            return trajets;
        }

        while(!ruePossibles.isEmpty()){
            var randomRue = ruePossibles.get(generateRandomIndex(0, ruePossibles.size()));
            var prochainDepart = obtenirProchainLieu(randomRue, depart);

            rueDejaVisiter.add(randomRue);
            trajets.addAll(marcher(carte, prochainDepart, destination, rueDejaVisiter));

            if(trajets.getLast().equals(destination)){
                return trajets;
            }
            trajets.add(depart);
            ruePossibles.remove(randomRue);
        }
        return trajets;
    }

    private List<Rue> obtenirRuesPossibles(Carte carte, Lieu depart, Set<Rue> ruesDejaVisitees) {
        return carte.rues()
                .stream()
                .filter(rue -> !ruesDejaVisitees.contains(rue) && rue.relie(depart))
                .collect(Collectors.toList());
    }

    static private final Random RANDOM = new Random();
    private static int generateRandomIndex(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

    private static Lieu obtenirProchainLieu(Rue rue, Lieu departActuel){
        return rue.getLieuA().equals(departActuel) ? rue.getLieuB() : rue.getLieuA();
    }
}