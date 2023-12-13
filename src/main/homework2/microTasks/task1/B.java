package homework2.microTasks.task1;

import java.util.Date;

class B extends A {
    private double varB1;

    public double getVarB1() {
        return varB1;
    }

    @Override
    public int foo(Date d) {
        return (int) new Date().getTime();
    }

    @Override
    public void buzz() {

    }
}
