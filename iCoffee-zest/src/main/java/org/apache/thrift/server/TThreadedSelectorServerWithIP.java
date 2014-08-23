package org.apache.thrift.server;

import name.upton.zest.thrift.ThreadLocalIpUtils;


public class TThreadedSelectorServerWithIP extends TThreadedSelectorServer {
    
    /**
     * Create the server with the specified Args configuration
     */
    public TThreadedSelectorServerWithIP(Args args) {
      super(args);      
    }
    
    protected Runnable getRunnable(FrameBuffer frameBuffer) {
      String ip = ThreadLocalIpUtils.getIp();
      ThreadLocalIpUtils.remove();
      return new InvocationWithIP(frameBuffer, ip);
    }
  }
