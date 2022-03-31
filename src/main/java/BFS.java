import java.util.ArrayList;
import java.util.List;

public class BFS {
        public List<Board> stack = new ArrayList<>();
        public int[][] tab;
        public double time;
        public int n;
        public int m;
        public int[][] finalTab;
        public char[] moveTypeList = {'L', 'R', 'U', 'D'};

        public BFS(int[][] tab, int n, int m) {
            this.tab = tab;
            this.n = n;
            this.m = m;
        }

        public boolean startSearching() {
            double startTime = System.nanoTime();
            stack.add(new Board(n,m,tab, '0'));
            tab = stack.get(0).tableGame;
            Board lastMov = stack.get(0);
            stack.remove(lastMov);

            while (true) {
                    for (int i = 0; i < 4; i++) {
                        if (lastMov.blockMove != moveTypeList[i])
                            stack.add(new Board(n,m,tab, moveTypeList[i]));
                }

                tab = stack.get(0).tableGame;
                lastMov = stack.get(0);
                stack.remove(lastMov);

                while (!lastMov.checkMov()) {
                    tab = stack.get(0).tableGame;
                    lastMov =stack.get(0);
                    stack.remove(lastMov);
                }

                if (lastMov.checkVerify()) {
                    finalTab = lastMov.tableGame;
                    double endTime = System.nanoTime();
                    this.time = (endTime - startTime)/1000000;
                    return true;

                }
                if (stack.size() == 0) {
                    double endTime = System.nanoTime();
                    this.time = (endTime - startTime)/1000000;
                    return false;
                }
            }
        }
    }
