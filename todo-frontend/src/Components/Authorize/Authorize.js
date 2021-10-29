import React, { useState, useEffect } from 'react';
import AuthenticationHandler from '../../Services/AuthenticationHandler';
import './Authorize.css'

const Authorize = props => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isRegisterring, setIsRegisterring] = useState(false);
  const [isProcessing, setIsProcessing] = useState(false);
  const [error, setError] = useState(null);

  const registerClickHandler = () => {
    setIsRegisterring(!isRegisterring);
  }

  const submitClickHandler = (event) => {
    event.preventDefault();
    setIsProcessing(true);
    if (isRegisterring) AuthenticationHandler.registerAccount(username, password).then(res => {
      if (res.error) {
        setError(res.error);
        setTimeout(() => {
          setError(null);
        }, 3000);
      } else {
        alert(res.username + ' is now registered!');
      }
    }).catch(err => {
      setError(err);
      setTimeout(() => {
        setError(null);
      }, 4000);
    }).finally(() => {
      setIsProcessing(false);
    });

    else AuthenticationHandler.loginAccount(username, password).then(res => {
      if (res.error) {
        setError(res.error);
        setTimeout(() => {
          setError(null);
        }, 3000);
      } else {
        props.setUser({
          username: res.username,
          createdAt: res.createdAt,
          updatedAt: res.updatedAt,
          id: res.id
        })
      }
    }).catch(err => {
      setError(err);
      setTimeout(() => {
        setError(null);
      }, 4000);
    }).finally(() => {
      setIsProcessing(false);
    });
  }

  return (
    <div className={isProcessing ? 'authorize processing' : 'authorize'}>
      <h1><i className="gg-template"></i> To-Done</h1>
      <div className={error ? 'authorize-form shake' : 'authorize-form'} >
        <h1>
          {
            isRegisterring ? 'Register' : 'Welcome'
          }
        </h1>
        <span>
          {isRegisterring ? 
            "Enter your credentials to create your account" :
            "Enter your credentials to continue to your account"}
        </span>
        <input
          type="text"
          placeholder="Username"
          maxLength="25"
          onKeyUp={e => setUsername(e.target.value)} />
        <input
          type="password"
          placeholder="Password"
          maxLength="25"
          onKeyUp={e => setPassword(e.target.value)} />
        <div className="authorize-form-buttons">
          <a href="#" className="primary" onClick={submitClickHandler}>
            {
              isRegisterring ? 'Confirm' : 'Sign In'
            }
          </a>
          <a href="#" onClick={registerClickHandler}>
            {
              isRegisterring ? 'Back To Login' : 'Create Account'
            }
          </a>
        </div>
        <span className="authorize-form-error">{error ? error : ''}</span>
      </div>
    </div>
  )
}

export default Authorize;