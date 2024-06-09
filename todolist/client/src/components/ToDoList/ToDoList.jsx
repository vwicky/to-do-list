import { useState, useEffect } from "react";
import styles from './ToDoList.module.css'

function ToDoList() {

  const [items, setItems] = useState([]);

  useEffect(() => {
    fetchTasks();
  }, [])

  const fetchTasks = async () => {
    try {
      const response = await fetch("http://localhost:5678/api/v1/card/all");
      const data = await response.json();
      setItems(data);
    } catch (e) {
      console.log(e);
    }
  };

  async function uploadItem(item) {
    try {
      const response = await fetch("http://localhost:5678/api/v1/card/add", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
      });
      const result = await response.json();
      console.log(`Result succesfull ${result}`);
      // after being uploaded - refresh the items
      fetchTasks();
    }
    catch (error) {
      console.error(error)
    }
  }
  async function deleteItem(itemId) {
    try {
      await fetch(`http://localhost:5678/api/v1/card/delete/${itemId}`, {
        method: "DELETE",
        headers: {
          'Content-Type': 'application/json'
        },
      }).then(() => console.log(`Result : DELETED succesfull`));
      
      // after being uploaded - refresh the items
      fetchTasks();
    }
    catch (error) {
      console.error(error)
    }
  }

  function handleAddingNewTask() {
    const inputField = document.getElementById("inputText");
    if (!inputField.value || inputField.value.length <= 0) {
      return;
    }
    const newTask = {
      content: inputField.value,
      cardUrgency: "MEDIUM",
    };
    inputField.value = ""
    uploadItem(newTask);
  }

  function handleRemoveTask(itemId) {
    deleteItem(itemId);
  }

  return (
    <div className={styles.mainBody}>
      <h1>ToDo - List</h1>
      <div className={styles.inputHolder}>
        <div className={styles.inputHolderIn}>
          <input type="text" id="inputText" placeholder="Enter a new task" />
          <button onClick={handleAddingNewTask}>+</button>
        </div>
      </div>    
      <div className={styles.contentHolder}>
        {
          items.map(item => 
            <div key={item.id} className={styles.listItemElement}>
              <h3 className={styles.itemTitle}>{item.creationDate}</h3>
              <p>{item.content}</p>
              <hr />
              <p>urgency: {item.cardUrgency}</p>
              <button onClick={() => handleRemoveTask(item.id)}>Remove Task</button>
            </div>
          )
        }
      </div>
    </div>
  )
}

export default ToDoList;