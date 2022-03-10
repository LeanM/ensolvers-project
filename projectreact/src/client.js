const server_path = "http://localhost:8080/api/todolist/";

const checkStatus = (response) => {
  if (response.ok) {
    return response;
  } else {
    let error = new Error(response.statusText);
    error.response = response;
    response.json().then((e) => {
      error.error = e;
    });
    return Promise.reject(error);
  }
};

export const getTodosInMainFolder = () => {
  return fetch(server_path + "todo/", {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    method: "GET",
  }).then(checkStatus);
};

export const getFoldersInMainFolder = () => {
  return fetch(server_path + "folder/", {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    method: "GET",
  }).then(checkStatus);
};

export const getTodosInFolder = (idFolder) => {
  return fetch(server_path + "todo/" + idFolder, {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    method: "GET",
  }).then(checkStatus);
};

export const getFoldersInFolder = (idFolder) => {
  return fetch(server_path + "folder/" + idFolder, {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    method: "GET",
  }).then(checkStatus);
};

export const addNewTodo = (todoName, idParentFolder) => {
  return fetch(server_path + "todo" + "?idParentFolder=" + idParentFolder, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({ name: todoName, checked: false }),
  }).then(checkStatus);
};

export const addNewFolder = (folderName, idParentFolder) => {
  return fetch(server_path + "folder" + "?idParentFolder=" + idParentFolder, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({ name: folderName }),
  }).then(checkStatus);
};

export const updateTodo = (idTodo, todoName, isChecked) => {
  return fetch(server_path + "todo/update/" + idTodo, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify({ name: todoName, checked: isChecked }),
  }).then(checkStatus);
};

export const updateFolder = (idFolder, folderName) => {
  return fetch(server_path + "folder/update/" + idFolder, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify({ name: folderName }),
  }).then(checkStatus);
};

export const deleteTodo = (idTodo) => {
  return fetch(server_path + "todo/delete/" + idTodo, {
    method: "DELETE",
  }).then(checkStatus);
};

export const deleteFolder = (idFolder) => {
  return fetch(server_path + "folder/delete/" + idFolder, {
    method: "DELETE",
  }).then(checkStatus);
};
