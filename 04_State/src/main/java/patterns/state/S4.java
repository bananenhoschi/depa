package patterns.state;

public class S4 extends AbstractState{

    public double getValue(char ch) {
        return 10 * exp + getNumericValue(ch);
    }
}
