import { addNewFolder, addNewTodo } from "../client";

export default function PageWrapper(props) {
  return (
    <div className="body">
      <h1 className="body__h1">To-do List</h1>
      <div className="body__directory">
        <span className="body__directory-text">MainFolder</span>
      </div>
      {props.children}
      <div className="body__add-items">
        <input
          id="add-item-text"
          className="body__add-items__text"
          type="text"
        ></input>
        <button
          className="body__add-items__add-todo"
          onClick={() => {
            let input = document.querySelector("#add-item-text");
            let name = input.value;
            input.value = "";
            addNewTodo(name, props.idFolderActual).then(() => props.onCreate());
          }}
        >
          Add To-do
        </button>
        <button
          className="body__add-items__add-folder"
          onClick={() => {
            let input = document.querySelector("#add-item-text");
            let name = input.value;
            input.value = "";
            addNewFolder(name, props.idFolderActual).then(() =>
              props.onCreate()
            );
          }}
        >
          Add Folder
        </button>
      </div>
    </div>
  );
}
