package app.model;

import java.io.File;
import java.util.Scanner;

public class MapFile {
    private File mapFile;
    private char[][] map;

    public MapFile(File file){
        this.mapFile = file;
        this.map = new char[100][100];
        initialize();
    }

    public char[][] getMap(){
        return this.map;
    }

    public void initialize(){

        try {
            Scanner sc = new Scanner(this.mapFile);

            for(char[] row : this.map){
                String line = sc.nextLine();
                row = line.toCharArray();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        String name = this.mapFile.getName();
        if (name.indexOf(".") > 0)
            name = name.substring(0, name.lastIndexOf("."));
        return name;
    }
}
