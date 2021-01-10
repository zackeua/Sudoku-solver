//=========================================================================
//  Sudokulösare.
//-------------------------------------------------------------------------
//              Historik
//  Namn        Version     Datum       Beskrivning
//  Example     1.1         YYMMDD      Discription...
//  Zackeus     0.1         181230      Börjat med saker.
//  Zackeus     0.2         190204      Förmodligen inte färdig.
//  Zackeus     0.3         190506      Strukturerar om skiten.
//  Zackeus     1.0         190930      Färdig
//=========================================================================

/**
 * Sudokulösare
 * @author Zackeus Zetterberg, zackster snackster
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

    public static int[][] EXGRID_TO_SOLVE = {
        {9,2,3,1,0,0,0,0,5},
        {4,7,5,0,9,0,2,0,1},
        {8,1,6,0,4,0,0,0,0},
        {0,0,0,0,8,0,0,0,0},
        {0,0,0,7,0,0,0,0,0},
        {0,0,0,0,2,6,0,0,9},
        {2,0,0,3,0,0,0,0,6},
        {0,0,0,2,0,0,9,0,0},
        {0,0,1,9,0,4,5,7,0},
    };


    public static int[][] GRID_SOLUTION = {
        {9,3,4,1,7,2,6,8,5},
        {7,6,5,8,9,3,2,4,1},
        {8,1,2,6,4,5,3,9,7},
        {4,2,9,5,8,1,7,6,3},
        {6,5,8,7,3,9,1,2,4},
        {1,7,3,4,2,6,8,5,9},
        {2,9,7,3,5,8,4,1,6},
        {5,4,6,2,1,7,9,3,8},
        {3,8,1,9,6,4,5,7,2},
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
        {0,3,5,6,1,4,8,9,2},
        {8,4,2,9,7,3,5,6,1},
        {9,6,1,2,8,5,3,7,4},
        {2,8,6,3,4,9,1,5,7},
        {4,1,3,8,5,7,9,2,6},
        {5,7,9,1,2,6,4,3,8},
        {1,5,7,4,9,2,6,8,3},
        {6,9,4,7,3,8,2,1,5},
        {3,2,8,5,6,1,7,4,9},
    };

    public static int[][] EV = {
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
    };

    public static int[][] EMPTY = {
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

    public static int[][] EMPTY_SOLUTION = {
        {1,2,3,4,5,6,7,8,9},
        {4,5,6,7,8,9,1,2,3},
        {7,8,9,1,2,3,4,5,6},
        {2,1,4,3,6,5,8,9,7},
        {3,6,5,8,9,7,2,1,4},
        {8,9,7,2,1,4,3,6,5},
        {5,3,1,6,4,2,9,7,8},
        {6,4,2,9,7,8,5,3,1},
        {9,7,8,5,3,1,6,4,2},
    };

    private int[][] grid;



    public static final int BOX_SIZE = 3;
    public static final int SIZE = BOX_SIZE*BOX_SIZE;
    public static final int EMPTY_ELEMENT = 0;


    /**
     * Standard constructor
     * @return Empty sudoku.
     */
    public Sudoku() {
        this.grid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE ; j++) {
                this.grid[i][j] = 0;
            }
        }
    }

    /**
     * Constructor
     * @param grid matrix with the values that the sudoku is supposed to be solved for.
     * @return Sudoku that is supposed to be solved.
     */
     public Sudoku(int[][] grid) {
         this.grid = new int[SIZE][SIZE];
         for (int i = 0; i < SIZE; i++) {
             for (int j = 0; j < SIZE; j++) {
                 this.grid[i][j] = grid[i][j];
             }
         }
     }



     /**
      * Checks if a value is in a specified row
      * @param xIndex the row to be searched
      * @param number the value to be searched for
      * @return boolean true of the element is found, false othervise.
      */
    public boolean isInRow(int xIndex, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (this.grid[xIndex][i] == number) {
                return true;
            }
        }
        return false;
    }

     /**
      * Checks if a value is in a specified column
      * @param yIndex the column to be searched
      * @param number the value to be searched for
      * @return boolean true if the element is found, false othervise.
      */
    private boolean isInCol(int yIndex, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (this.grid[i][yIndex] == number) {
                return true;
            }
        }
        return false;
    }


     /**
      * Checks if a value is in a specified box
      * @param xIndex the row for one of the rows in the box
      * @param yIndex the column for one of the columns in the box
      * @param number the value to be searched for
      * @return boolean true if the element is in the box, false othervise.
      */
    public boolean isInBox(int xIndex, int yIndex, int number) {
        int xBox = (int)(xIndex / BOX_SIZE);
        int yBox = (int)(yIndex / BOX_SIZE);

        for (int i = BOX_SIZE*xBox; i < BOX_SIZE*xBox + BOX_SIZE; i++) {
            for (int j = BOX_SIZE*yBox; j < BOX_SIZE*yBox + BOX_SIZE; j++){
                if (this.grid[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }


     /**
      * Looks for an empty element
      * @return int[2] (xIndex, yIndex) index for the first empty element.
      * If no empty element is found the element [-1,-1] is retuned.
      */
    public int[] getEmptyElement() {
        int[] element = {-1,-1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.grid[i][j] == 0) {
                    element[0] = i;
                    element[1] = j;
                    return element; // only here to solve the sudoku from upper right corner
                }
            }
        }
        return element;
    }

     /**
      * Checks if the sudoku is solved:
      * If any element is not filled, then it is not solved.
      * But it might not be solved...
      * Therefore we check if all numbers are in all rows, columns and boxes...
      * @return boolean true if the sudoku is solved, else false.
      */

     //Städa upp den här skiten
    public boolean solved() {
        int[] element = new int[2];
        element = this.getEmptyElement(); // if empty element is [-1,-1] the sudoku is full

        if (element[0] != -1) {
          return false;
        }

        for (int number = 1; number < SIZE + 1 ; number++) { // loop over all 9 test numbers

          for (int i = 0; i < SIZE; i++) {
              // testar raderna
              if (!this.isInRow(i, number)) { // test all rows
                  return false;
              }
              // testar kolonnerna
              if (!this.isInCol(i, number)) { // test all columns
                  return false;
              }
          }

            // testar boxerna
            for (int i = 0; i < SIZE; i = i + BOX_SIZE) { // code for testing boxes
                for (int j = 0; j < SIZE; j = j + BOX_SIZE) { // incremets in larger steps to
                    if(!this.isInBox(i, j, number)) { // not check the same fucking boxes multiple times
                        return false;
                    }
                }
            }
        }
        return true;
    }

     /**
      * checks if a move is an allowed move
      * @param xIndex row for the element.
      * @param yIndex column for the element.
      * @param number the value to be tested
      * @return boolean true if allowed, else false.
      */
    public boolean isLegal(int xIndex, int yIndex, int number) {
    // You can not put in values outside the array
    if (xIndex == -1 || yIndex == -1) {
        return false;
    }

    // If the value already is in the row, column or box
    // then the move is illegal
    return (!this.isInRow(xIndex, number) &&
            !this.isInCol(yIndex, number) &&
            !this.isInBox(xIndex, yIndex, number));
    }

     /*
     /**
      * Adds a value to the grid.
      * @param xIndex row for the element.
      * @param yIndex column for the element.
      * @param number the value to be added

    private void addElement(int xIndex, int yIndex, int number) {
        if (xIndex != -1) {
            this.grid[xIndex][yIndex] = number;
        }
    }
    */



    /**
     * Adds a value to the grid.
     * @param xIndex row for the element.
     * @param yIndex column for the element.
     * @param number the value to be added.
     * @return Sudoku a sudoku that have the value added
     */
   private Sudoku addElement(int xIndex, int yIndex, int number) {
       Sudoku result = new Sudoku();
       for (int x = 0; x < SIZE; x++) {
           for (int y = 0; y < SIZE; y++) {
               result.grid[x][y] = this.grid[x][y];
           }
       }
       if (xIndex != -1) {
           result.grid[xIndex][yIndex] = number;
       }
       return result;
   }

     /**
      * Solves the sudoku
      * @param objekt the sudoku to be solved.
      * @return Sudoku that hopfully is solved
      */
    public static Sudoku solve(Sudoku object) {
        Sudoku object2;
        if (object.solved()) {
            return object;
        }



        int[] element = object.getEmptyElement();





        for (int guess = 1; guess <= SIZE ; guess++ ) {
            if (object.isLegal(element[0], element[1], guess)) {

                object2 = object.addElement(element[0], element[1], guess);
                object2 = solve(object2);

                if (object2.solved()) {
                    return object2;
                }
            }
        }
        return object;
    }



    public static Sudoku evaluater(Sudoku object) {
        System.out.println();
        System.out.println("Puzzle: ");
        System.out.println(object.toString());
        System.out.println("Puzzle solved? " + object.solved());

        System.out.println();
        System.out.println("Solution?: ");
        Sudoku solved = solve(object);
        System.out.println(solved.toString());
        System.out.println("Solution solved? " + solved.solved());
        return solved;
    }

    public static Sudoku evaluater(Sudoku object, Sudoku solution) {
        System.out.println();
        System.out.println("Known solution: ");
        System.out.println(solution.toString());
        System.out.println("Known solution solved? " + solution.solved());

        System.out.println();
        System.out.println("Puzzle: ");
        System.out.println(object.toString());
        System.out.println("Puzzle solved? " + object.solved());

        System.out.println();
        System.out.println("Solution?: ");
        Sudoku solved = solve(object);
        System.out.println(solved.toString());
        System.out.println("Solution solved? " + solved.solved());
        return solved;
    }

    /**
     * toString method
     * @return string a String representation of the Sudoku.
     */
    public String toString() {
        String string = new String("|------+------+------|\n|");
        for (int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                string  = string + this.grid[i][j]  + " ";
                if (j%BOX_SIZE == BOX_SIZE-1) {
                    string = string + "|";
                }
            }
            string = string + "\n|";
            if (i%BOX_SIZE == BOX_SIZE-1 && i < SIZE -1) {
                string = string + "------+------+------|\n|";
            } else if (i%BOX_SIZE == BOX_SIZE-1) {
                string = string + "------+------+------|\n";
            }
        }
        return string;
    }

    // main metoden
    public static void main(String[] args) {


        Sudoku foo = new Sudoku(GRID_TO_SOLVE);
        Sudoku foo_sol = new Sudoku(GRID_SOLUTION);

        //Sudoku foo1 = new Sudoku(GRID1_TO_SOLVE);
        //Sudoku foo1_sol = new Sudoku(GRID1_SOLUTION);

        //Sudoku foo2 = new Sudoku(EMPTY);
        //Sudoku foo2_sol = new Sudoku(EMPTY_SOLUTION);

        //Sudoku foo3 = new Sudoku(EV);

        //System.out.println(foo2.getEmptyElement()[0] + " " + foo2.getEmptyElement()[1]);
        evaluater(foo, foo_sol);

        //evaluater(foo1, foo1_sol);

        //evaluater(foo2);

    }
}
