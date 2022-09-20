package com.alibaba.cloud.youxia.manager;

/**
 * 乐观锁线程
 */
public class OptLockThread implements Runnable{

    public  OptLockThread(ProductManager example2UserManager){
        this.example2UserManager=example2UserManager;
    }

    @Override
    public void run() {
        example2UserManager.updateByEntitySucc();
    }

    private ProductManager example2UserManager;
}
