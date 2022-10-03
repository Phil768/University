//Capturing the main elements in the HTML and storing them in variables.
 const addTask = document.getElementById('add-task');
 const taskContainer = document.getElementById('task-container');
 var inputTask = document.getElementById('input-task');
 const retrieveButton = document.getElementById('retrieve-button');
 const clearButton = document.getElementById('clear-button');
 var currentTasks = "";
 var tasksArray = [];



 function createTask(value)
 {
    var task = document.createElement('div');
    task.classList.add('task');

    var li = document.createElement('li');
    li.innerText = `${value}`;
    task.appendChild(li);

    var checkButton = document.createElement("button");
    checkButton.innerHTML = '<i>O</i>';
    checkButton.classList.add('checkTask');
    task.appendChild(checkButton);

    var deleteButton = document.createElement("button");
    deleteButton.innerHTML = '<i>X</i>';
    deleteButton.classList.add('deleteTask');
    task.appendChild(deleteButton);

    if(value === "")
    {
        alert('Please enter a task.')
    }
    else
    {
        taskContainer.appendChild(task);
    }

    chrome.storage.sync.get([currentTasks], function(result) 
    {
        tasksArray = result[currentTasks] ? result[currentTasks]: [];

        tasksArray.unshift(value);

        var jsonObj = {};
        jsonObj[currentTasks] = tasksArray;
        chrome.storage.sync.set(jsonObj, function() 
        {
            console.log("Saved a new array item");
        });
    });

    checkButton.addEventListener('click', function() 
    {
        checkButton.parentElement.style.textDecoration = "line-through";
    });

    deleteButton.addEventListener('click', function(e) 
    {
        let target = e.target;
        //The parent of the parent is the div (div ==> li ==> button)
        target.parentElement.remove();
    });
}

(() => 
{   
    chrome.storage.sync.get({'list':currentTasks}, (data) => {
        console.log(data.list);
    });
    console.log("Here");
    tasksArray.forEach(element => createTask(element));
    console.log(tasksArray);
})();

//Event listener for adding buttons.
addTask.addEventListener('click', () => {
    createTask(inputTask.value)
    inputTask.value = "";
});

retrieveButton.addEventListener('click', (e) => 
{
    if(tasksArray.length === 0)
    {
        alert("No tasks to retrieve.")
    }
    else
    {
        tasksArray.forEach(element => createTask(element));
    }
    console.log(tasksArray);
    e.target.style.display = "none";
});

clearButton.addEventListener('click', (e) =>
{
    if(chrome.storage.sync.clear())
    {
        alert("All previous tasks have been deleted")
    }
    else
    {
        alert("Something went wrong")
    }
    tasksArray.forEach(element => createTask(element.parentElement.parentElement.remove));
});

