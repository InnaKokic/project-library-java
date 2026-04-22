package member;

import exception.LibraryException;

public class MemberSuspendedException extends LibraryException {
    public MemberSuspendedException(int id) {
        super("This member " + "[ID: " + id + "]" + " is suspended. Please contact admin.");
    }
}

//Exception som ärver från LibraryException