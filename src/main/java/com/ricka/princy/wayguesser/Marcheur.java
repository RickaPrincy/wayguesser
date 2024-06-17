package com.ricka.princy.wayguesser;

import java.util.*;
import java.util.stream.Collectors;

public record Marcheur(String name){
    public List<Lieu> marcher(Carte carte, Lieu depart, Lieu destination){
        return marcher(carte, depart, destination, new HashSet<>());
    }

    private List<Lieu> marcher(Carte carte, Lieu depart, Lieu destination, Set<Rue> ruesDejaVisitees){
        var trajets = new ArrayList<>(Set.of(depart));
        var ruePossibles = obtenirRuesPossibles(carte, depart, ruesDejaVisitees);

        if(depart.equals(destination)){
            return trajets;
        }

        while(!ruePossibles.isEmpty()){
            var randomRue = ruePossibles.get(generateRandomIndex(ruePossibles.size()));
            var prochainDepart = obtenirProchainLieu(randomRue, depart);

            ruesDejaVisitees.add(randomRue);
            trajets.addAll(marcher(carte, prochainDepart, destination, ruesDejaVisitees));

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
    private static int generateRandomIndex(int maxIndex) {
        return RANDOM.nextInt(maxIndex);
    }

    private static Lieu obtenirProchainLieu(Rue rue, Lieu departActuel){
        return rue.getLieuA().equals(departActuel) ? rue.getLieuB() : rue.getLieuA();
    }
}