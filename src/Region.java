import java.util.ArrayList;

public class Region {
    private String name;
    private int[] results;
    private String color;

    private int red;
    private int independant;
    private int blue;


    private ArrayList<double[]> xVals = new ArrayList<>();
    private ArrayList<double[]> yVals = new ArrayList<>();

    public Region(int[] userResults, String rName) {
        name = rName;
        results = userResults;
        color = getWinner();
        red = (int) ((results[0] * 1.0 / (this.results[0] + this.results[1] + this.results[2]) * 1.0) * 255);
        independant = (int) ((this.results[2] * 1.0 / (this.results[0] + this.results[1] + this.results[2]) * 1.0) * 255);
        blue = (int) ((this.results[1] * 1.0 / (this.results[0] + this.results[1] + this.results[2]) * 1.0) * 255);
    }

    public Region(String rName) {
        name = rName;
        color = getWinner();
    }

    public String getWinner() {
        if (results == null)
            return "nope";
        else {
            if (results[0] > results[1] && results[0] > results[2])
                return "RED";
            else if (results[1] > results[0] && results[1] > results[2])
                return "BLUE";
            else if (results[2] > results[0] && results[2] > results[1])
                return "GREY";
            else
                return "WHITE";
        }
    }

    public String getName() {
        return name.toUpperCase();
    }

    public int getR() {

        return red;
    }

    public int getIndependant() {

        return independant;
    }

    public int getB() {

        return blue;
    }

    public void setVotes(int[] votes) {
        results = votes;
        color = getWinner();
        red = (int) ((results[0] * 1.0 / (this.results[0] + this.results[1] + this.results[2]) * 1.0) * 255);
        independant = (int) ((this.results[2] * 1.0 / (this.results[0] + this.results[1] + this.results[2]) * 1.0) * 255);
        blue = (int) ((this.results[1] * 1.0 / (this.results[0] + this.results[1] + this.results[2]) * 1.0) * 255);
    }


    public String getColor() {
        getWinner();
        return color;
    }

    public void addxyVals(double[] x, double[] y) {

        xVals.add(x);
        yVals.add(y);
    }

    public ArrayList<double[]> getXVals() {

        return xVals;

    }

    public ArrayList<double[]> getYVals() {

        return yVals;

    }

    @Override
    public String toString() {
        return name + ", " + results[0] + ", " + results[1] + ", " + results[2] + ", " + color;
    }
}
