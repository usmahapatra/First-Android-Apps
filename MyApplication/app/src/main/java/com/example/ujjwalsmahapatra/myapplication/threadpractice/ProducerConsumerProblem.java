package com.example.ujjwalsmahapatra.myapplication.threadpractice;
//Producer-Consumer problem with deadlock situation

public class ProducerConsumerProblem {
    public static void main(String[] args) {
    ChildThread c=new ChildThread(Thread.currentThread());
    c.start();//start child thread

        for (;;){
            //infinite loop- in parent thread
            System.out.println("Parent Thread: Producing Money");
            try {
                c.interrupt();//wake up child if waiting
                c.join();
            } catch (InterruptedException e) {

            }
        }
    }
}

class ChildThread extends Thread{
    Thread t1;
    ChildThread(Thread t)
    {
        t1=t;
    }
    public void run(){

        for(;;){
            try {
                t1.interrupt();//wake up the parent
                t1.join();//child says: until my parent produces money I will not start consuming money
            } catch (InterruptedException e) {

            }
            //infinite loop
            System.out.println("Child Thread: Eat-Enjoy");
        }
    }
}
