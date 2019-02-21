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
        {0,0,0,1,2,3,0,0,0},
        {0,0,0,4,5,6,0,0,0},
        {0,0,0,7,8,0,0,0,0},
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



    private int[][] grid;
    public static final int BOX_SIZE = 3;
    public static final int SIZE = BOX_SIZE*BOX_SIZE;
    public static final int EMPTY_ELEMENT = 0;


   /**
    * Konstruktor
    * @param grid matris med värderna som sudokun skall lösas för.
    * @return Sudoku som skall lösas.
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
    * Kollar om ett visst värde i ett givet element är korrekt
    * insatt med avseende på den raden elementet är med i
    * @param xIndex raden för elementet
    * @param yIndex kolumnen för elementet
    * @return boolean true om legalt, false om ej.
    */
    public boolean legalNumberInRow(int xIndex, int yIndex) {
        for (int i = 0; i < SIZE; i++) {
            if (i != yIndex) {
              if (this.grid[xIndex][i] == this.grid[xIndex][yIndex]) {
                  return false;
              }
            }
        }
        return true;
    }

    /**
     * Kollar om ett visst värde i ett givet element är korrekt
     * insatt med avseende på den kolumnen elementet är med i
     * @param xIndex raden för elementet
     * @param yIndex kolumnen för elementet
     * @return boolean true om legalt, false om ej.
     */
    public boolean legalNumberInCol(int xIndex, int yIndex) {
        for (int i = 0; i < SIZE; i++) {
            if (i != xIndex) {
              if (this.grid[i][yIndex] == this.grid[xIndex][yIndex]) {
                  return false;
              }
            }
        }
        return true;
    }

    /**
    * Kollar om ett visst värde i ett givet element är korrekt
    * insatt med avseende på den boxen elementet är med i
     * @param xIndex raden för elementet
     * @param yIndex kolumnen för elementet
     * @return boolean true om legalt, false om ej.
     */
    public boolean legalNumberInBox(int xIndex, int yIndex) {
        int xBox = (int)(xIndex / BOX_SIZE);
        int yBox = (int)(yIndex / BOX_SIZE);

        for (int i = BOX_SIZE*xBox; i < BOX_SIZE*xBox + BOX_SIZE; i++) {
            for (int j = BOX_SIZE*yBox; j < BOX_SIZE*yBox + BOX_SIZE; j++){
                if (i == xIndex && j == yIndex) {
                  if (this.grid[i][j] == this.grid[xIndex][yIndex]) {
                      return false;
                  }
                }
            }
        }
        return true;
    }



    /**
     * Kollar om ett visst värde finns i en rad är ett
     * @param xIndex raden för elementet
     * @param number värdet som ska kontrolleras
     * @return boolean true om element finns på raden, false om ej.
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
     * Kollar om ett visst värde finns i en kolumn är ett
     * @param yIndex kolumnen för elementet
     * @param number värdet som ska kontrolleras
     * @return boolean true om element finns i kolonnen, false om ej.
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
     * Kollar om ett visst värde finns i en box är ett
     * @param xIndex raden för ett element i boxen
     * @param yIndex kolumnen för ett element i boxen
     * @param number värdet som ska kontrolleras
     * @return boolean true om element finns i boxen, false om ej.
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
     * Kollar om ett element är tomt
     * @return int[2] (xIndex, yIndex) index för första
     * tomma elementet. Om inget tomt element existerar returneras (-1,-1)
     */
    public int[] emptyElement() {
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





    /**
     * Kollar om sudokun är löst vilket görs genom
     * att först kolla om det finns något tomt element
     * ifall det finns ett tomt element så är sudokun ej löst.
     * Om det inte finns något tomt element så kan
     * det finnas dubletter sedan tidigare
     * kolla att alla element uppfyller att
     * alla element uppfyller de tre kriterierna
     * @return boolean true om löst, false annars.
     */
    public boolean solved() {
        int[] element = new int[2];

        element = this.emptyElement();

        if (element[0] != -1) {
          return false;
        }

        for (int number = 1; number < SIZE + 1 ; number++) {

          for (int i = 0; i < SIZE; i++) {
              // testar raderna
              if (!this.isInRow(i, number)) {
                  return false;
              }
              // testar kolonnerna
              if (!this.isInCol(i, number)) {
                  return false;
              }
          }

            // testar boxerna
            for (int i = 0; i < SIZE; i = i + BOX_SIZE) {
                for (int j = 0; j < SIZE; j = j + BOX_SIZE) {

                        if(!this.isInBox(i, j, number)) {
                            return false;
                        }
                    }
            }
        }
        return true;
    }



    /**
     * kollar om ett givet värde är ett
     * "legalt drag" i ett givet element
     * @param xIndex rad för elementet.
     * @param yIndex kolumn för elementet.
     * @param number värde som testas för insättning i elementet
     * @return boolean true om legalt drag, false annars.
     */
    public boolean isLegal(int xIndex, int yIndex, int number) {
        if (xIndex == -1 || yIndex == -1) {
            return false;
        }

        //return (this.legalNumberInRow(xIndex, yIndex) && this.legalNumberInCol(xIndex, yIndex) && this.legalNumberInBox(xIndex, yIndex));
         return (!this.isInRow(xIndex, number) &&
                !this.isInCol(yIndex, number) &&
                !this.isInBox(xIndex, yIndex, number));
    }

    /**
     * kollar om ett givet värde är ett
     * "legalt drag" i ett givet element
     * @param xIndex rad för elementet.
     * @param yIndex kolumn för elementet.
     * @param number värde som ska sättas in i elementet
     * @return int[][] ny matris med värdet
     * (number) i element (xIndex,yIndex).
     */
    private int[][] addElement(int xIndex, int yIndex, int number) {
        int[][] newint = new int[SIZE][SIZE];
        newint = this.grid;
        newint[xIndex][yIndex] = number;
        return newint;
    }



    /** löser sudokun v1
     * löser this sudoku
     * @return Sudoku som förhoppningsvist är löst
     */
    public Sudoku solve() {

      if (!this.solved()) {
          int[] element = this.emptyElement();
          for (int guess = 1; guess < SIZE + 1; guess++) {
              if (this.isLegal(element[0], element[1], guess)) {
                  Sudoku inter = new Sudoku(this.addElement(element[0], element[1], guess));
                  System.out.println("guess = " + guess);
                  inter.solve();
              }
          }
      }
        return this;
    }

    /** löser sudokun v2
     * löser this sudoku
     * @param objekt den sudoku som skall lösas.
     * @return Sudoku som förhoppningsvist är löst
     */
    public static Sudoku solve(Sudoku object) {

      if (!object.solved()) {
          int[] element = object.emptyElement();
          for (int guess = 1; guess < SIZE + 1; guess++) {
              //System.out.println("" + guess);
              if (object.isLegal(element[0], element[1], guess)) {
                  //System.out.println("xIndex = " + element[0] + ", yIndex = " + element[1] + ", guess = " + guess);
                  Sudoku inter = new Sudoku(object.addElement(element[0], element[1], guess));
                  //System.out.println(inter.toString());
                  object = solve(inter);
              }
          }
      }
        return object;
    }


    public static Sudoku evaluater(Sudoku object) {
        System.out.println("Puzzle: ");
        System.out.println(object.toString());
        System.out.println("Puzzle solved? " + object.solved());

        System.out.println("Solution?: ");
        Sudoku solved = solve(object);
        System.out.println(solved.toString());
        System.out.println("Solution solved? " + solved.solved());
        return solved;
    }
    public static Sudoku evaluater(Sudoku object, Sudoku solution) {
        System.out.println("Known solution: ");
        System.out.println(solution.toString());
        System.out.println("Known solution solved? " + solution.solved());

        System.out.println("Puzzle: ");
        System.out.println(object.toString());
        System.out.println("Puzzle solved? " + object.solved());

        System.out.println("Solution?: ");
        Sudoku solved = solve(object);
        System.out.println(solved.toString());
        System.out.println("Solution solved? " + solved.solved());
        return solved;
    }




    /** toString metoden
     *
     * @return string som är en textrepresentation av en Sudoku.
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

        Sudoku foo1 = new Sudoku(GRID1_TO_SOLVE);
        Sudoku foo1_sol = new Sudoku(GRID1_SOLUTION);

        Sudoku foo2 = new Sudoku(EMPTY);
        Sudoku foo3 = new Sudoku(EV);

    //    System.out.println("" + foo3.isInBox(3,3,1));

        evaluater(foo, foo_sol);

        evaluater(foo1, foo1_sol);

        evaluater(foo2);

    }
}
