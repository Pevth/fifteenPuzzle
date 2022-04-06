import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

class AStarTest {

    int n;
    int m;
    double sumTime = 0;

    int[][] generateTable(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String data;
        int[][] tab = new int[0][0];
        int x = 0;
        int y;

        for (int i = 0; (data = br.readLine()) != null; i++) {
            if (i == 0) {
                String[] coordinates = data.split(" ");
                this.n = Integer.parseInt(coordinates[0]);
                this.m = Integer.parseInt(coordinates[1]);
                tab = new int[this.n][this.m];
            } else {
                String[] values = data.split(" ");
                for (int j = 0; j < this.m; j++) {
                    tab[i - 1][j] = Integer.parseInt(values[j]);
                }
            }
        }
        return tab;
    }

    @Test
    void checkManhattan() throws IOException, InterruptedException {
        int a = 0;
        for (int z = 1; z < 413; z++) {
            int[][] tableGame = generateTable("src/test/java/TestTables/file_" + z);
            AStar aStar = new AStar(tableGame, n, m);
            boolean test = aStar.startSearchingAStar('M');
            //Assertions.assertTrue(test);
            System.out.println("Tablica: file_" + z + " Test: " + test + " Czas: " + aStar.time);
           if(test == true) {
               a++;
           }
            sumTime += aStar.time;

        }
        System.out.println("Suma czasu: " + sumTime + " | Poprawnych wynikow: " + a);
    }

    @Test
    void checkHamming() throws IOException, InterruptedException {
        int a = 0;
        for (int z = 1; z < 413; z++) {
            int[][] tableGame = generateTable("src/test/java/TestTables/file_" + z);
            AStar aStar = new AStar(tableGame, n, m);
            boolean test = aStar.startSearchingAStar('H');
            //Assertions.assertTrue(test);
            System.out.println("Tablica: file_" + z + " Test: " + test + " Czas: " + aStar.time);
            if(test == true) {
                a++;
            }
            sumTime += aStar.time;

        }
        System.out.println("Suma czasu: " + sumTime + " | Poprawnych wynikow: " + a);
    }

}