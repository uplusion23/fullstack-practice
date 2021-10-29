import React, { useState, useEffect } from 'react';
import TodoHandler from '../../Services/TodoHandler';
import ItemsNavigation from './ItemsNavigation/ItemsNavigation';
import './Items.css';

const Items = props => {
  const [items, setItems] = useState([]);

  const getItems = () => {
    TodoHandler.getUserItems(props.user.id).then(items => {
      console.log(items);
      setItems(items);
    });
  }

  const submitNewTodo = (item) => {
    TodoHandler.submitItem(item, props.user.id).then(response => {
      // handle response
      getItems();
    })
  }

  const changeItemStatus = (item) => {
    TodoHandler.changeItemStatus(item, props.user.id).then(response => {
      // handle response
      getItems();
    })
  }

  const deleteItem = (itemId) => {
    console.log(itemId);
    TodoHandler.deleteItem(itemId, props.user.id).then(response => {
      // handle response
      getItems();
    })
  }

  useEffect(() => {
    getItems();
  }, []);

  return (
    <div className="items">
      <ItemsNavigation logout={props.logout} user={props.user} submitNewTodo={submitNewTodo} />
      <h1><i class="gg-list"></i>To-Do List</h1>
      <div className="items-list">
        {
          items.map(item => (
            <div className="todo-item" data-completed={item.completed}>
              <h1>{item.title}</h1>
              <p>{item.content}</p>
              <a
                href="#"
                onClick={() => changeItemStatus({
                  ...item,
                  completed: !item.completed
                })}>
                  {item.completed ? 'Mark Unfinished' : 'Mark Completed'}
                </a>
              <a
                href="#"
                className="red"
                onClick={() => deleteItem(item.id)}>
                  Delete To-Do
                </a>
            </div>
          ))
        }
      </div>
    </div>
  )
}

export default Items;