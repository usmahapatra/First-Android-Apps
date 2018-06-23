package com.example.ujjwalsmahapatra.myapplication;

public class Anagram {


     public static void main(String[] args) {
       String sc1="god";
       String sc2="dog";
       String az="abcdefghijklmnopqrstuvwxyz";
         int count1=0;

         char scone;
         char sctwo;


       for(int i=0;i<sc1.length();i++) {
           for (int j = 0; j < sc2.length(); j++) {
               scone=sc1.charAt(i);
               sctwo=sc2.charAt(j);
               if(scone==sctwo)
               {
                 count1++;
               }

           }
      }



       if(count1==sc1.length()&&count1==sc2.length()){
           System.out.println("Both represents an Anagram");
       }
       else {
          System.out.println("Both does not represent Anagram");
      }

   }
}
