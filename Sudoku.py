from math import sqrt

class Sudoku:

    def __init__(self, grid = None):

        if grid != None:
            size = len(grid)
            assert sqrt(size) == int(sqrt(size))
            self._size = size
            self._box_size = int(sqrt(size))
            self._grid = [[number if number != 0 else None for number in row] for row in grid]
        else:
            self._size = 9
            self._box_size = 3
            self._grid = [[None for _ in range(self._size)] for _ in range(self._size)]
        #self._possible = [[[] for _ in row] for row in self._grid]

    def __str__(self):
        s = ""
        for rowIndex, row in enumerate(self._grid):
            if rowIndex%self._box_size == 0:
                s = s + '\n'
            for elementIndex, element in enumerate(row):
                if elementIndex%self._box_size == 0:
                    s = s + " "
                s = s + str(element or "0") + " "
            s = s + '\n'

        return s

    def isInRow(self, xIndex, number):
        """Checks if a number is in a given Row"""
        #return number in self.grid[xIndex]
        return number in [value for value in self._grid[xIndex][:]]

    def isInCol(self, yIndex, number):
        """Checks if a number is in a given Column"""
        #return number in self.grid[:][yIndex]
        #return number in [value for value in self.grid[:][yIndex]]
        for i in range(self._size):
            if self._grid[i][yIndex] == number:
                return True
        return False



    def isInBox(self, xIndex, yIndex, number):
        """Checks if a nmber is in a given box"""
        #return number in [a[i] for a in self.grid[xBox:xBox+BOX_SIZE] for i in range(BOX_SIZE*yBox,BOX_SIZE*yBox+BOX_SIZE)]
        xBox = xIndex//self._box_size
        yBox = yIndex//self._box_size
        for i in range(self._box_size * xBox, self._box_size * xBox + self._box_size):
            for j in range(self._box_size * yBox, self._box_size * yBox + self._box_size):
                if self._grid[i][j] == number:
                    return True
        return False

    def get_possible(self):
        possible = []
        for i, row in enumerate(self._grid):
            for j, element in enumerate(row):
                #self._possible[i][j] = []
                temp = []
                if element == None: # empty
                    for num in range(1, self._size +1):
                        if self.isLegal([i, j], num):
                            #self._possible[i][j].append(num)
                            temp.append(num)
                    if len(temp) == 1:
                        possible.append([i, j, temp[0]])
        return possible

    def update_possible(self):
        possible = self.get_possible()
        updated = []
        flag = False
        while (len(possible) > 0):
            for t in possible:
                try:
                    self._grid[t[0]][t[1]] = t[2] # self._possible[t[0]][t[1]][0]
                    updated.append(t)
                    possible = self.get_possible()
                except:
                    flag = True
        if flag:
            print('reset')
            for tup in updated:
                self._grid[tup[0]][tup[1]] = None


    
    def getEmptyElement(self):
        """Return the first empty element in the grid, if no element is empty, return None"""
        for i, row in enumerate(self._grid):
            for j, element in enumerate(row):
                if element == None:
                    return [i, j]
        return None

    def solved(self):
        """Check if the sudoku is solved"""
        full = self.getEmptyElement()
        if full != None: # if sudoku is not full it is not solved
            return False

        for number in range(1, self._size + 1):
            for position in range(self._size):
                if not (self.isInRow(position, number)):
                    return False
                if not (self.isInCol(position, number)):
                    return False
            for xIndex in range(0, self._size, self._box_size):
                for yIndex in range(0, self._size, self._box_size):
                    if  not (self.isInBox(xIndex, yIndex, number)):
                        return False
        return True

    def isLegal(self, index, number):
        """Check if a number is allowed in a given position"""
        # can only insert values in the array
        if index == None:
            return False
        # if the value is alread y in the row, column or box
        # then the value is illegal
        return (not(self.isInRow(index[0], number)) and
                not(self.isInCol(index[1], number)) and
                not(self.isInBox(index[0], index[1], number)))


    def addElement(self, index, number):
        """Return a new sudoku with the number inserted at a given valid index"""
        result = Sudoku(self._grid)
        if index != None:
            result.grid[index[0]][index[1]] = number
        return result


    def solve(self):
        """Solves the sudoku"""
        if self.solved():
            return self
        
        #self.update_possible()
        #print('backtrack')
        index = self.getEmptyElement()
        for guess in range(1, self._size + 1):
            if self.isLegal(index, guess):
                #temp = self.addElement(index, guess)
                self._grid[index[0]][index[1]] = guess
                self = self.solve()
                if self.solved():
                    return self
                else:
                    self._grid[index[0]][index[1]] = None
        return self

def main():
    """main function"""
    '''
    Grid = [[9,2,3,1,0,0,0,0,5],
            [4,7,5,0,9,0,2,0,1],
            [8,1,6,0,4,0,0,0,0],
            [0,0,0,0,8,0,0,0,0],
            [0,0,0,7,0,0,0,0,0],
            [0,0,0,0,2,6,0,0,9],
            [2,0,0,3,0,0,0,0,6],
            [0,0,0,2,0,0,9,0,0],
            [0,0,1,9,0,4,5,7,0]]
    '''

    Grid = [[9,0,0,1,0,0,0,0,5],
            [0,0,5,0,9,0,2,0,1],
            [8,0,0,0,4,0,0,0,0],
            [0,0,0,0,8,0,0,0,0],
            [0,0,0,7,0,0,0,0,0],
            [0,0,0,0,2,6,0,0,9],
            [2,0,0,3,0,0,0,0,6],
            [0,0,0,2,0,0,9,0,0],
            [0,0,1,9,0,4,5,7,0]]

    empty = [[0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0],
             [0,0,0,0,0,0,0,0,0]]
    """
    empty = [[0,2,0, 0,4,0, 0,7,0],
                  [1,0,0, 0,2,0, 0,0,0],
                  [3,0,4, 8,9,0, 0,0,0],
                  [0,8,0, 0,0,9, 0,0,1],
                  [4,3,0, 0,6,2, 0,0,5],
                  [9,0,0, 5,3,8, 4,0,0],
                  [0,0,5, 0,0,3, 0,2,4],
                  [0,0,0, 0,0,7, 0,1,0],
                  [0,0,1, 0,0,0,0,0,6]]
    """
    sudo = Sudoku(Grid)
    #sudo = Sudoku(empty)

    print(sudo)

    solved = sudo.solve()

    print(solved)

if __name__ == '__main__':
    main()
