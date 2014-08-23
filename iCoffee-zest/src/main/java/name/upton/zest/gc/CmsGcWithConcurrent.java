package name.upton.zest.gc;

import java.nio.ByteBuffer;


public class CmsGcWithConcurrent {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int size = 500000000; // 50MB
        for(int i = 0; i < size; i++){
            ByteBuffer.allocateDirect(1024 * 1024);
        }
        
        
    }

}
