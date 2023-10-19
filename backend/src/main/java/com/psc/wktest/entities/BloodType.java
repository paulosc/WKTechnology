package com.psc.wktest.entities;

public enum BloodType {
    A_PLUS("A+"),
    A_LESS("A-"),
    B_PLUS("B+"),
    B_LESS("B-"),
    AB_PLUS("AB+"),
    AB_LESS("AB-"),
    O_PLUS("O+"),
    O_LESS("O-");

    String bloodType;

    BloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getValue() {
        return bloodType;
    }
}
