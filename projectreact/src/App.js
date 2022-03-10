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
  const [folderDirectorio, setFolderDirectorio] = useState([[1, "MainFolder"]]);
  const [idParentFolderActual, setIdParentFolderActual] = useState(0);

  const [listTodos, setListTodos] = useState([]);
  const [listFolders, setListFolders] = useState([]);

  /**
   * Fetchs all the folders items inside the actual folder
   */
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

  /**
   * Fetchs all the to-do's items inside the actual folder
   */
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

  /**
   * Executes when the hook idFolderActual is changed
   * so it fetches all the items inside the new actual folder.
   */
  useEffect(() => {
    fetchFolders();
    fetchTodos();
  }, [idFolderActual]);

  return (
    <PageWrapper
      idFolderActual={idFolderActual}
      onCreate={() => {
        fetchFolders();
        fetchTodos();
      }}
    >
      <ListItems
        onEnterFolder={(idFolderToEnter, nameFolder) => {
          setIdFolderActual(idFolderToEnter);
        }}
        onDeleteItem={() => {
          fetchFolders();
          fetchTodos();
        }}
        folders={listFolders}
        todos={listTodos}
      ></ListItems>
    </PageWrapper>
  );
}

export default App;
