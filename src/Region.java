public class Region {
    private String name;
    private int[] results;
    private String color;

    public Region(int[] userResults, String rName){
        name = rName;
        results = userResults;
        color = getWinner();
    }

    public String getWinner(){
        if (results[0]> results[1]&& results[0]> results[2])
            return "RED";
        else if (results[1]> results[0]&& results[1]> results[2])
            return "BLUE";
        else if (results[2]> results[0]&&results[2]> results[1])
            return "GREY";
        else
            return "WHITE";
    }
    public String getColor(){
        return color;
    }

    @Override
    public String toString() {
        return name+ ", " + results[0] +  ", " +results[1]+  ", " +results[2] + ", " + color;
    }
}
