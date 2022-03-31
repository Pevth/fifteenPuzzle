public class Board {

    //potrzebna funkcja generująca poprawną tablice
    public final int[][] tabFinal = {{1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}};

    public int[][] tableGame;

    public int indexY;
    public int indexX;
    public int sizeN;
    public int sizeM;
    public int recursionLevel;
    public char blockMove;
    private boolean checkMovement = false;

        private void checkIndex() {
        for (int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeM; j++) {
                if (this.tableGame[i][j] == 0) {
                    this.indexX = j;
                    this.indexY = i;
                    break;
                }
            }
        }
    }

        public boolean checkMov() {
            return checkMovement;
        }

        public boolean checkVerify() {
            for(int i=0; i < sizeN;i++) {
                for(int j=0;j<sizeM;j++) {
                    if(this.tableGame[i][j] != this.tabFinal[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void swap(int x1, int y1, int x2, int y2) {
            int tmp;
            tmp = this.tableGame[y2][x2];
            this.tableGame[y2][x2] =  this.tableGame[y1][x1];
            this.tableGame[y1][x1] = tmp;
        }

        public void printBoard() {
            for(int i=0; i < sizeN; i++) {
                for(int j=0; j <sizeM; j++) {
                    System.out.printf("%3s", Integer.toString(tableGame[i][j]));
                }
                System.out.println();
            }
        }

        public void copyBoard(int[][] tab) {
            for(int i=0; i < sizeN;i++) {
                for(int j=0; j < sizeM;j++) {
                    this.tableGame[i][j] = tab[i][j];
                }
            }
        }

        private void left()
        {
            if(indexX != 0) {
                swap(indexX, indexY, indexX - 1, indexY);
                this.checkMovement = true;
            } else {
                this.checkMovement = false;
            }
        }

        private void right()
        {
            if(indexX != sizeM-1) {
                swap(indexX,indexY,indexX+1,indexY);
                this.checkMovement = true;
            } else {
                this.checkMovement = false;
            }
        }

        private void up()
        {
            if(indexY != 0) {
                swap(indexX,indexY,indexX,indexY-1);
                this.checkMovement = true;
            } else {
                this.checkMovement = false;
            }
        }

        private void down()
        {
            if(indexY != sizeN-1) {
                swap(indexX,indexY,indexX,indexY+1);
                this.checkMovement = true;
            } else {
                this.checkMovement = false;
            }
        }

        private void zero() {
            this.checkMovement = true;
        }

        private void blockMove(char movType) {
            if(movType == 'L') {
                this.blockMove = 'R';
            }
            else if(movType == 'R') {
                this.blockMove = 'L';
            }
            else if(movType == 'U') {
                this.blockMove = 'D';
            }
            else if(movType == 'D') {
                this.blockMove = 'U';
            }

        }

        public Board(int n, int m, int[][] tab, char movType) {
            tableGame = new int[4][5];
            sizeN = n;
            sizeM = m;
            copyBoard(tab);
            checkIndex();
            blockMove(movType);
            switch (movType) {
                case 'L' -> left();
                case 'R' -> right();
                case 'U' -> up();
                case 'D' -> down();
                default -> zero();
            }
        }

        public Board(int n, int m, int[][] tab, char movType, int level) {
            this.recursionLevel = level;
            tableGame = new int[n][m];
            sizeN = n;
            sizeM = m;
            copyBoard(tab);
            checkIndex();
            blockMove(movType);
            switch (movType) {
                case 'L' -> left();
                case 'R' -> right();
                case 'U' -> up();
                case 'D' -> down();
                default -> zero();
            }
        }
}


