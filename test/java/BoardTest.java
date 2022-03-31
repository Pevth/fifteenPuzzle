import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void checkBoard() {

        int n = 4;
        int m = 4;

        int[][] tableGame = {{1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}};

        System.out.println("Left: ");
        Board boardL = new Board(n,m, tableGame,'L');
        boardL.printBoard();
        System.out.println("Movement checker: " +boardL.checkMov());
        System.out.println("Verify checker: " +boardL.checkVerify());

        System.out.println("Up: ");
        Board boardU = new Board(n,m, tableGame,'U');
        boardU.printBoard();
        System.out.println("Movement checker: " +boardU.checkMov());
        System.out.println("Verify checker: " +boardU.checkVerify());


        System.out.println("Right: ");
        Board boardR = new Board(n,m,tableGame,'R');
        boardR.printBoard();
        System.out.println("Movement checker: " +boardR.checkMov());
        System.out.println("Verify checker: " +boardR.checkVerify());



        System.out.println("Down: ");
        Board boardD = new Board(n,m,tableGame,'D');
        boardD.printBoard();
        System.out.println("Movement checker: " +boardD.checkMov());
        System.out.println("Verify checker: " +boardD.checkVerify());



    }
}