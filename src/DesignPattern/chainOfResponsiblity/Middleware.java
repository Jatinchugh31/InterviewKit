package src.DesignPattern.chainOfResponsiblity;

public abstract class Middleware {
    private Middleware nextMiddleware;

    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain : chain) {
            head.nextMiddleware = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(String email, String password);

    protected boolean checkNext(String email, String password) {
        if (nextMiddleware == null) {
            return true;
        }
        return nextMiddleware.check(email, password);
    }
}
