package com.alibaba.cloud.youxia.thread;

public class ExecuteThread implements Runnable{
    private UserService userService;

    public ExecuteThread(UserService userService){
        this.userService=userService;
    }
    @Override
    public void run(){
        while (true){
            try {
                userService.getUserAndAddr("test");
                Thread.sleep(10000);
            }catch (InterruptedException e){
                System.out.println(e.getCause());
            }
        }
    }
}
