package com.alibaba.cloud.youxia.service;

public class ConfigValueThread implements Runnable{

    private Config config;

    @Override
    public void run() {
        while (true) {
            try {
                String name = config.getName();
                System.out.println("当前Spring Name是："+name);
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
