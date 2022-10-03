let element = document.querySelectorAll("div");
let div = document.getElementById("div3");

let rect1 = element[0].getBoundingClientRect();
let rect2 = element[1].getBoundingClientRect();

console.log("Hello Div: ");
console.log("x: " + rect1.x);
console.log("y: " + rect1.y);

console.log("GoodBye Div: ");
console.log("x: " + rect2.x);
console.log("y: " + rect2.y);

console.log("-----------------------");

console.log(div.offsetLeft);
console.log(div.offsetHeight);
console.log(div.offsetParent);
console.log(div.offsetTop);
console.log(div.scrollTop);
console.log(div.scrollLeft);
