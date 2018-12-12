package patterns.state;

public class S1 extends AbstractState {

    public double getValue(char c) {
        return  10 * m + getNumericValue(c);
    }
}
