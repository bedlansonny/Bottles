import java.io.*;
import java.util.*;

public class Bottles
{
    static int[] vols;
    static int[] mcv;

    public static void main(String args[]) throws IOException
    {
        Scanner in = new Scanner(new File("bottles.dat"));
        int c = in.nextInt();

        for(int ci = 0; ci < c; ci++)
        {
            int n = in.nextInt();

            vols = new int[n];
            for(int ni = 0; ni < n; ni++)
            {
                vols[ni] = in.nextInt();
            }

            mcv = new int[vols.length];
            for(int i = 0; i < mcv.length; i++)
                mcv[i] = -1;
            //System.out.println(bestMemo(vols.length-1));
            System.out.println(bestDP());
        }
    }

    static int best(int index)
    {
        if(index == 0)
            return vols[0];
        else if(index == 1)
            return Math.max(vols[0], vols[1]);
        else
            return Math.max(best(index-2) + vols[index], best(index-1));
    }

    static int bestMemo(int index)
    {
        if(mcv[index] == -1)
        {
            if(index == 0)
                mcv[index] = vols[0];
            else if(index == 1)
                mcv[index] = Math.max(vols[0], vols[1]);
            else
                mcv[index] = Math.max(bestMemo(index-2) + vols[index], bestMemo(index-1));
        }
        return mcv[index];
    }

    static int bestDP()
    {
        mcv[0] = vols[0];
        mcv[1] = Math.max(vols[0], vols[1]);
        for(int i = 2; i < mcv.length; i++)
        {
            mcv[i] = Math.max(mcv[i-2] + vols[i], bestMemo(i-1));
        }
        return mcv[mcv.length-1];
    }
}
