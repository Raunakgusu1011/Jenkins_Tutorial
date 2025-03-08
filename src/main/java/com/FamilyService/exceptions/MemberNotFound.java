package com.FamilyService.exceptions;

public class MemberNotFound extends RuntimeException {
    public MemberNotFound() {
        super("Family member which you are searching is not present either have deleted..");
    }

    public MemberNotFound(String msg) {
        super(msg);
    }
}
