package com.example.ujjwalsmahapatra.myapplication.threadpractice;

public class ThreadEg1 {
    public static void main(String[] args) {
//step2: create the thread object:
        MyThread m1=new MyThread();
        m1.setName("Rajani-Thread");
        m1.setPriority(10);
        //step3: run the thread
        m1.start();
        System.out.println("current thread name :   ");
        System.out.println(Thread.currentThread().getName());
        System.out.println("main thread priority is:");
        System.out.println(Thread.currentThread().getPriority());
    }
}
//step1 : create a thread class

class MyThread extends Thread{

    public void run(){
        System.out.println("welcome to threads..");
        System.out.println(getName());
        System.out.println("worker thread priority is: ");
        System.out.println(getPriority());
    }
}
