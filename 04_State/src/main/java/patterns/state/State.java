package patterns.state;

public interface State {

    void digit(int d);

    void e();

    void plusMinus(boolean plus);

    void comma();

    State nextState();

}
