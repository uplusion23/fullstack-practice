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
  },
  submitItem: function (item, userId) {
    return new Promise((resolve, reject) => {
      axios.post(this.baseUrl, {
        userId: userId,
        title: item.title,
        content: item.content
      }).then(response => {
        resolve(response.data);
      }).catch(error => {
        if (error.response) return reject(error.response);
        else if (error.request) return reject(`An unspecified network error occured.`);
        else return reject(error.message);
      });
    });
  },
  changeItemStatus: function (item, userId) {
    return new Promise((resolve, reject) => {
      axios.patch(this.baseUrl + `/${item.id}`, {
        userId: userId,
        title: item.title,
        content: item.content,
        completed: item.completed
      }).then(response => {
        resolve(response.data);
      }).catch(error => {
        if (error.response) return reject(error.response);
        else if (error.request) return reject(`An unspecified network error occured.`);
        else return reject(error.message);
      });
    });
  },
  deleteItem: function (itemId, userId) {
    return new Promise((resolve, reject) => {
      axios.delete(this.baseUrl + `/${itemId}`, {
        userId: userId
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

export default TodoHandler;