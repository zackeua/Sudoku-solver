BOX_SIZE = 3
SIZE = BOX_SIZE * BOX_SIZE
EMPTY_ELEMENT = None
class Sudoku:

    def __init__(self, grid = None):

        self.grid = [[None for _ in range(SIZE)] for _ in range(SIZE)]
        if grid != None:
            self.grid = [[number if number != 0 else None for number in row] for row in grid]
            '''
            for i in range(SIZE):
                for j in range(SIZE):
                    if list[i][j] != 0:
                        self.grid[i][j] = list[i][j]
            '''

    def __str__(self):
        s = ""
        for rowIndex, row in enumerate(self.grid):
            if rowIndex%BOX_SIZE == 0:
                s = s + '\n'
            for elementIndex, element in enumerate(row):
                if elementIndex%BOX_SIZE == 0:
                    s = s + " "
                s = s + str(element or "0") + " "
            s = s + '\n'

        return s

    def isInRow(self, xIndex, number):
        """Checks if a number is in a given Row"""
        #return number in self.grid[xIndex]
        return number in [value for value in self.grid[xIndex][:]]
        '''
        for i in range(SIZE):
            if self.grid[xIndex][i] == number:
                return True
        return False
        '''

    def isInCol(self, yIndex, number):
        """Checks if a number is in a given Column"""
        #return number in self.grid[:][yIndex]
        #return number in [value for value in self.grid[:][yIndex]]
        for i in range(SIZE):
            if self.grid[i][yIndex] == number:
                return True
        return False



    def isInBox(self, xIndex, yIndex, number):
        """Checks if a nmber is in a given box"""
        #return number in [a[i] for a in self.grid[xBox:xBox+BOX_SIZE] for i in range(BOX_SIZE*yBox,BOX_SIZE*yBox+BOX_SIZE)]
        xBox = xIndex//BOX_SIZE
        yBox = yIndex//BOX_SIZE
        for i in range(BOX_SIZE * xBox, BOX_SIZE * xBox + BOX_SIZE):
            for j in range(BOX_SIZE * yBox, BOX_SIZE * yBox + BOX_SIZE):
                if self.grid[i][j] == number:
                    return True
        return False


    def getEmptyElement(self):
        """Return the first empty element in the grid, if no element is empty, return None"""
        for i, row in enumerate(self.grid):
            for j, element in enumerate(row):
                if element == None:
                    return [i, j]
        return None

    def solved(self):
        """Check if the sudoku is solved"""
        full = self.getEmptyElement()
        if full != None: # if sudoku is not full it is not solved
            return False

        for number in range(1, SIZE + 1):
            for position in range(SIZE):
                if not (self.isInRow(position, number)):
                    return False
                if not (self.isInCol(position, number)):
                    return False
            for xIndex in range(0, SIZE, BOX_SIZE):
                for yIndex in range(0, SIZE, BOX_SIZE):
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
        result = Sudoku(self.grid)
        if index != None:
            result.grid[index[0]][index[1]] = number
        return result


    def solve(self):
        """Solves the sudoku"""
        if self.solved():
            return self
        index = self.getEmptyElement()
        for guess in range(1, SIZE + 1):
            if self.isLegal(index, guess):
                #temp = self.addElement(index, guess)
                self.grid[index[0]][index[1]] = guess
                self = self.solve()
                if self.solved():
                    return self
                else:
                    self.grid[index[0]][index[1]] = None
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

    sudo = Sudoku(Grid)

    print(sudo)

    solved = sudo.solve()

    print(solved)

if __name__ == '__main__':
    main()
