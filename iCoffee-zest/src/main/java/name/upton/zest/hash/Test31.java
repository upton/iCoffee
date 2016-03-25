package name.upton.zest.hash;

public class Test31 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            if (31 * i != (i << 5) - i) {
                System.out.println("31 * " + i + " != (" + i + " << 5) - " + i + "");
            }
        }

        System.out.println("done");
    }

}
