import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmptyMap {
    public static void main(String[] args) throws FileNotFoundException {
        int scale = 1500;
        File f = new File("./input/" + args[0] + ".txt");
        Scanner s = new Scanner(f);
        // get dimensions
        double longMin = s.nextDouble();
        double latMin = s.nextDouble();
        double longMax = s.nextDouble();
        double latMax = s.nextDouble();
        double height = latMax - latMin;
        double width = longMax - longMin;

        StdDraw.setCanvasSize(scale, (int) (scale * (height / width)));
        StdDraw.setXscale(longMin, longMax);
        StdDraw.setYscale(latMin, latMax);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);
        StdDraw.enableDoubleBuffering();
        s.reset();
        for (int i = 0; i <5 ; i++) {
            s.nextLine();
        }

        boolean flag = false;
        while (s.hasNextLine()) {
            if (s.hasNextInt()) {
                int size = s.nextInt();
                if (s.hasNextDouble() && !s.hasNextInt()) {
                    double[] xVals = new double[size];
                    double[] yVals = new double[size];
                    int i = 0;
                    while (s.hasNextDouble()) {
                        xVals[i] = s.nextDouble();
                        yVals[i] = s.nextDouble();
                        i++;
                    }
//                    if (flag){
//                        StdDraw.setPenColor(StdDraw.RED);
//                        flag = false;
//                    }
//                    else{
//                        StdDraw.setPenColor(StdDraw.GREEN);
//                        flag = true;}
                    StdDraw.polygon(xVals,yVals);

                } else
                    s.nextLine();
            }
            else
                s.nextLine();
        }
        StdDraw.show();
    }

}

