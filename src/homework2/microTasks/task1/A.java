package homework2.microTasks.task1;

import java.util.Date;

abstract class A {
    private String varA1 = "aaa";
    private int varA2;

    public String getVarA1() {
        return varA1;
    }

    public void setVarA1(String varA1) {
        this.varA1 = varA1;
    }

    public int getVarA2() {
        return varA2;
    }

    public void setVarA2(int varA2) {
        this.varA2 = varA2 >= 100 ? 99 : (varA2 < 0 ? 0 : varA2);
    }

    public abstract int foo(Date a);

    public abstract void buzz();
}
