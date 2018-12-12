package patterns.state.parser;

import patterns.state.State;

public class Context {


    double m;

    double q;

    State s;

    Context(double m, double q){
        this.m = m;
        this.q = q;
    }


    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public State getS() {
        return s;
    }

    public void setS(State s) {
        this.s = s;
    }
}
