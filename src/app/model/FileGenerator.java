package app.model;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class FileGenerator {

    public FileGenerator(){
        createFiles();
    }


    private void createFiles(){
        Random rng = new Random();
        for(int i = 0; i < 10; i ++){

            char[][] map = createMap();
            writeMap(map, i);

            for(int j = 0; j < 10; j++){

                char[] start = createStart(map);

                char[] actions = createActions();

                char[][] readings = createReadings(map, start, actions);

                writeDataFile(start, actions, readings, i, j);
            }
        }
    }

    private char[] createStart(char[][] map){
        Random rng = new Random();
        char[] result = new char[2];

        int row = rng.nextInt(100);
        int col = rng.nextInt(100);
        while(map[row][col] == 'B'){
            row = rng.nextInt(100);
            col = rng.nextInt(100);
        }

        result[0] = (char)row;
        result[1] = (char)col;


        return result;
    }
    private char[][] createMap(){
        Random rng = new Random();
        char[][] map = new char[100][100];
        for(int row = 0; row < 100; row++){
            for(int col = 0; col < 100; col++){
                switch(rng.nextInt(10)){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        map[row][col] = 'N';
                        break;
                    case 5:
                    case 6:
                        map[row][col] = 'H';
                        break;
                    case 7:
                    case 8:
                        map[row][col] = 'T';
                        break;
                    case 9:
                        map[row][col] = 'B';
                        break;
                }
            }
        }

        return map;
    }

    private char[] createActions(){
        Random rng = new Random();
        char[] actions = new char[100];
        for(int i = 0; i < 100; i++){
            switch(rng.nextInt(4)){
                case 0:
                    actions[i] = 'U';
                    break;
                case 1:
                    actions[i] = 'D';
                    break;
                case 2:
                    actions[i] = 'L';
                    break;
                case 3:
                    actions[i] = 'R';
                    break;
            }
        }

        return actions;
    }

    private char[][] createReadings(char[][] map, char[] start, char[] actions){
        Random rng = new Random();
        char[][] readings = new char[100][3];
        int row = start[0] - '0';   //row
        int col = start[1] - '0';   //col
        for(int i = 0; i < 100; i++){
            switch(actions[i]){
                case 'U':       //move up (row -1)
                    if(inBounds(row-1, col) && map[row-1][col] != 'B' && rng.nextInt(10) != 9){
                        readings[i][0] = (char)(row-1);
                        readings[i][1] = (char)(col);
                        row -= 1;
                    }else{
                        readings[i][0] = (char)(row);
                        readings[i][1] = (char)(col);
                        readings[i][2] = map[row][col];
                    }

                    if(rng.nextInt(10) != 9){
                        readings[i][2] = map[row][col];
                    }else{
                        readings[i][2] = readPosition(map[row][col]);
                    }
                    break;
                case 'D':       //row+1
                    if(inBounds(row+1, col) && map[row-1][col] != 'B' && rng.nextInt(10) != 9){
                        readings[i][0] = (char)(row+1);
                        readings[i][1] = (char)(col);
                        row += 1;
                    }else{
                        readings[i][0] = (char)(row);
                        readings[i][1] = (char)(col);
                        readings[i][2] = map[row][col];
                    }

                    if(rng.nextInt(10) != 9){
                        readings[i][2] = map[row][col];
                    }else{
                        readings[i][2] = readPosition(map[row][col]);
                    }
                    break;
                case 'L':       //col-1
                    if(inBounds(row, col-1) && map[row-1][col] != 'B' && rng.nextInt(10) != 9){
                        readings[i][0] = (char)(row);
                        readings[i][1] = (char)(col-1);
                        col -= 1;
                    }else{
                        readings[i][0] = (char)(row);
                        readings[i][1] = (char)(col);
                        readings[i][2] = map[row][col];
                    }

                    if(rng.nextInt(10) != 9){
                        readings[i][2] = map[row][col];
                    }else{
                        readings[i][2] = readPosition(map[row][col]);
                    }
                    break;
                case 'R':       //col+1
                    if(inBounds(row, col+1) && map[row-1][col] != 'B' && rng.nextInt(10) != 9){
                        readings[i][0] = (char)(row);
                        readings[i][1] = (char)(col+1);
                        col += 1;
                    }else{
                        readings[i][0] = (char)(row);
                        readings[i][1] = (char)(col);
                        readings[i][2] = map[row][col];
                    }

                    if(rng.nextInt(10) != 9){
                        readings[i][2] = map[row][col];
                    }else{
                        readings[i][2] = readPosition(map[row][col]);
                    }
            }
        }
        return readings;
    }

    private boolean inBounds(int x, int y){
        if(x > 99 || x < 0 || y > 99 || y < 0)
            return false;
        return true;
    }

    private char readPosition(char val){
        Random rng = new Random();

        switch(val){
            case 'N':
                if(rng.nextBoolean())
                    return 'T';
                return 'H';
            case 'H':
                if(rng.nextBoolean())
                    return 'T';
                return 'N';
            case 'T':
                if(rng.nextBoolean())
                    return 'N';
                return 'H';
        }
        return 'O';
    }

    private void writeMap(char[][] map, int num){
        String filePath = "src/app/maps/MapFile" + Integer.toString(num) + ".txt";
        PrintWriter write;

        try{
            File file = new File(filePath);
            file.createNewFile();
            write = new PrintWriter(file);

            for(char[] row : map){
                write.println(String.valueOf(row));
            }

            write.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeDataFile(char[] start, char[] actions, char[][] readings, int map, int num){
        String filePath = "src/app/data/Map" + Integer.toString(map) + "Set" + Integer.toString(num) + ".txt";
        PrintWriter write;

        try{
            File file = new File(filePath);
            file.createNewFile();
            write = new PrintWriter(file);

            write.println(String.valueOf(start[0]) + " " + String.valueOf(start[1]));

            for(char[] row : readings){
                write.println(String.valueOf(row[0]) + " " + String.valueOf(row[1]));
            }

            for(char action : actions){
                write.println(String.valueOf(action));
            }

            for(char[] row : readings){
                write.println(String.valueOf(row[2]));
            }

            write.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
