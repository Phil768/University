(() => {
  const paragraph1 = document.getElementById("paragraph1");
  const paragraph2 = document.getElementById("paragraph2");

  paragraph1.innerHTML = paragraph1.innerHTML + " " + paragraph2.innerHTML;
  paragraph2.remove();
})();
