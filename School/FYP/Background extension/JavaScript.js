//Variables for input field and button.
const inputField = document.getElementById('userInput');
const button = document.getElementById('submit');

button.addEventListener('click', function()
{
    if(inputField.value == "")
    {
        alert('Please enter an image link');
    }
    else
    {
        console.log(inputField.value);
        //Storing the input of the user in a variable.
        document.querySelector('body').style.background = "url("+inputField.value+")";
    }
});