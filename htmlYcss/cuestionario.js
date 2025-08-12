
function raizCuadraticaSuma(a, b, c) {
    return (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
}


function raizCuadraticaResta(a, b, c) {
    return (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
}


function cambiarTextoPorId(id, texto) {
    document.getElementById(id).innerHTML = texto;
}


function numeroAleatorioEnId(id) {
    let numero = Math.floor(Math.random() * 100) + 1; 
    document.getElementById(id).innerHTML = numero;
}
