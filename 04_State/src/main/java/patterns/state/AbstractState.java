package patterns.state;

public class AbstractState implements State {


    public double m = 0;

    protected int getNumericValue(char ch) {
        return Character.getNumericValue(ch);
    }

    @Override
    public AbstractState nextState() {
        return new S0();
    }
}
