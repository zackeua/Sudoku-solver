function isNumeric(str) {
    return !isNaN(parseFloat(str));
  }

function updateText(element) {
    var value = element.innerText;
    if (value.length > 1) {
        element.innerText = element.innerText[0];
    }
    value = element.innerText;
    if (!isNumeric(value) || (value < 1 || value > 9)) {
        element.innerText = '';
    }

}

function generateTableRow() {
    const tr = document.createElement('tr');
    for (let index = 0; index < 9; index++) {
        const td = document.createElement('td');
        const element = document.createElement('div');
        element.setAttribute('contenteditable', true)
        element.classList.add('element');
        element.addEventListener('blur' , () => {  
            updateText(element);
        })

        td.appendChild(element);
        tr.appendChild(td);
    }
    return tr;
}

function generateTable() {
    
    const table = document.getElementsByTagName('table')[0];
    for (let index = 0; index < 9; index++) {
        const tr = generateTableRow();
        table.appendChild(tr);
    }
}

function makeBold() {
    const elements = document.getElementsByClassName('element');

    for (var i = 0; i < elements.length; i++) {
        const element = elements[i];
        if (element.innerText) {
            element.classList.add('bold');
            element.setAttribute('contenteditable', false);
        }

    }
}

document.addEventListener("DOMContentLoaded", () => {


    generateTable();

    const solveButton = document.getElementById('solve');
    
    solveButton.addEventListener('click', () => {
        makeBold();
    })

})

