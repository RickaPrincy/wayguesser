package com.ricka.princy.wayguesser;

import java.util.Set;

public record Carte(Set<Rue> rues) {
    public void deleteRue(Rue rue){
        this.rues.remove(rue);
    }
}