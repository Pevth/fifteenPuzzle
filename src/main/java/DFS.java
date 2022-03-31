import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFS {
    public List<Board> stack = new ArrayList<>();
    public int recursionLevel;
    public double time;
    int n;
    int m;
    public int[][] tab;
    public int[][] finalTab;
    public char[] moveTypeList = {'L', 'R', 'U', 'D'};

    public <T> T getLast(List<T> list) {
        return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
    }

    public DFS(int level, int[][] tab, int n, int m) {
        this.n = n;
        this.m = m;
        if (level < 20) {
            recursionLevel = 20;
        } else {
            recursionLevel = level;
        }
        this.tab = tab;
    }

    public boolean startDeeping() {
        double startTime = System.nanoTime();
        stack.add(new Board(n,m,tab, '0',0));
        tab = getLast(stack).tableGame;
        Board lastMov = getLast(stack);
        stack.remove(lastMov);

        while (true) {
            if (lastMov.recursionLevel < recursionLevel) {
                for (int i = 0; i < 4; i++) {
                    if (lastMov.blockMove != moveTypeList[i])
                        stack.add(new Board(n,m,tab, moveTypeList[i], lastMov.recursionLevel + 1));
                }
            }

            tab = getLast(stack).tableGame;
            lastMov = getLast(stack);
            stack.remove(lastMov);

            while (!lastMov.checkMov()) {
                tab = getLast(stack).tableGame;
                lastMov = getLast(stack);
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
