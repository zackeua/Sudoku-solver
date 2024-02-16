from pyscript import document
from Sudoku import Sudoku
from itertools import product
from pyodide.ffi import create_proxy
from functools import partial

EVENT_LISTENERS = {}

def add_event_listener(elt, event: str, listener) -> None:
    """Wrapper for JavaScript's addEventListener() which automatically manages the lifetime
    of a JsProxy corresponding to the listener param.
    """
    proxy = create_proxy(listener)
    EVENT_LISTENERS[(elt.js_id, event, listener)] = proxy
    elt.addEventListener(event, proxy)

def read_sudoku_grid():
    elements = document.getElementsByClassName('element')
    grid = [[None for _ in range(9)] for _ in range(9)]
    for element, (i, j) in zip(elements, product(range(9), range(9))):
        grid[i][j] = int(element.value) if element.value else None

    return grid


def set_solution(grid):
    elements = document.getElementsByClassName('element')
    for element, (i, j) in zip(elements, product(range(9), range(9))):
        element.value = str(grid[i][j])
        element.setAttribute('readonly', True)


def reset_sudoku(event):
    elements = document.getElementsByClassName('element')
    for element in elements:
        element.value = ''
        element.removeAttribute('readonly')
        element.classList.remove('bold')
    disable_button('reset')
    enable_button('solve')


def make_bold():
    elements = document.getElementsByClassName('element')
    for element in elements:
        if element.value:
            element.classList.add('bold')



def disable_button(name):
    button = document.getElementById(name)
    button.classList.add('hidden')


def enable_button(name):
    button = document.getElementById(name)
    button.classList.remove('hidden')


def make_blue(button):
    print('hej')
    button.classList.add('blue')


def remove_blue(button):
    print('haha')

    button.classList.remove('blue')


def setup_eventlisteners():
    elements = document.getElementsByClassName('element')
    for element in elements:
        add_event_listener(element, 'focus', lambda x: partial(make_blue, element))
        add_event_listener(element, 'blur', lambda x: partial(remove_blue, element))
        
def solve_sudoku(event):
    
    make_bold()

    grid = read_sudoku_grid()

    sudoku = Sudoku(grid)

    if sudoku.is_valid():
        sudoku.solve()
    else:
        # TODO: Notify if soduki is not valid
        pass

    print(sudoku._grid)
    set_solution(sudoku._grid)
    
    # disable solve button
    disable_button('solve')
    enable_button('reset')
setup_eventlisteners()