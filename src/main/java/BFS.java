import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BFS {
        public List<Board> stack = new ArrayList<>();
        public HashSet<String> hashList;
        public int checkSize;
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
            int a = 0;
            hashList = new HashSet<>();
            double startTime = System.nanoTime();
            stack.add(new Board(n,m,tab, '0'));
            hashList.add(stack.get(0).hashString);
            tab = stack.get(0).tableGame;
            Board lastMov = stack.get(0);
            stack.remove(lastMov);

            while (true) {
                    for (int i = 0; i < 4; i++) {
                        if (lastMov.blockMove != moveTypeList[i]) {
                            stack.add(new Board(n, m, tab, moveTypeList[i]));
                            checkSize = hashList.size();
                            hashList.add(stack.get(stack.size()-1).hashString);
                            if(checkSize == hashList.size()) {
                                stack.remove(stack.size()-1);
                            }
                        }
                }
                a++;
                tab = stack.get(0).tableGame;
                lastMov = stack.get(0);
                stack.remove(lastMov);

                while (!lastMov.checkMov()) {
                    tab = stack.get(0).tableGame;
                    lastMov =stack.get(0);
                    stack.remove(lastMov);
                }

                if (lastMov.checkVerify()) {
                    System.out.print("Stany odwiedzone: " + a + " ");
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
