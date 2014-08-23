package name.upton.zest.bloom;

public class BloomTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BloomFilter bloom = new BloomFilter();

        long start = System.currentTimeMillis();
        int failCount = 0;
        for (int i = 0; i < 5000000; i++) {
            String str = String.valueOf(i);
            
            if (bloom.contains(str)) {
                failCount++;
            }
            
            bloom.add(String.valueOf(i));
        }

        long end = System.currentTimeMillis();

        System.out.println("failCount=" + failCount);
        System.out.println("total=" + (end - start) + "ms");
    }
}
