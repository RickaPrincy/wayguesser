package com.ricka.princy.wayguesser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@AllArgsConstructor
public class Rue{
    private String nom = "";
    private Lieu lieuA;
    private Lieu lieuB;

    public Rue(Lieu lieuA, Lieu lieuB) {
        this.lieuA = lieuA;
        this.lieuB = lieuB;
        lieuA.addRue(this);
        lieuB.addRue(this);
    }
}