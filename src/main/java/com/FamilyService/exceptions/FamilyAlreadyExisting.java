package com.FamilyService.exceptions;

public class FamilyAlreadyExisting extends RuntimeException {
    public FamilyAlreadyExisting() {
        super("Family member already existing");
    }

    public FamilyAlreadyExisting(String msg) {
        super(msg);
    }
}
