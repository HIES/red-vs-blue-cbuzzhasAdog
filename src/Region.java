public class Region {
    private String name;
    private int[] results;

    public Region(int[] userResults, String rName){
        name = rName;
        results = userResults;
    }

    public int getWinner(){
        if (results[0]> results[1]&& results[0]> results[2])
            return 0;
        else if (results[1]> results[0]&& results[1]> results[2])
            return 1;
        else if (results[2]> results[0]&&results[2]> results[1])
            return 2;
        else
            return -1;
    }
}
