import Folder from "./folder";
import Todo from "./todo";

export default function ListItems(props) {
  let array_items = [];

  props.folders.map((folder) => {
    array_items.push(
      <Folder
        key={folder.id}
        id={folder.id}
        name={folder.name}
        onEnterFolder={(idFolderToEnter, nameFolder) =>
          props.onEnterFolder(idFolderToEnter, nameFolder)
        }
        onDeleteItem={() => props.onDeleteItem()}
      ></Folder>
    );
  });
  props.todos.map((todo) => {
    array_items.push(
      <Todo
        key={todo.id}
        id={todo.id}
        name={todo.name}
        checked={todo.checked}
        onDeleteItem={() => props.onDeleteItem()}
      ></Todo>
    );
  });

  return <div className="body__list-items">{array_items}</div>;
}
