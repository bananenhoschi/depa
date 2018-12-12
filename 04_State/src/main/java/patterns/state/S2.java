package patterns.state;

public class S2 extends AbstractState {

    private int quo = 10;

    public double getValue(char c) {
        m =  m + getNumericValue(c) / quo;
        quo = quo * 10;
        return m;
    }
}
