import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class EmptyMap {

    public static void main(String[] args) throws FileNotFoundException {


        File f = new File("./input/" + "USA.txt");

        Scanner s = new Scanner(f);


        // get dimensions

        String[][] dimensions = new String[2][2];
        double latMin = s.nextDouble();
        double latMax = s.nextDouble();
        double longMin = s.nextDouble();
        double longMax = s.nextDouble();


        StdDraw.setCanvasSize(1024, 1024);

        StdDraw.setXscale(latMin, latMax);

        StdDraw.setYscale(longMin, longMax);

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);


        // turn on animation mode

        StdDraw.enableDoubleBuffering();


        // run smallest insertion heuristic


        s.reset();

        s.nextLine();

        s.nextLine();

        s.nextLine();

        s.nextLine();

        s.nextLine();

        s.nextLine();

        s.nextLine();


        while (s.hasNextLine()) {
            if (s.hasNextDouble()&& !s.hasNextInt()) {
                while (s.hasNextDouble()) {

                    double x = Double.parseDouble(s.next());

                    double y = Double.parseDouble(s.next());

                    Point p = new Point(x, y);

                    p.draw();


                    // uncomment the lines below to animate

                    // StdDraw.clear();

                    // tour.draw();

                    // StdDraw.textLeft(20, 0, "length = " + tour.length());

                     //StdDraw.show();

                    // StdDraw.pause(50);

                }
            }
            else
               s.nextLine();

        }

        // draw to standard draw
        StdDraw.show();
    }

}

