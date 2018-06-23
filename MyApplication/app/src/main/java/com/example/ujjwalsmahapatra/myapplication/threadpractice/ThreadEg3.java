package com.example.ujjwalsmahapatra.myapplication.threadpractice;

public class ThreadEg3 {
    public static void main(String[] args) {
        B b1=new B(Thread.currentThread());
        b1.start();//start thread b
        //******************
       /* try {
            b1.join();//i want to wait for b1 thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for(char ch='A';ch<='Z';ch++){
            System.out.println("main thread : "+ch);
        }
    }
}

class B extends Thread{
    Thread t;
    public B(Thread mainthread){
        t=mainthread;
    }
    public void run(){
        //print 1..100
        //wait for the main thread
        try {
            t.join();//parent thread dies first then child will start running-(zombie thread)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=1;i<101;i++) {

            System.out.println("B.G Thread: "+i);
        }
    }
}
