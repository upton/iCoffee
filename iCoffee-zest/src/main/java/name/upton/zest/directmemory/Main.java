package name.upton.zest.directmemory;

import java.nio.ByteBuffer;

import sun.misc.VM;

public class Main {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        //List<ByteBuffer> buffs = new ArrayList<ByteBuffer>();
        System.out.println(VM.maxDirectMemory()/1024);
        int size = 1300;
        for(int i = 0; i < size; i++){
            ByteBuffer.allocateDirect(1024 * 1024); // 1MB
        }

        Thread.sleep(100000L);
    }
}
