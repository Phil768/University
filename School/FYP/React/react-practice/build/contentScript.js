chrome.runtime.onMessage.addListener((msg, sender, response) => {
  if (msg.from === "App") {
    var paragraphs = [...document.querySelectorAll("p")];
    var selected = [];
    console.log("INPUT :" + msg.subject);

    paragraphs.forEach((par) => {
      if (
        par.innerHTML.toLowerCase().includes(msg.subject.toLowerCase()) &&
        msg.subject.toString() !== " "
      ) {
        par.style["background-color"] = "red";
        selected.push(par);
        console.log("NUM: " + selected.length);
        chrome.runtime.sendMessage({
          from: "Content",
          input: msg.subject,
          num: selected.length,
        });
      } else {
        par.style["background-color"] = "transparent";
      }
    });
  }
});
