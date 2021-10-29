import React, { useState } from 'react';
import './ItemsNavigation.css';

const ItemsNavigation = props => {
  const [createNewActive, setCreateNewActive] = React.useState(false);
  const [createToDoTitle, setCreateToDoTitle] = React.useState('');
  const [createToDoContent, setCreateToDoContent] = React.useState('');

  const handleCreateNew = () => {
    setCreateNewActive(!createNewActive);
  };

  const handleCreateToDoTitle = e => {
    setCreateToDoTitle(e.target.value);
  }

  const handleCreateToDoContent = e => {
    setCreateToDoContent(e.target.value);
  }

  const handleCreateToDo = e => {
    e.preventDefault();
    props.submitNewTodo({
      title: createToDoTitle,
      content: createToDoContent
    });
    handleClearCreateToDo(e);
    setCreateNewActive(false);
  }

  const handleClearCreateToDo = e => {
    e.preventDefault();
    setCreateToDoTitle('');
    setCreateToDoContent('');
  }

  const handleLogout = e => {
    e.preventDefault();
    props.logout();
  }

  return (
    <div className="items-navigation">
      {
        createNewActive ? <div className="hide" onClick={() => setCreateNewActive(false)}></div> : null
      }
      <span className="logo"><i className="gg-template" /></span>
      <span className="username">{props.user.username}</span>
      <a
        href="#"
        className="create-new"
        onClick={handleCreateNew} >New To-Do</a>
      <a
        href="#"
        className="logout"
        onClick={handleLogout}>Logout</a>
      <div className={createNewActive ? "todo-creator active" : "todo-creator"}>
        <h1>Create A New To-Do</h1>
        <div class="group">
          <span><i className="gg-quote" /></span>
          <input
            type="text"
            placeholder="Title"
            value={createToDoTitle}
            onChange={handleCreateToDoTitle} />
        </div>
        <div class="group tall">
          <span><i className="gg-comment" /></span>
          <textarea
            placeholder="Description"
            value={createToDoContent}
            onChange={handleCreateToDoContent}/>
        </div>
        <div className="todo-creator-buttons">
          <a
            href="#"
            className="primary"
            onClick={handleCreateToDo}>Create</a>
          <a
            href="#"
            onClick={handleClearCreateToDo}>Clear Form</a>
        </div>
      </div>
    </div>
  )
}
export default ItemsNavigation;