//Wrapping or content in a DOMcontentloaded function.
document.addEventListener("DOMContentLoaded", async() => 
{
    //storing the required elements into JS variables.
    const linksList = document.getElementById('linksList');
    //URL of the API.
    const url = 'https://api.chucknorris.io/jokes/random';

    const copy = (e) => 
    {
        const str = e.target.dataset.url;
        const el = document.createElement('textarea');
        el.value = str;
        document.body.appendChild(el);
        el.select();
        document.execCommand('copy');
        document.removeChild(el);
    }

    //Try/catch block when attempting to connect to the API.
    try
    {
        //Fetching the url.
        const res = await fetch(url);
        //Storing the JSON into a variable.
        const jokes = await res.json();
        //Extracting the 'value' property of the JSON file.
        const jokeValue = jokes.value;
        //Setting the HTML of the <ol></ol>.
        linksList.innerHTML = `<div class="joke-value">${jokeValue}
                                    <div>
                                        <button class="btn" data-url="${jokeValue}">Copy</button>
                                    </div>
                               </div> `;

        const jokeLinks = document.querySelector('.joke-value');
        jokeLinks.addEventListener('click', copy);
    }
    catch(err)
    {
        console.error(err);
    }
});