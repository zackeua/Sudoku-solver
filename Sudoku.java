//=========================================================================
//  Sudokulösare.
//-------------------------------------------------------------------------
//           Historik
//  Namn     Version    Datum     Beskrivning
//  Zackeus  1.0        181230    Börjat med saker.
//  Zackeus  1.0        190204    Förmodligen inte färdig.
//=========================================================================



/**
 * Sudokulösare
 * @author Zackeus Zetterberg, zaczet-7
 */
public class Sudoku {

    
    
    
    public static int[][] GRID_TO_SOLVE = {
        {9,0,0,1,0,0,0,0,5},
        {0,0,5,0,9,0,2,0,1},
        {8,0,0,0,4,0,0,0,0},
        {0,0,0,0,8,0,0,0,0},
        {0,0,0,7,0,0,0,0,0},
        {0,0,0,0,2,6,0,0,9},
        {2,0,0,3,0,0,0,0,6},
        {0,0,0,2,0,0,9,0,0},
        {0,0,1,9,0,4,5,7,0},
    };
    
    public static int[][] GRID1_SOLUTION = {
        {7,3,5,6,1,4,8,9,2},
        {8,4,2,9,7,3,5,6,1},
        {9,6,1,2,8,5,3,7,4},
        {2,8,6,3,4,9,1,5,7},
        {4,1,3,8,5,7,9,2,6},
        {5,7,9,1,2,6,4,3,8},
        {1,5,7,4,9,2,6,8,3},
        {6,9,4,7,3,8,2,1,5},
        {3,2,8,5,6,1,7,4,9},
    };
    
    
    public static int[][] GRID1_TO_SOLVE = {
        {7,3,5,6,1,4,8,9,2},
        {8,4,2,9,7,3,5,6,1},
        {9,6,1,2,8,5,3,7,4},
        {2,8,6,3,4,9,1,5,7},
        {4,1,3,8,5,7,9,2,6},
        {5,7,9,1,2,6,4,3,8},
        {1,5,7,4,9,2,6,8,3},
        {6,9,4,7,3,8,2,1,5},
        {3,2,8,5,6,1,7,4,9},
    };
    
    
    public static int[][] GRID2_TO_SOLVE = {
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
    };
    
    
    
    private int[][] grid;
    public static final int SIZE = 9;
    public static final int BOX_SIZE = 3;
    public static final int EMPTY = 0;
    
    public Sudoku(int[][] grid) {
        this.grid = new int[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }
    
    
    // kollar om ett vist värde i ett element är ett 
    // "legalt drag" med avseende på raden elementet är i
    private boolean legalNumberInRow(int xIndex, int yIndex, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (i == yIndex) {
                i++;
            } else if (this.grid[xIndex][i] == number) {
                return true;
            }
        }
        return false;
    }
    
    // kollar om ett vist värde i ett element är ett 
    // "legalt drag" med avseende på kolumnen elementet är i
    private boolean legalNumberInCol(int xIndex, int yIndex, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (i == xIndex) {
                i++;
            } else if (this.grid[i][yIndex] == number) {
                return true;
            }
        }
        return false;
    }
    
    // kollar om ett vist värde i ett element är ett 
    // "legalt drag" med avseende på boxen elementet är i
    private boolean legalNumberInBox(int xIndex, int yIndex, int number) {
        int xBox = (int)(xIndex / BOX_SIZE);
        int yBox = (int)(yIndex / BOX_SIZE);
        
        for (int i = BOX_SIZE*xBox; i < xBox + BOX_SIZE; i++) {
            for (int j = BOX_SIZE*yBox; j < yBox + BOX_SIZE; j++){
                if (i == xIndex && j == yIndex) {
                    j++;
                } else if (this.grid[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    // kollar på värdet number redan finns i raden xIndex
    private boolean isInRow(int xIndex, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (this.grid[xIndex][i] == number) {
                return true;
            }
        }
        return false;
    }
    
    
    // kollar på värdet number redan finns i kolumnen yIndex
    private boolean isInCol(int yIndex, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (this.grid[i][yIndex] == number) {
                return true;
            }
        }
        return false;
    }
    
    // kollar på värdet number redan finns i 
    // boxen som innehåller  elementet xIndex,yIndex
    private boolean isInBox(int xIndex, int yIndex, int number) {
        int xBox = (int)(xIndex / BOX_SIZE);
        int yBox = (int)(yIndex / BOX_SIZE);
        
        for (int i = BOX_SIZE*xBox; i < xBox + BOX_SIZE; i++) {
            for (int j = BOX_SIZE*yBox; j < yBox + BOX_SIZE; j++){
                if (this.grid[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    // returnerar första tomma (0) elementet
    // om inga tomma element finns så returneras (-1,-1)
    private int[] emptyElement() {
        int[] element = new int[2];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.grid[i][j] == 0) {
                    element[0] = i;
                    element[1] = j;
                    return element;
                }
            }
        }
        element[0] = -1;
        element[1] = -1;
        return element;
        
    }
    
    
    
    // Kollar om sudokun är löst vilket görs genom 
    // att kolla att alla element uppfyller att 
    // alla element uppfyller de tre kriterierna
    private boolean solved() {
        int count;
        
        
        // testar raderna
        for (int xIndex = 0; xIndex < SIZE; xIndex++) {
            count = 0;
            for (int number = 1; number < SIZE + 1; number++) {
                if (this.isInRow(xIndex,number)) {
                    count++;
                }
            }
            if (count != SIZE) {
                return false;
            }
        }
        
        // testar kolonnerna
        for (int yIndex = 0; yIndex < SIZE; yIndex++) {
            count = 0;
            for (int number = 1; number < SIZE + 1; number++) {
                if (this.isInRow(yIndex,number)) {
                    count++;
                }
            }
            if (count != SIZE) {
                return false;
            }
        }
        
        // testar boxerna
        for (int xIndex = 0; xIndex < SIZE; xIndex++) {
            for (int yIndex = 0; yIndex < SIZE; yIndex++) {
                count = 0;
                for (int number = 1; number < SIZE + 1; number++) {
                    if(this.isInBox(xIndex, yIndex, number)) {
                        count++;
                    }
                }
                if (count != SIZE) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // kollar om ett givet värde är ett 
    // "legalt drag" i ett givet element  
    private boolean isLegal(int xIndex, int yIndex, int number) {
        return (!this.legalNumberInRow(xIndex,yIndex,number) && !this.legalNumberInCol(xIndex,yIndex,number) && !this.legalNumberInBox(xIndex,yIndex,number));
    }
    
    // sätter in ett givet värde i ett givet element (xIndex, yIndex)
    private int[][] addElement(int xIndex, int yIndex, int number) {
        int[][] newint = new int[SIZE][SIZE];
        newint = this.grid;
        
        newint[xIndex][yIndex] = number;
        
        return newint;
    }
    
    // löser sudokun v1    
    private Sudoku solve() {
        
        int[] element = this.emptyElement();
        
        if (element[0] != -1) {
            for (int guess = 1; guess < SIZE + 1; guess++) {
                if (this.solved()) {
                    return this;
                } else if (this.isLegal(element[0],element[1],guess)) {
                    Sudoku inter = new Sudoku(this.addElement(element[0],element[1],guess));
                    System.out.println("guess = " + guess);
                    inter.solve();
                }
            }
        } else {
            return this;
        }
        return this;
    }
    
    
    // löser sudokun v2    
    private static Sudoku solve(Sudoku object) {
        
        int[] element = object.emptyElement();
        
        
        
        if (element[0] != -1) {
            for (int guess = 1; guess < SIZE + 1; guess++) {
                if (object.isLegal(element[0],element[1],guess)) {
                    System.out.println("xIndex = " + element[0] + ", yIndex = " + element[1] + ", guess = " + guess);
                    Sudoku inter = new Sudoku(object.addElement(element[0],element[1],guess));
                    object = solve(inter);
                }
            }
        }
        return object;
    }
    
    
    
    
    
    // toString metoden
    public String toString() {
        String string = new String("");
        for (int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                string  = string + this.grid[i][j]  + " ";
                if (j == 2 || j == 5) {
                    string = string + " ";
                }
            }
            string = string + "\n";
            if (i == 2 || i == 5) {
                string = string + "\n";
            }
        }
        return string;
    }
    
    
    
    
    
    // main metoden
    public static void main(String[] args) {
        
        
        
        Sudoku sol = new Sudoku(GRID1_SOLUTION);
        Sudoku foo = new Sudoku(GRID1_TO_SOLVE);
        
        
        
        System.out.print(sol.toString());
        System.out.print(foo.toString());
        
        Sudoku solved = solve(foo);
        
        System.out.println("Solution:");
        
        System.out.print(solved.toString());
        System.out.print(foo.solved());
    }
}
