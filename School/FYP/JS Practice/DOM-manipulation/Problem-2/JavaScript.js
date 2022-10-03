const p = document.querySelector("p");

//Splitting the inner text when there is a space in order to access each indivual word.
p.innerHTML = p.innerText
  .split(" ")
  .map((word) =>
    word.length > 8
      ? `<span style = "background-color:lightblue">${word}</span>`
      : word
  )
  .join(" ");

//Overwriting the innerHTML of the current paragraph.
p.innerHTML = p.innerHTML.split(".").join(".<br>");
