import java.util.*;
import java.util.concurrent.TimeUnit;

public class AStar {
    public List<Board> list = new ArrayList<>();
    public List<Board> additionalList = new ArrayList<>();

    Comparator<Board> recursionSort = Comparator.comparing(Board::getDistance);
    PriorityQueue<Board> priorityQueue = new PriorityQueue<>(recursionSort);
    HashSet<String> hashList = new HashSet<String>();

    public int checkDistance;
    public int checkSize;
    public int[][] tab;
    public double time;
    public int n;
    public int m;
    public int[][] finalTab;
    public char[] moveTypeList = {'L', 'R', 'U', 'D'};

    public AStar(int[][] tab, int n, int m) {
        this.tab = tab;
        this.n = n;
        this.m = m;
    }

    public boolean startSearchingAStar(char heuristicType) throws InterruptedException {
        long a = 0;
        double startTime = System.nanoTime();
        priorityQueue.add(new Board(n, m, tab, '0',heuristicType, 0));
        Board lastMov = priorityQueue.poll();
        tab = lastMov.tableGame;
        int maxDistance = lastMov.distance;
        while (true) {
            for (int i = 0; i < 4; i++) {
                if (lastMov.blockMove != moveTypeList[i]) {
                    Board newBoard = new Board(n, m, tab, moveTypeList[i], heuristicType, lastMov.recursionLevel + 1);

                    checkSize = hashList.size();
                    hashList.add(newBoard.hashString);
                    if(checkSize != hashList.size()) {
                        if (newBoard.checkMov()) {
                            priorityQueue.add(newBoard);
                        }
                    }

                }
            }
            int z = 1;



            lastMov = priorityQueue.poll();
            a++;
            tab = lastMov.tableGame;
            if (lastMov.checkVerify()) {
                finalTab = lastMov.tableGame;
                double endTime = System.nanoTime();
                this.time = (endTime - startTime) / 1000000;
                System.out.print("Stany odwiedzone: " + a + " ");
                return true;
            }

            if (lastMov.distance > 80) {
                double endTime = System.nanoTime();
                this.time = (endTime - startTime) / 1000000;
                return false;
            }
        }

    }
}
