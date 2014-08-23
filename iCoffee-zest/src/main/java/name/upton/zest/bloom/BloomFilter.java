package name.upton.zest.bloom;

import java.util.BitSet;

public class BloomFilter {
    /* BitSet初始分配100000000个bit */
    private static final int DEFAULT_SIZE = 2000000000;
    /* 不同哈希函数的种子，一般应取质数 */
    private static final int[] seeds = new int[] { 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
            71, 73, 79, 83 };
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    /* 哈希函数对象 */
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    // 将字符串标记到bits中
    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    // 判断字符串是否已经被bits标记
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }
}