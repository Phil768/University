//Variables.
const selectedWord = document.getElementById("selectedWord");
const selectedWordDefinition = document.getElementById(
  "selectedWordDefinition"
);
const selectedWordExample = document.getElementById("selectedWordExample");
var word = "";

//Retrieveing the selected word from the content script which is saved in storage.
chrome.storage.local.get("word", async function (data) {
  if (typeof data.word == "undefined") {
    console.log("ERROR");
  } else {
    word = data.word;
    selectedWord.innerHTML = word;

    //Dictionary API with the selected word as a variable.
    console.log("HERE: " + word);
    let url = `https://api.dictionaryapi.dev/api/v2/entries/en/${word}`;

    try {
      //Fetching the url.
      const res = await fetch(url);
      //Storing the JSON into a variable.
      const definition = await res.json();
      //Extracting the 'example' property from the JSON file.
      const wordExample = definition[0].meanings[0].definitions[0].example;
      console.log(wordExample);
      //Extracting the 'definition' property of the JSON file.
      const wordDefinition =
        definition[0].meanings[0].definitions[0].definition;
      console.log("DEF" + wordDefinition);
      //Setting the inner text of the HTML divs.
      selectedWordDefinition.innerText = wordDefinition;
      if (wordExample === undefined) {
        selectedWordExample.innerText =
          "No example with suitable context found.";
      } else {
        selectedWordExample.innerText = wordExample;
      }
    } catch (error) {
      console.error(error);
    }
  }
});
