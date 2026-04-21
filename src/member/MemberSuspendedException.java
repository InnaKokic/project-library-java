package member;

public class MemberSuspendedException extends RuntimeException {
    public MemberSuspendedException(int id) {
        super("This member " + "[ID: " + id + "]" + " is suspended. Please contact admin.");
    }
}
