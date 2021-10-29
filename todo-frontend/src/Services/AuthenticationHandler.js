import axios from 'axios';

const AuthenticationHandler = {
  baseUrl: 'http://localhost:8080/user/',
  registerAccount: function (username, password) {
    return new Promise((resolve, reject) => {
      axios.post(this.baseUrl + 'register', {
        username: username,
        password: password
      }).then(response => {
        resolve(response.data);
      }).catch(error => {
        if (error.response) return reject(error.response);
        else if (error.request) return reject(`An unspecified network error occured.`);
        else return reject(error.message);
      });
    });
  },
  loginAccount: function (username, password) {
    return new Promise((resolve, reject) => {
      axios.post(this.baseUrl + 'login', {
        username,
        password
      }).then(response => {
        resolve(response.data);
      }).catch(error => {
        if (error.response) return reject(error.response);
        else if (error.request) return reject(`An unspecified network error occured.`);
        else return reject(error.message);
      });
    });
  }
}

export default AuthenticationHandler;