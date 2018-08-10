package com.example.ujjwalsmahapatra.myapplication;

import java.util.Scanner;
import java.util.Arrays;

public class ShortIntBitSwap {
    public static void main(String[] args) {

        short n, m;
        String x = "";
        String temp1,temp2="",temp3="",temp4="",temp5="";
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Decimal Number:");
        n = s.nextShort();
        while (n > 0) {
            int a = n % 2;
            x = a + x;
            n = (short) (n / 2);
        }
        System.out.println(x);
        int first=16-x.length();
        for(int i=0;i<16;i++)
        {
            if(i<first)
           temp4=temp4+"0";
        }
        temp4=temp4+x;
        System.out.println(temp4);
        char ch[]=temp4.toCharArray();
        char a;
        a=ch[4];
        ch[4]=ch[8];
        ch[8]=a;
        for(int j=0;j<16;j++){
        System.out.print(ch[j]);}
      /*  temp1="0000000000000000";
        for(int i=0;i<temp4.length();i++)
        {
            if(i>1&&i<7)
            {
                temp2=temp2+temp4.charAt(i);
            }
            if(i>8&&i<14)
            {
              temp3=temp3+temp4.charAt(i);
            }

        }
        System.out.println(temp2+"   "+temp3+"  "+temp4);

        for(int i=0;i<2;i++)
        {
            temp5=temp5+temp4.charAt(i);
        }
        for(int i=2;i<7;i++)
        {
            temp5=temp5+temp3.charAt(i-2);
        }
        for(int i=7;i<9;i++)
        {
            temp5=temp5+temp4.charAt(i);
        }
        for(int i=9;i<14;i++)
        {
            temp5=temp5+temp2.charAt(i-9);
        }
        for(int i=14;i<16;i++)
        {
            temp5=temp5+temp4.charAt(i);
        }



        System.out.println(temp5);

        int foo=Integer.parseInt(temp5,2);

        System.out.println(foo);
        */


    }

}
