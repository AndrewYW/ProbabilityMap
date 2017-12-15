package app.model;


import java.io.File;
import java.util.Scanner;

public class DataFile {
    private File dataFile;
    private char[] actions;
    private int[] start;
    private int[][] coords;
    private char[] readings;

    public DataFile(File file){
        this.dataFile = file;
        this.actions = new char[100];
        this.start = new int[2];
        this.coords = new int[100][2];
        this.readings = new char[100];

        initialize();
    }

    public char[] getActions(){
        return this.actions;
    }
    public int[] getStart(){
        return this.start;
    }
    public int[][] getCoords(){
        return this.coords;
    }
    public char[] getReadings(){
        return this.readings;
    }

    private void initialize(){
        try{
            Scanner sc = new Scanner(this.dataFile);

            int c = 0;
            while(c < 101) {
                String line = sc.nextLine();
                String[] splits = line.split(" ");

                if( c == 0 ){
                    this.start[0] = Integer.parseInt(splits[0]);
                    this.start[1] = Integer.parseInt(splits[1]);
                    c++;
                }else if( c != 100){
                    this.coords[c-1][0] = Integer.parseInt(splits[0]);
                    this.coords[c-1][1] = Integer.parseInt(splits[1]);
                }
            }

            for(int i = 0; i < 100; i++){
                String line = sc.nextLine();
                this.actions[i] = line.charAt(0);
            }
            for(int i = 0; i < 100; i++){
                String line = sc.nextLine();
                this.readings[i] = line.charAt(0);
            }

            sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        String name = this.dataFile.getName();
        if (name.indexOf(".") > 0)
            name = name.substring(0, name.lastIndexOf("."));
        return name;
    }
}
