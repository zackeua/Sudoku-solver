from pyscript import document
from Sudoku import Sudoku
from itertools import product

def solve_sudoku(event):
    elements = document.getElementsByClassName('element')
    grid = [[None for _ in range(9)] for _ in range(9)]
    for element, (i, j) in zip(elements, product(range(9), range(9))):
        grid[i][j] = int(element.innerText) if element.innerText else None

    sudoku = Sudoku(grid)
    sudoku.solve()
    print(sudoku._grid)

    for element, (i, j) in zip(elements, product(range(9), range(9))):
        element.innerText = str(sudoku._grid[i][j])