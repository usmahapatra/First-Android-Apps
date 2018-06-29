package com.example.ujjwalsmahapatra.myapplication;

public class ArrayPractice {
    public static void main(String[] args) {

        int[] arr= new int[]{2, 3, 4};
        arr=new int[10];
        arr[3]=6;
        //arr= new int[]{4, 5};
        //arr= new int[]{6, 7, 8};
        arr[3]=6;
        for (int i=0;i<arr.length;i++)
        {
            System.out.println(arr[i]+"\n");
        }



    }
}
