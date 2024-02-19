function isNumeric(str) {
    return !isNaN(parseFloat(str));
  }

function updateText(element) {
    var value = element.value;
    if (value.length > 1) {
        element.value = element.value[0];
    }
    value = element.value;
    if (!isNumeric(value) || (value < 1 || value > 9)) {
        element.value = '';
    }

}


function makeBlue(element) {
    element.classList.add('blue');
}

function removeBlue(element) {
    element.classList.remove('blue');
}

function generateTableRow() {
    const tr = document.createElement('tr');
    for (let index = 0; index < 9; index++) {
        const td = document.createElement('td');
        const element = document.createElement('input');
        element.classList.add('element');
        element.setAttribute('type', 'number');
        element.setAttribute('min', '1');
        element.setAttribute('max', '9');


        //element.setAttribute('maxlength', '1');
        //element.setAttribute('pattern', '[1-9]');
        element.addEventListener('blur' , () => {  
            updateText(element);
            removeBlue(element);
        })

        element.addEventListener('focus', () => {
            makeBlue(element);
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


document.addEventListener("DOMContentLoaded", () => {


    generateTable();

})

