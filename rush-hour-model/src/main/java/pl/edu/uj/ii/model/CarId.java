package pl.edu.uj.ii.model;

/**
 * Created by gauee on 3/31/16.
 */
public enum CarId {
    A('a'),
    C('c'),
    E('e'),
    I('i'),
    M('m'),
    N('n'),
    O('o'),
    R('r'),
    S('s'),
    U('u'),
    V('v'),
    W('w'),
    Z('z'),
    X('x');

    private final char id;

    CarId(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }
}
