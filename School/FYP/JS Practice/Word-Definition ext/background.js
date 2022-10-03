console.log("Background loaded");
var word = "Test background";

chrome.runtime.onMessage.addListener((request, sender, sendResponse) => {
  if (request.data === "ready") {
    console.log(request.data);
    chrome.runtime.sendMessage(request.data);
  }
});
