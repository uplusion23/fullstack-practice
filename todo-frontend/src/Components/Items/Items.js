import React, { useState, useEffect } from 'react';
import TodoHandler from '../../Services/TodoHandler';
import ItemsNavigation from './ItemsNavigation/ItemsNavigation';
import './Items.css';

const Items = props => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    console.log(props)
    TodoHandler.getUserItems(props.user.id).then(items => {
      setItems(items);
    });
  }, []);

  return (
    <div className="items">
      <ItemsNavigation user={props.user} />
      <h1><i class="gg-list"></i>To-Do List</h1>
      <div className="items-list">
        {
          items.map(item => (
            <div className="todo-item" data-completed={item.completed}>
              <h1>{item.title}</h1>
              <p>{item.content}</p>
              <span>{item.completed.toString()}</span>
            </div>
          ))
        }
      </div>
    </div>
  )
}

export default Items;