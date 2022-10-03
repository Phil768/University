import { getActiveTabURL } from "./utils.js";

//Adding a new bookmark row to the popup.
const addNewBookmark = (bookmarksElement, bookmark) => {
    //Bookmark Title element.
    const bookmarkTitleElement = document.createElement("div");
    //Bookmark elemnt which will contain buttons and overall UI.
    const newBookmarkElement = document.createElement("div");
    const controlsElement = document.createElement("div");

    bookmarkTitleElement.textContent = bookmark.desc;
    bookmarkTitleElement.className = "bookmark-title";

    controlsElement.className = "bookmark-controls";

    //Creating a specific ID for each bookmark created.
    newBookmarkElement.id = "bookmark-" + bookmark.time;
    newBookmarkElement.className = "bookmark";
    newBookmarkElement.setAttribute("timestamp", bookmark.time);

    setBookmarkAttributes("play", onPlay, controlsElement);
    setBookmarkAttributes("delete", onDelete, controlsElement);

    newBookmarkElement.appendChild(bookmarkTitleElement);
    newBookmarkElement.appendChild(controlsElement);
    bookmarksElement.appendChild(newBookmarkElement);
};

const viewBookmarks = (currentBookmarks = []) => {
    const bookmarksElement = document.getElementById("bookmarks");
    //Setting all the bookmarks to null (resetting the space in order to build up).
    bookmarksElement.innerHTML = "";

    //If there exists some bookmarks.
    if(currentBookmarks.length > 0)
    {
       for(let i = 0; i < currentBookmarks.length; i++)
       {
          const bookmark = currentBookmarks[i];
          addNewBookmark(bookmarksElement, bookmark);
       } 
    }
    else
    {
        bookmarksElement.innerHTML = '<i class="row">No bookmarks to show.</i>';
    }
    return;
};

const onPlay = async e => {
    const bookmarkTime = e.target.parentNode.parentNode.getAttribute("timestamp");
    const activeTab = await getActiveTabURL();

    chrome.tabs.sendMessage(activeTab.id, {
        type: "PLAY",
        value: bookmarkTime,
    });
};

const onDelete = async e => {
    const activeTab = await getActiveTabURL();
    const bookmarkTime = e.target.parentNode.parentNode.getAttribute("timestamp"); 
    const bookmarkElementToDelete = document.getElementById("bookmark-" + bookmarkTime);
    bookmarkElementToDelete.parentNode.removeChild(bookmarkElementToDelete);

    chrome.tabs.sendMessage(activeTab.id, {
        type: "DELETE",
        value: bookmarkTime,
    }, viewBookmarks);
};

const setBookmarkAttributes =  (src, eventListener, controlParentElement) => {
    const controlElement = document.createElement("img");
    controlElement.src = "assets/" + src + ".png";
    controlElement.title = src;

    //Waiting for a button click.
    controlElement.addEventListener("click", eventListener);
    controlParentElement.appendChild(controlElement);
};

document.addEventListener("DOMContentLoaded", async() => {
    //Look at current user's current tab.
    const activeTab = await getActiveTabURL();
    //save query parameters to help us identify the video.
    const queryParameters = activeTab.url.split("?")[1];
    //URL search paramters.
    const urlParameters = new URLSearchParams(queryParameters);

    //Getting the unique identifier.
    const currentVideo = urlParameters.get("v");

    //Checking that we are watching a YouTube video.
    if(activeTab.url.includes("youtube.com/watch") && currentVideo)
    {
       chrome.storage.sync.get([currentVideo], (data) => {
        //If there are no bookmarks for the current video, an empty array ('[]') is returned.
        const currentVideoBookmarks = data[currentVideo] ? JSON.parse(data[currentVideo]): [];
        //Getting all the bookmarks associated with the video, using an already created function.
        viewBookmarks(currentVideoBookmarks);
       });
    }
    else
    {
        const container = document.getElementsByClassName("container")[0];
        container.innerHTML = '<div class="title">This is not a YouTube video.</div>';
    }
});


