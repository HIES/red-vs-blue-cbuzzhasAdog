import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ElectoralMap {
    private static String[] canidates;
    private static ArrayList<String> regions = new ArrayList<>();
    private static String[] info;
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

            if (subr.contains(" PARISH"))
                subr = subr.substring(0, subr.indexOf(" PARISH"));
            if (subr.contains(" CITY")/*&& !reg.equals("NV")&& !reg.equals("VA")*/)
                subr = subr.substring(0, subr.indexOf(" CITY"));

            //System.out.print(reg);
            //System.out.println("   " + subr);

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
                regionsMap.get(reg).get(subr).addxyVals(xVals, yVals);
                regions.add(reg);
            } else if (regionsMap.containsKey(reg) && !regionsMap.get(reg).containsKey(subr)) {

                regionsMap.get(reg).put(subr, new Region(subr));
                regionsMap.get(reg).get(subr).addxyVals(xVals, yVals);

            } else if (regionsMap.containsKey(reg) && regionsMap.get(reg).containsKey(subr)) {
                regionsMap.get(reg).get(subr).addxyVals(xVals, yVals);
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
            info = regCanidates;
            canidates = new String[regCanidates.length - 1];

            for (int i = 1; i < regCanidates.length; i++) {  //gets regions and candidates
                //System.out.print(regCanidates[i] + " ");
                canidates[i - 1] = regCanidates[i];

            }
            //System.out.print("\n");//formatting

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(","); // grabs line of data

                int[] votes = new int[line.length - 1]; // creates place to store votecount
                for (int i = 1; i < line.length; i++) {
                    votes[i - 1] = Integer.parseInt(line[i]); //fills votecount into votes
                }
                String subr = line[0].toUpperCase();
                if (subr.contains(" PARISH"))
                    subr = subr.substring(0, subr.indexOf(" PARISH"));
                if (subr.contains(" CITY"))
                    subr = subr.substring(0, subr.indexOf(" CITY"));

                if (regionsMap.get(r).get(subr) != null) {
                    regionsMap.get(r).get(subr).setVotes(votes);
                    //System.out.println(regionsMap.get(r).get(subr).toString());

                }

            }
        }
    }

    public static void visualize(String userFile) throws FileNotFoundException {
        int scale;
        File f2 = new File("./input/" + userFile + ".txt");
        Scanner j = new Scanner(f2);
        // get dimensions
        double longMin = j.nextDouble();
        double latMin = j.nextDouble();
        double longMax = j.nextDouble();
        double latMax = j.nextDouble();
        double height = latMax - latMin;
        double width = longMax - longMin;


        if (userFile.contains("USA")) {
            scale = 1280;
            StdDraw.setCanvasSize(scale, (int) (scale * (height / width)));
            StdDraw.setXscale(longMin * 1.002, longMax * .838);
            StdDraw.setYscale(latMin * .9995, latMax * 1.12);
        } else {
            scale = 800;
            StdDraw.setCanvasSize(scale, (int) (scale * (height / width)));
            StdDraw.setXscale(longMin * 1.002, longMax * .97);
            StdDraw.setYscale(latMin * .99, latMax * 1.05);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);
        StdDraw.enableDoubleBuffering();
        j.close();

        for (String superReg : regions) {
            for (Region value : regionsMap.get(superReg).values()) {
                ArrayList<double[]> xvals;
                ArrayList<double[]> yvals;
                xvals = regionsMap.get(superReg).get(value.getName()).getXVals();
                yvals = regionsMap.get(superReg).get(value.getName()).getYVals();

                System.out.println(superReg + "  " + value.getName());

                for (int z = 0; z < xvals.size(); z++) {
                    if (!regionsMap.get(superReg).containsKey(value.getName()) ||
                            regionsMap.get(superReg).get(value.getName()).getColor().equals("nope")) {
                        break;
                    } else {
                        if (regionsMap.get(superReg).get(value.getName()).getColor().equals("RED")) {
                            StdDraw.setPenColor(StdDraw.RED);
                        } else if (regionsMap.get(superReg).get(value.getName()).getColor().equals("BLUE")) {
                            StdDraw.setPenColor(StdDraw.BLUE);
                        } else if (regionsMap.get(superReg).get(value.getName()).getColor().equals("GREY")) {
                            StdDraw.setPenColor(StdDraw.GRAY);
                        } else if (regionsMap.get(superReg).get(value.getName()).getColor().equals("WHITE")) {
                            StdDraw.setPenColor(StdDraw.WHITE);
                        }
                        StdDraw.filledPolygon(xvals.get(z), yvals.get(z));
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.polygon(xvals.get(z), yvals.get(z));

                    }
                }
                //System.out.println(superReg+ "  " + value.getName());
            }
        }
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.filledRectangle(5, 9.2, 5, .8);
        StdDraw.filledRectangle(9.2, 5, .8, 5);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(9.2, 5, .8, 5);
        StdDraw.rectangle(5, 9.2, 5, .8);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(8.6, 6, .125, .125);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(8.6, 5, .125, .125);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(8.6, 4, .125, .125);
        StdDraw.setPenColor(StdDraw.WHITE);

        StdDraw.textLeft(8.8, 6, canidates[0]);
        StdDraw.textLeft(8.8, 5, canidates[1]);
        StdDraw.textLeft(8.8, 4, canidates[2]);
        StdDraw.text(4, 9.2, info[0]);
        StdDraw.text(4, 8.8, userFile);
        StdDraw.rectangle(8.6, 6, .125, .125);
        StdDraw.rectangle(8.6, 5, .125, .125);
        StdDraw.rectangle(8.6, 4, .125, .125);

        StdDraw.show();
    }


    public static void main(String[] args) throws FileNotFoundException {


                getGeoData("USA-county");
                getVoteData(1964);
                visualize("USA-county");


    }
}