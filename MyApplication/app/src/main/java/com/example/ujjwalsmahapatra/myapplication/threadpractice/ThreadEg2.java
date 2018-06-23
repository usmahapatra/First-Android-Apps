package com.example.ujjwalsmahapatra.myapplication.threadpractice;

public class ThreadEg2 {
    public static void main(String[] args) {
        A a1=new A();
        a1.start();

    }
}

class A extends Thread{

    public void run(){
        //we will print 1,2,3.. till 100
        for(int i=1;i<=100;i++){
            System.out.println("thread: "+i);
            if(i%10==0){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
