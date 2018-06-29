package com.example.ujjwalsmahapatra.myapplication;

import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        int c=0,b=0;
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter a three digit number: ");

        for(int i=0;i<1000;i++){
           // String s = sc.next();
        //System.out.println((s.charAt(0)-48)+(s.charAt(1)-48)+(s.charAt(2)-48));
            String s=String.valueOf(i);
        String s1 = "" + s.charAt(0);
        int a = Integer.parseInt(s1);
            if(s.length()>1) {
                String s2 = "" + s.charAt(1);
                b = Integer.parseInt(s2);
            }
        if(s.length()==3) {
            String s3 = "" + s.charAt(2);
             c = Integer.parseInt(s3);
        }
        int number = Integer.parseInt(s);
        int length = s.length();
            if(s.length()==1)
            {
                if ((Math.pow(a, length)  == number)) {
                    System.out.println(number + " ");
                }
            }
        if(s.length()==2)
        {
            if ((Math.pow(a, length) + Math.pow(b, length)  == number)) {
                System.out.println(number + " ");
            }
        }
            if(s.length()==3) {
                if ((Math.pow(a, length) + Math.pow(b, length) + Math.pow(c, length)) == number) {
                    System.out.println(number + " ");
                }
            }
    }
    }
}
