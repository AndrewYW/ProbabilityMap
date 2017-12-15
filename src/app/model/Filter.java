package app.model;

public class Filter {
    private char[][] map;
    private char[] actions;
    private char[] readings;
    private int[] start;
    private int[][] coords;
    private float[] errors;
    private float[][] probabilityMap;


    public Filter(MapFile map, DataFile data){
        this.map = map.getMap();
        this.actions = data.getActions();
        this.readings = data.getReadings();
        this.start = data.getStart();
        this.coords = data.getCoords();
        this.probabilityMap = new float[100][100];
        this.errors = new float[100];
    }

    public void filtering(int iterations){

    }

    public float setError(){
        return 0;
    }
}
