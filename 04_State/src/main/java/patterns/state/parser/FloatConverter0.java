package patterns.state.parser;

import patterns.state.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FloatConverter0 {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = r.readLine();
        while (s != null && s.length() > 0) {
            try {
                double d = parseFloat(s);
                System.out.println(d);
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Format");
            }
            s = r.readLine();
        }
    }

    private static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }


    private static double parseFloat(String str) {
        AbstractState s = new S0();
        double m = 0;
        int exp = 0, exp_sign = 1;
        int pos = 0;
        while (s != State.ERROR && pos < str.length()) {
            char ch = str.charAt(pos++);
            if (isDigit(ch)) {
                if (s == State.S0) {
                    m = ((S0) s).getValue(ch);
                    s = s.nextState();
                } else if (s == State.S1) {
                    m = ((S1) s).getValue(ch);
                    s = s.nextState();
                } else if (s == State.S2 || s == State.S3) {
                    m = ((S2) s).getValue(ch);
                    s = s.nextState();
                } else if (s == State.S4 || s == State.S5 || s == State.S6) {
                    exp = ((S4) s).getValue(ch);
                    s = s.nextState();
                } else {
                    s = State.ERROR;
                }
            } else if (ch == '.') {
                if (s == State.S0) {
                    s = State.S2;
                } else if (s == State.S1) {
                    s = State.S3;
                } else {
                    s = State.ERROR;
                }
            } else if (ch == 'e' || ch == 'E') {
                if (s == State.S1 || s == State.S3) {
                    s = State.S4;
                } else {
                    s = State.ERROR;
                }
            } else if (ch == '+') {
                if (s == State.S4) {
                    s = State.S5;
                } else {
                    s = State.ERROR;
                }
            } else if (ch == '-') {
                if (s == State.S4) {
                    exp_sign = -1;
                    s = State.S5;
                } else {
                    s = State.ERROR;
                }
            }
        }

        if (s == State.S3 || s == State.S6) {
            return m * Math.pow(10, exp_sign * exp);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private enum State {
        S0, S1, S2, S3, S4, S5, S6, ERROR
    }
}
