table = document.getElementsByTagName("tbody")[0].children;
let max = 10;
let min = -10;
for (let i = 1; i<table.length; i++){
    table[i].children[0].children[0].value = (Math.random() * (max - min) + min).toFixed(2);
    table[i].children[1].children[0].value = (Math.random() * (max - min) + min).toFixed(2);
}