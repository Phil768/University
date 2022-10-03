const fname = document.getElementById("fname");
const lname = document.getElementById("lname");
const red = document.getElementById("red");
const blue = document.getElementById("blue");
const form = document.getElementById("form");

function getFormvalue() {
  if (lname.value === "" || lname.value === "") {
    console.log("Enter name AND surname.");
  } else {
    console.log(fname.value);
    console.log(lname.value);
  }
}

red.addEventListener("click", () => {
  form.style.background = "red";
});

blue.addEventListener("click", () => {
  form.style.background = "blue";
});
