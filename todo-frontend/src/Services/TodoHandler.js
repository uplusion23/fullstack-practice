import axios from 'axios';

const TodoHandler = {
  baseUrl: 'http://localhost:8080/api/items',
  getUserItems: function (userId) {
    return new Promise((resolve, reject) => {
      axios.get(this.baseUrl + `?userId=${userId}`).then(response => {
        resolve(response.data);
      }).catch(error => {
        if (error.response) return reject(error.response);
        else if (error.request) return reject(`An unspecified network error occured.`);
        else return reject(error.message);
      });
    });
  }
}

export default TodoHandler;