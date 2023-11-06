import java.util.Scanner;

public class App {
    //input ship grid 
    static char grid[][];
    //to track visited node
    static boolean visited[][];
    //grid dimention
    static int n;
    //hold output number of ships
    static int aliveShipCounter;

    //forth right back left 
    // -1 0 +1 0
    // 0 1 0 -1
    static int[] moveX = { -1, 0, 1, 0 };
    static int[] moveY = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //input test cases
        int testCases = sc.nextInt();
        sc.nextLine();
        for (int t = 0; t < testCases; t++) {
            //input grid dimention
            n = sc.nextInt();
            sc.nextLine();
            //reset grid and visisted array for next input
            grid = new char[n][n];
            visited = new boolean[n][n];
            //reset shipCounter for next test case
            aliveShipCounter = 0;
            
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = line.charAt(j);
                    visited[i][j] = false;
                }
            }

            //call ship counter
            int c = shipCounter();
            //output
            System.out.printf("Case %d: %d\n", t+1, c);
            //reset n for next dimention input
            n = 0;
        }

    }

    //each x in the grid is a probable part of whole ship 
    //we start dfs to descover the rest of the ship 
    //if and only if it's not visited before 
    private static int shipCounter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'x' && !visited(i, j)){
                    dfs(i, j);
                aliveShipCounter++;
                }

            }
        }
        return aliveShipCounter;
    }

    private static void dfs(int xc, int yc) {
        visited[xc][yc] = true;
        //4 moves forth right left down by moveX & moveY array
        for (int i = 0; i < 4; i++) {
            int newXc = xc + moveX[i];
            int newYc = yc + moveY[i];
            //call dfs to discover the rest of ship and mark it as visisted
            if (isValid(newXc, newYc) && !visited(newXc, newYc) && isShipPart(newXc, newYc))
                dfs(newXc, newYc);
        }

    }

    //check if current node chipe part "alive or sink"
    private static boolean isShipPart(int xc, int yc) {
        if (grid[xc][yc] == 'x' || grid[xc][yc] == '@')
            return true;
        return false;
    }

    //avoid arrayIndexOutOfBoundException
    private static boolean isValid(int xc, int yc) {
        if (xc >= 0 && yc >= 0 && xc < n && yc < n)
            return true;
        return false;
    }

    //mark node as visisted
    private static boolean visited(int i, int j) {
        if (visited[i][j])
            return true;
        return false;
    }
}

// scan array for each x if its not visited start dfs
// dfs 4 moves condition notvisited valid @ or x
