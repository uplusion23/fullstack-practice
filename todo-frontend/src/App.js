import React, { useState } from 'react';
import Authorize from './Components/Authorize/Authorize';
import Items from './Components/Items/Items';
import './App.css';

const App = () => {
  const [user, setUser] = useState(null);

  const logout = () => {
    setUser(null);
  }

  return (
    <div className="App">
      {
        user ? 
          <Items user={user} logout={logout} /> :
          <Authorize setUser={setUser} />
      }
    </div>
  );
}

export default App;
