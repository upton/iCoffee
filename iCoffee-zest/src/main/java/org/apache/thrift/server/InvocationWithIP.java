package org.apache.thrift.server;

import name.upton.zest.thrift.ThreadLocalIpUtils;

import org.apache.thrift.server.AbstractNonblockingServer.FrameBuffer;

public class InvocationWithIP extends Invocation{
    private final String ip;

    public InvocationWithIP(final FrameBuffer frameBuffer, String ip) {
      super(frameBuffer);
      this.ip = ip;
    }

    public void run() {
      ThreadLocalIpUtils.setWorkerIp(ip);
      super.run();
    }
  }