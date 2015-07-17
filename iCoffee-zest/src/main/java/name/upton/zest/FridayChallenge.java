package name.upton.zest;

import java.util.ArrayList;
import java.util.List;

public class FridayChallenge {

    public static void main(String[] args) {
        int n = 10000000;

        int count = 0;
        
        long time = 0L;
        
        time = System.currentTimeMillis();
        count = xiazhijian(n);
        System.out.print("xiazhijian: ");
        System.out.println("count=" + count + ", time=" + (System.currentTimeMillis() - time));
        
        time = System.currentTimeMillis();
        count = chentao(n);
        System.out.print("chentao   : ");
        System.out.println("count=" + count + ", time=" + (System.currentTimeMillis() - time));
        
        time = System.currentTimeMillis();
        count = chenzehong(n);
        System.out.print("chenzehong: ");
        System.out.println("count=" + count + ", time=" + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        count = limaosheng(n);
        System.out.print("limaosheng: ");
        System.out.println("count=" + count + ", time=" + (System.currentTimeMillis() - time));
    }

    // 陈泽洪
    public static int chenzehong(final int n) {
        int result = 0;

        // init
        boolean[] arr = new boolean[n + 1];

        int length = arr.length;
        int end = (int) Math.sqrt(length) + 1;

        for (int i = 2; i < end; i++) {
            if (!arr[i]) {
                for (int j = i, k = j * i; k < length; j++, k = j * i) {
                    arr[k] = true;
                }
            }
        }

        // count
        for (int i = 2; i < length; i++) {
            if (!arr[i]) {
                result++;
            }
        }

        return result;
    }

    // 陈涛
    public static int chentao(int n) {
        int number = 1;// 从3开始计算
        ArrayList<Integer> list = new ArrayList<Integer>(n >> 1);

        for (int i = 3; i <= n; i += 2) {// 两个质素之间相差至少为2
            boolean bool = true;
            for (int t : list) { // 用已有质数集作判断
                if (i % t == 0) {
                    bool = false;
                    break;
                }
                if (i < t * t) {// 计算到一个开方后的数时结束
                    break;
                }
            }
            if (bool) {
                number++;
                list.add(i);
            }
        }

        return number;
    }

    // 夏子健
    public static int xiazhijian(int n) {
        int ret = 0;
        int[] number = new int[n];
        if (n > 3) {
            // 标记跳过偶数
            for (int i = 3; i <= n; i++) {
                for (int j = i * 2; j <= n; j += i) {
                    number[j - 1] = 1;
                }
            }

            // 筛选跳过偶数
            for (int i = 2; i < n; i += 2) {
                ret += number[i];
            }
            ret = n / 2 - ret + n % 2;
        } else {
            ret = n - 1;
        }

        return ret;
    }

    // 李茂森
    public static int limaosheng(int n) {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(2);
        outer: for (int num = 3; num <= n; num += 2) {
            int sqrtTemp = (int) Math.sqrt(num) + 1;
            inner: for (Integer temp : arr) {
                if (temp > sqrtTemp) {
                    break inner;
                }
                if (num % temp == 0) {
                    continue outer;
                }
            }
            arr.add(num);
        }
        int count = arr.size();
        return count;
    }
}