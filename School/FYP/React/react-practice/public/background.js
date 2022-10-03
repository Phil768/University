chrome.runtime.onInstalled.addListener(() => {
  console.log("Loaded");
});

chrome.runtime.onMessage.addListener((msg, sender, response) => {
  if (msg.from === "Content") {
    console.log("MESSAGE:" + msg.input);
    chrome.runtime.sendMessage(msg);
  }
});
