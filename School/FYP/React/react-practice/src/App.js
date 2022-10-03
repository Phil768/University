/*global chrome*/
import React, { useEffect, useState } from "react";
import "./App.css";

function App() {
  //Creating a new state.
  let [input, setInput] = useState("");
  var num = 0;
  //Funtion that will execute when input is changed.
  function Search(e) {
    //Storing the input by the user into the state.
    input = e.target.value;
    console.log(input);
    //Sending messages to the contentScript.
    chrome.tabs.query({ active: true, currentWindow: true }, function (tabs) {
      chrome.tabs.sendMessage(tabs[0].id, { from: "App", subject: input });
    });

    chrome.runtime.onMessage.addListener((msg, sender, response) => {
      if (msg.from === "Content") {
        console.log("MESSAGE FROM BACKGROUND:" + msg.from);
        // const content = document.getElementById("content");
        // content.innerHTML = `'${input}' is present in ${msg.num} paragraphs.`;
      }
    });

    useEffect = () => {
      const search = document.getElementById("search");
      const content = document.getElementById("content");
      setInput(() => {
        return search.value;
      });

      content.innerHTML = setInput();
    };
  }

  return (
    <body className="App-body">
      <div className="container">
        <div className="input">
          Enter word to search:
          <input
            type="text"
            id="search"
            placeholder="Example 'Hello'"
            onChange={Search}
          ></input>
        </div>
        <div className="content" id="content"></div>
      </div>
    </body>
  );
}

export default App;
