(() => {
    //variables which allow us to access the YouTube controls and player.
    let youtubeLeftControls, youtubePlayer;
    //Blank variable.
    let currentVideo = "";
    let currentVideoBookmarks = [];

    const fetchBookmarks = () => {
        return new Promise((resolve) => {
            chrome.storage.sync.get([currentVideo], (obj) => {
                resolve(obj[currentVideo] ? JSON.parse(obj[currentVideo]): []);
            });
        });
    };

    const addNewBookMarkEventHandler = async() => {
        const currentTime = youtubePlayer.currentTime;
        const newBookmark = {
            time: currentTime,
            desc: "Bookmark at " + getTime(currentTime),
        };

        //Makes sure that we are using the most up to date bookmarks.
        currentVideoBookmarks = await fetchBookmarks();

        chrome.storage.sync.set({
            [currentVideo]: JSON.stringify([...currentVideoBookmarks, newBookmark].sort((a,b) => a.time - b.time))
        });
    };

    const newVideoLoaded = async() => {
        const bookmarkBtnExists = document.getElementsByClassName("bookmark-btn")[0];
        currentVideoBookmarks = await fetchBookmarks();

        if(!bookmarkBtnExists){
            //Creating a new image element.
            const bookmarkBtn = document.createElement("img");
            //Creating the source for the image element.
            bookmarkBtn.src = chrome.runtime.getURL("assets/bookmark.png");
            //Styling.
            bookmarkBtn.className = "ytp-button " + "bookmark-btn";
            bookmarkBtn.title = "Click to bookmark current timestamp";

            //Grabbing the elements needed from the YouTube screen.
            //[Left controls]
            youtubeLeftControls = document.getElementsByClassName("ytp-left-controls")[0];
            //[YouTube player]
            youtubePlayer = document.getElementsByClassName("video-stream")[0];

            //Appending a new element (bookmark button) to the row.
            youtubeLeftControls.appendChild(bookmarkBtn);
            //When the button is clicked.
            bookmarkBtn.addEventListener("click", addNewBookMarkEventHandler);
        }
    };

    //Code to listen to the background.js message.
    chrome.runtime.onMessage.addListener((obj, sender, response) => {
        const { type, value, videoId } = obj;
        
        if(type === "NEW")
        {
            currentVideo = videoId;
            //function.
            newVideoLoaded();
        }
        else if(type === "PLAY")
        {
            youtubePlayer.currentTime = value;
        }
        else if(type === "DELETE")
        {
            currentVideoBookmarks = currentVideoBookmarks.filter((b) => b.time != value);
            chrome.storage.sync.set({ [currentVideo]: JSON.stringify(currentVideoBookmarks)});

            response(currentVideoBookmarks);
        }
    });
    
    //Making sure that the button is always added.
    newVideoLoaded();
})();

const getTime = t => {
    var date = new Date(0);
    date.setSeconds(t);

    return date.toISOString().substr(11, 8);
};

//     (() => {
//   let youtubeLeftControls, youtubePlayer;
//   let currentVideo = "";
//   let currentVideoBookmarks = [];

//   const fetchBookmarks = () => {
//     return new Promise((resolve) => {
//       chrome.storage.sync.get([currentVideo], (obj) => {
//         resolve(obj[currentVideo] ? JSON.parse(obj[currentVideo]) : []);
//       });
//     });
//   };

//   const addNewBookmarkEventHandler = async () => {
//     const currentTime = youtubePlayer.currentTime;
//     const newBookmark = {
//       time: currentTime,
//       desc: "Bookmark at " + getTime(currentTime),
//     };

//     currentVideoBookmarks = await fetchBookmarks();

//     chrome.storage.sync.set({
//       [currentVideo]: JSON.stringify([...currentVideoBookmarks, newBookmark].sort((a, b) => a.time - b.time))
//     });
//   };

//   const newVideoLoaded = async () => {
//     const bookmarkBtnExists = document.getElementsByClassName("bookmark-btn")[0];

//     currentVideoBookmarks = await fetchBookmarks();

//     if (!bookmarkBtnExists) {
//       const bookmarkBtn = document.createElement("img");

//       bookmarkBtn.src = chrome.runtime.getURL("assets/bookmark.png");
//       bookmarkBtn.className = "ytp-button " + "bookmark-btn";
//       bookmarkBtn.title = "Click to bookmark current timestamp";

//       youtubeLeftControls = document.getElementsByClassName("ytp-left-controls")[0];
//       youtubePlayer = document.getElementsByClassName('video-stream')[0];

//       youtubeLeftControls.appendChild(bookmarkBtn);
//       bookmarkBtn.addEventListener("click", addNewBookmarkEventHandler);
//     }
//   };

//   chrome.runtime.onMessage.addListener((obj, sender, response) => {
//     const { type, value, videoId } = obj;

//     if (type === "NEW") {
//       currentVideo = videoId;
//       newVideoLoaded();
//     } else if (type === "PLAY") {
//       youtubePlayer.currentTime = value;
//     } else if ( type === "DELETE") {
//       currentVideoBookmarks = currentVideoBookmarks.filter((b) => b.time != value);
//       chrome.storage.sync.set({ [currentVideo]: JSON.stringify(currentVideoBookmarks) });

//       response(currentVideoBookmarks);
//     }
//   });

//   newVideoLoaded();
// })();

// const getTime = t => {
//   var date = new Date(0);
//   date.setSeconds(t);

//   return date.toISOString().substr(11, 8);
// };