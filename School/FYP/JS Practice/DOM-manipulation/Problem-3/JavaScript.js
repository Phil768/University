//Storing the HTML elements into variables.
const monthlyRadio = document.getElementById("monthly");
const yearlyRadio = document.getElementById("annual");
const monthlyContainer = document.getElementById("month");
const yearlyContainer = document.getElementById("year");

const options = document.getElementById("options");
//Storing button elements into variables.
const sMonthly = document.getElementById("sMonthly");
const pMonthly = document.getElementById("pMonthly");
const sYearly = document.getElementById("sYearly");
const pYearly = document.getElementById("pYearly");

//Global variable for a new element which needs to be overwritten.
var newDiv = document.createElement("div");

//Default choice when entering the site.
(() => {
  monthlyRadio.checked = true;
  yearlyContainer.style.display = "none";
})();

monthlyRadio.addEventListener("click", () => {
  newDiv.innerHTML = `<h2></h2>`;
  yearlyContainer.style.display = "none";
  monthlyContainer.style.display = "flex";
});

yearlyRadio.addEventListener("click", () => {
  newDiv.innerHTML = `<h2></h2>`;
  monthlyContainer.style.display = "none";
  yearlyContainer.style.display = "flex";
});

function choice(value) {
  newDiv.className = "choice";
  newDiv.innerHTML = `<h2>${value}</h2>`;
  options.appendChild(newDiv);
}

sMonthly.addEventListener("click", () => {
  newDiv.innerHTML = `<h2></h2>`;
  choice("You Have chosen a montly, Standard plan.");
});

pMonthly.addEventListener("click", () => {
  newDiv.innerHTML = `<h2></h2>`;
  choice("You Have chosen a monthly, Pro plan.");
});

sYearly.addEventListener("click", () => {
  newDiv.innerHTML = `<h2></h2>`;
  choice("You Have chosen a yearly, Standard plan.");
});

pYearly.addEventListener("click", () => {
  newDiv.innerHTML = `<h2></h2>`;
  choice("You Have chosen a yearly, Pro plan.");
});
