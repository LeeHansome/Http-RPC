package com.hsm.rpc.container;

/**
 * Created by yongzhangli on 14/2/17.
 */
public abstract class Container {
    public static volatile boolean isStart=false;
    public abstract void start();
    public static volatile Container container = null;
}
