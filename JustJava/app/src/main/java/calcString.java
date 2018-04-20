import static java.lang.System.out;

/**
 * Created by Ujjwal S Mahapatra on 24-Jan-18.
 */

public class calcString {
    public static void Main()
    {

    }
    String input="98 87 63 92 81 89 94 88 79 83";
    public void process(input)
    {
        String[] vals= input.split(" ");
        //Each element of vals is a String representation
        //of a score
        int[] score = new int[vals.length];
        int total=0;
        for(int i=0; i<score.length; i++)
        {
            score[i]=Integer.parseInt(vals[i]);
            total+=score[i];
        }
        double average=(double) total /score.length;
        out.println("The average was: "+average);
        for(int i=0;i<score.length;i++){
            if(score[i]<average){
                out.println("Score"+score[i]+"was less than the average");
            }
        }
    }
}
