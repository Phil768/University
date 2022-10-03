window.addEventListener("mouseup", () => {
  console.log("Selected word");
  var selectedText = window.getSelection().toString().trim();
  console.log(selectedText);
  var message = "";

  if (selectedText.length > 0) {
    //Creating the message object.
    message = {
      text: selectedText,
    };
  }
  chrome.storage.local.set({ word: selectedText });
});
