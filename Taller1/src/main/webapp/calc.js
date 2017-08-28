var pantalla;
window.onload = function () {
   pantalla = document.getElementById('pantalla');
}

function writeNumber(n){
    pantalla.innerHTML=pantalla.textContent==0?n:pantalla.textContent+n
};
function writeOperator(n){
    var text = pantalla.textContent;
    var lastChar = text.slice(-1).toString();
    switch (n) {
        case '-':pantalla.innerHTML=(lastChar.match(/[\*\+]/g) || $.isNumeric(lastChar))?text+n:text;break;
        default:pantalla.innerHTML=$.isNumeric(lastChar)?text+n:text;break;
    }
};

function comma(){
    var text = pantalla.textContent;
    var numbers = text.split(/[+*\-/]/);
    if (!numbers[numbers.length-1].includes('.') && numbers[numbers.length-1]!="") {
        pantalla.innerHTML=text+'.';
    }
};

function moreLess(){
    var result = eval(pantalla.textContent).toString();
    console.log(result);
    pantalla.innerHTML=result.includes('-')?result.replace(/\-/g,""):'-'+result;
};

function squareRoot(){
    var result = eval(pantalla.textContent).toString();
    if (!result.includes('-')) {
        pantalla.innerHTML=Math.sqrt(result);
    };
};

function square(){
    pantalla.innerHTML=Math.pow(eval(pantalla.textContent),2);
};

function reciprocal(){
    pantalla.innerHTML=1/eval(pantalla.textContent);
};

function percentage(){
    pantalla.innerHTML=eval(pantalla.textContent)/100;
}

function BorrarTodo(){
    pantalla.innerHTML="0";
};
function Borrar(){
    var text = pantalla.textContent;
    pantalla.innerHTML=text.length>1?text.slice(0,-1):'0';
};

function solve(){
    pantalla.innerHTML = eval(pantalla.textContent);
};


