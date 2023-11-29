package homework2.microTasks.task1;

import java.util.Date;

public class D extends C {
    private E varD1;

    public E getVarD1() {
        return varD1;
    }

    public void setVarD1(E varD1) {
        this.varD1 = varD1;
    }

    @Override
    public int foo(Date d) {
        return (int) new Date().getTime();
    }

    @Override
    public void buzz() {

    }
}

class E {
    private E varE1;

    public void bar() {

    }

    private int bar(int size) {
        return size;
    }
}