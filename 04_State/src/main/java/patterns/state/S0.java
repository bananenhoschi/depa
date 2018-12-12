package patterns.state;

public class S0 extends AbstractState {

    public double getValue(char c) {
        return getNumericValue(c);
    }

    @Override
    public S1 nextState() {
        return new S1();
    }
}
