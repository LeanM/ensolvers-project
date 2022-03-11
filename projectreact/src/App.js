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
  const [folderDirectorio, setFolderDirectorio] = useState([[0, "MainFolder"]]);

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

  const backFolder = () => {
    if (folderDirectorio.length > 1) {
      folderDirectorio.pop();
      let lastFolder = folderDirectorio[folderDirectorio.length - 1];
      setIdFolderActual(lastFolder[0]);
    }
  };

  return (
    <PageWrapper
      idFolderActual={idFolderActual}
      folderDirectorio={folderDirectorio}
      onBack={() => {
        backFolder();
      }}
      onCreate={() => {
        fetchFolders();
        fetchTodos();
      }}
    >
      <ListItems
        onEnterFolder={(idFolderToEnter, nameFolder) => {
          setIdFolderActual(idFolderToEnter);
          folderDirectorio.push([idFolderToEnter, nameFolder]);
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
