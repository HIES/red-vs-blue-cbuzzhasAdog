import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class ElectoralMap {
    private String title;
    private int year;
    private static String[] canidates;
    private static String[] regions;

    public static void visulize(String usrRegion/*, int electYear*/) throws FileNotFoundException {
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
        File f = new File("./input/" + args[0]+ ".txt");
        Scanner s = new Scanner(f);
        String[] regCanidates = s.nextLine().split(",");

        canidates = new String[regCanidates.length];
        for (int i = 1; i < regCanidates.length; i++) {
            System.out.println(regCanidates[i]);
            canidates[i] = regCanidates[i];

        }
        HashMap<String, Region> regionsMap = new HashMap<String, Region>();
        while (s.hasNextLine()){
            String[] line = s.nextLine().split(,);
            int[] votes =
            regionsMap.put(line[0], new Region(votes,line[0]);
        }
    }

}
