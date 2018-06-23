package com.example.ujjwalsmahapatra.myapplication;

class Prime {
    public static void main(String args[]) {
        int sum=0;
        int [] arr ={0,1,2,3,4,5,6,7,8,9};
        String arr2;
        for(int j=0;j<10;j++)
        {
            for(int i=0;i<10;i++)
            {
             sum=arr[j]+arr[i];
             if(sum==5){
                 System.out.println(arr2="{"+arr[j]+","+arr[i]+"}");
             }
            }

        }

    }

}
