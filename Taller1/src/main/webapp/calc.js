function imprimir(n) {
    var pantalla = document.getElementById('pantalla');
    var actual = pantalla.textContent;
    if (actual == 0) {
        pantalla.innerHTML = n;
    } else if (actual.length <= 15) {
        pantalla.innerHTML = actual.toString() + n.toString();
    }
};

function solve(){
    var pantalla = document.getElementById('pantalla');
    pantalla.innerHTML = eval(pantalla.textContent.replace(/x/g , "*"));
}
