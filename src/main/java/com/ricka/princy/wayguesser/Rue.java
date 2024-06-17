package com.ricka.princy.wayguesser;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Rue{
    private String nom = "";
    //TODO: rename all of this
    private Lieu lieuA;
    private Lieu lieuB;

    public Rue(Lieu lieuA, Lieu lieuB) {
        this.lieuA = lieuA;
        this.lieuB = lieuB;
    }
}