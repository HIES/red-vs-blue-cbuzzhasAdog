import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class ElectoralMap {
    private String title;
    private int year;
    private static String[] canidates;
    private static ArrayList<String> regions;

    public static void visulize(String usrRegion, int electYear) throws FileNotFoundException {
        String[] region = {usrRegion};
        try {
            EmptyMap.main(region);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
//        try {
//            visulize(args[0]);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
        File f = new File("./input/" + args[0] + ".txt");
        Scanner s = new Scanner(f);
        String[] regCanidates = s.nextLine().split(",");

        canidates = new String[regCanidates.length - 1];

        for (int i = 1; i < regCanidates.length; i++) {  //gets regions and candidates
            System.out.print(regCanidates[i] + " ");
            canidates[i - 1] = regCanidates[i];

        }
        System.out.print("\n");//formatting

        HashMap<String, Region> regionsMap = new HashMap<String, Region>();// creates hash map
        regions = new ArrayList<>(); // makes place to store region names that are also key values for hash map

        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(","); // grabs line of data
            regions.add(line[0]); //adds to list of regions
            int[] votes = new int[line.length - 1]; // creates place to store votecount
            for (int i = 1; i < line.length; i++) {
                votes[i - 1] = Integer.parseInt(line[i]); //fills votecount into votes
            }
            regionsMap.put(line[0], new Region(votes, line[0])); //creates hash/key pair holding region object that holds vote data
        }
        for (String i : regions) { // prints all regions
            System.out.println(i);
        }
        for (String i : regions) { // prints status of hashMap
            System.out.println(regionsMap.get(i).toString());
        }
        String regionName = args[0].substring(0, args[0].length() - 4); // gets name of file to use for map visualization
        System.out.println(regionName); // prints that

        s.close();


        int scale = 2560;
        File f2 = new File("./input/" + regionName + ".txt");
        Scanner j = new Scanner(f2);
        // get dimensions
        double longMin = j.nextDouble();
        double latMin = j.nextDouble();
        double longMax = j.nextDouble();
        double latMax = j.nextDouble();
        double height = latMax - latMin;
        double width = longMax - longMin;

        StdDraw.setCanvasSize(scale, (int) (scale * (height / width)));
        StdDraw.setXscale(longMin, longMax);
        StdDraw.setYscale(latMin, latMax);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);
        //StdDraw.enableDoubleBuffering();
        j.reset();
        for (int i = 0; i < 3; i++) {
            j.nextLine();
        }

        String workingRegion;
        while (j.hasNextLine()) {
            workingRegion = j.nextLine();
            j.nextLine();

            int size = j.nextInt();

            double[] xVals = new double[size];
            double[] yVals = new double[size];
            int i = 0;
            while (j.hasNextDouble()) {
                xVals[i] = j.nextDouble();
                yVals[i] = j.nextDouble();
                i++;
            }
            if (regionsMap.get(workingRegion).getColor().equals("RED"))
                StdDraw.setPenColor(StdDraw.RED);
            else if (regionsMap.get(workingRegion).getColor().equals("BLUE"))
                StdDraw.setPenColor(StdDraw.BLUE);
            else if (regionsMap.get(workingRegion).getColor().equals("GREY"))
                StdDraw.setPenColor(StdDraw.GRAY);
            else if (regionsMap.get(workingRegion).getColor().equals("WHITE"))
                StdDraw.setPenColor(StdDraw.WHITE);
            else {
            }

            StdDraw.filledPolygon(xVals, yVals);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.polygon(xVals,yVals);
            j.nextLine();
            j.nextLine();

        }


        StdDraw.show();

}

}
