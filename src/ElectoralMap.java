import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ElectoralMap {
    private static String[] canidates;
    private static ArrayList<String> regions = new ArrayList<>();
    private static ArrayList<String> subRegions;
    private static HashMap<String, HashMap<String, Region>> regionsMap = new HashMap<>();// creates hash map


    public static void getGeoData(String fileName) throws FileNotFoundException {
        File f2 = new File("./input/" + fileName + ".txt");
        Scanner g = new Scanner(f2);
        for (int i = 0; i < 4; i++) {
            g.nextLine();
        }

        while (g.hasNextLine()) {
            String subr = g.nextLine().toUpperCase();
            String reg = g.nextLine().toUpperCase();
            System.out.print(reg);
            System.out.println("   " + subr);

            int size = g.nextInt();
            double[] xVals = new double[size];
            double[] yVals = new double[size];
            int i = 0;
            while (g.hasNextDouble()) {
                xVals[i] = g.nextDouble();
                yVals[i] = g.nextDouble();
                i++;
            }

            if (!regionsMap.containsKey(reg)) {
                regionsMap.put(reg, new HashMap<String, Region>());
                regionsMap.get(reg).put(subr, new Region(subr));
                regionsMap.get(reg).get(subr).addxyVals(xVals,yVals);
                regions.add(reg);
            } else if (regionsMap.containsKey(reg) && !regionsMap.get(reg).containsKey(subr)){

                regionsMap.get(reg).put(subr, new Region(subr));
                regionsMap.get(reg).get(subr).addxyVals(xVals,yVals);

            } else if (regionsMap.containsKey(reg) && regionsMap.get(reg).containsKey(subr)){
                regionsMap.get(reg).get(subr).addxyVals(xVals,yVals);
            }



            if (g.hasNextLine()) {
                g.nextLine();
                g.nextLine();
            }

        }
        g.close();
        System.out.println("Done");

    }

    public static void getVoteData(int electYear) throws FileNotFoundException {
        for (String r : regions) {
            File f = new File("./input/" + r + electYear + ".txt");
            Scanner s = new Scanner(f);
            String[] regCanidates = s.nextLine().split(",");

            canidates = new String[regCanidates.length - 1];

            for (int i = 1; i < regCanidates.length; i++) {  //gets regions and candidates
                System.out.print(regCanidates[i] + " ");
                canidates[i - 1] = regCanidates[i];

            }
            System.out.print("\n");//formatting

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(","); // grabs line of data

                int[] votes = new int[line.length - 1]; // creates place to store votecount
                for (int i = 1; i < line.length; i++) {
                    votes[i - 1] = Integer.parseInt(line[i]); //fills votecount into votes
                }
                //regionsMap.put(line[0].toUpperCase(), new Region(votes, line[0].toUpperCase())); //creates hash/key pair holding region object that holds vote data
                //regionsMap.get(r).get(getStateAbb(line[0])).toString();
                /*if (!regionsMap.containsKey(line[0].toUpperCase())) {
                    regionsMap.get(r).get(getStateAbb(line[0].toUpperCase())).setVotes(votes);
                    System.out.println(regionsMap.get(r).get(getStateAbb(line[0].toUpperCase())).toString());
                }
                else {*/
                if (regionsMap.get(r).get(line[0].toUpperCase()) != null) {
                    regionsMap.get(r).get(line[0].toUpperCase()).setVotes(votes);
                    System.out.println(regionsMap.get(r).get(line[0].toUpperCase()).toString());
                    //}
                }

            }
        }
    }

    public static void visualize(String userFile) throws FileNotFoundException {
        int scale = 2560;
        File f2 = new File("./input/" + userFile + ".txt");
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
        j.close();

        for (String superReg : regions) {


            for (Region value : regionsMap.get(superReg).values()) {
                ArrayList<double[]> xvals;
                ArrayList<double[]> yvals;
                xvals = regionsMap.get(superReg).get(value.getName()).getXVals();
                yvals = regionsMap.get(superReg).get(value.getName()).getYVals();

                for (int z = 0; z < xvals.size(); z++) {
                    if (regionsMap.get(superReg).get(value.getName()).getColor().equals("RED"))
                        StdDraw.setPenColor(StdDraw.RED);
                    else if (regionsMap.get(superReg).get(value.getName()).getColor().equals("BLUE"))
                        StdDraw.setPenColor(StdDraw.BLUE);
                    else if (regionsMap.get(superReg).get(value.getName()).getColor().equals("GREY"))
                        StdDraw.setPenColor(StdDraw.GRAY);
                    else if (regionsMap.get(superReg).get(value.getName()).getColor().equals("WHITE"))
                        StdDraw.setPenColor(StdDraw.WHITE);

                    StdDraw.filledPolygon(xvals.get(z), yvals.get(z));
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.polygon(xvals.get(z), yvals.get(z));
                }
                System.out.println(value.getName());

            }
        }

        StdDraw.show();
    }


    public static void main(String[] args) throws FileNotFoundException {
        /*System.out.println(args[0].substring(0, args[0].length() - 4) + " " + args[0].substring(args[0].length() - 4));
        String filename = args[0].substring(0, args[0].length() - 4);

        int year = Integer.parseInt(args[0].substring(args[0].length() - 4));
        visulize(filename, year);*/

        getGeoData("USA");
        getVoteData(2012);
        visualize("USA");

    }
}