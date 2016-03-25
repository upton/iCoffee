package upton;

import java.io.File;
import java.util.UUID;

public class TestMain {
    public static String RUNPATH;
    public static String RUNPATH_HASH;

    static {
        RUNPATH = org.apache.http.Header.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (RUNPATH != null) {
            int lastSep = RUNPATH.lastIndexOf(File.separator);
            if (lastSep > 0) {
                RUNPATH = RUNPATH.substring(0, lastSep);
            }
        } else {
            RUNPATH = "";
        }

        RUNPATH_HASH = String.valueOf(Math.abs(RUNPATH.hashCode()));
    }

    public static void main(String[] args) {
        int a = 110;
        int b = 250;
        swapInt(a, b);

        System.out.println("a=" + a);
        System.out.println("b=" + b);

        String s1 = "hello";
        String s2 = "world";
        swapStr(s1, s2);

        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);

        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());

        System.out.println(RUNPATH);
        System.out.println(RUNPATH_HASH);
        
        
        int retryCount = 3;
        do {
            System.out.println("=======================");
            retryCount--;
        } while (retryCount > 0);
    }

    public static void swapInt(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    public static void swapStr(String s1, String s2) {
        String tmp = s1;
        s1 = s2;
        s2 = tmp;
    }
}
