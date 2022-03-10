import "./App.css";
import PageWrapper from "./components/PageWrapper";
import {
  getFoldersInFolder,
  getTodosInFolder,
  getFoldersInMainFolder,
  getTodosInMainFolder,
} from "./client";
import { useState, useEffect } from "react";
import ListItems from "./components/listItems";

function App() {
  const [idFolderActual, setIdFolderActual] = useState(0);

  const [listTodos, setListTodos] = useState([]);
  const [listFolders, setListFolders] = useState([]);

  const fetchFolders = async () => {
    let response;
    if (idFolderActual === 0) {
      response = await getFoldersInMainFolder();
    } else {
      response = await getFoldersInFolder(idFolderActual);
    }
    let json = await response.json();
    setListFolders(json);
  };

  const fetchTodos = async () => {
    let response;
    if (idFolderActual === 0) {
      response = await getTodosInMainFolder();
    } else {
      response = await getTodosInFolder(idFolderActual);
    }
    let json = await response.json();
    setListTodos(json);
  };

  useEffect(() => {
    fetchFolders();
    fetchTodos();
  }, [idFolderActual]);

  return (
    <PageWrapper>
      <ListItems folders={listFolders} todos={listTodos}></ListItems>
    </PageWrapper>
  );
}

export default App;
