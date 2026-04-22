package member;

import exception.LibraryException;

public class MemberNotFoundException extends LibraryException {
    public MemberNotFoundException(String email) {
        super("Member could not be found.");
    }
}

//Exception som ärver från LibraryException