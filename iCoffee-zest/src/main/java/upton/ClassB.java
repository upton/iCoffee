package upton;

class ClassA {
    static {
        System.out.print("1");
    }

    public ClassA() {
        System.out.print("2");
    }
}

public class ClassB extends ClassA {
    static {
        System.out.print("3");
    }

    public ClassB() {
        System.out.print("4");
    }

    public void foo() {
        String s5 = "5";
        String s6 = "6";

        swap(s5, s6);

        System.out.print(s5);
        System.out.print(s6);
    }

    public void swap(String s5, String s6) {
        String temp = s5;

        s5 = s6;
        s6 = temp;
    }

    public static void main(String args[]) {
        ClassB bb = new ClassB();

        bb.foo();
    }
}