import { useState } from "react";
import { deleteTodo, updateTodo } from "../client";

export default function Todo(props) {
  const [name, setName] = useState(props.name);
  const [check, setCheck] = useState(props.checked);

  return (
    <div className="body__list-items__item-todo">
      <span className="body__list-items__item-todo__text">{name}</span>
      <input
        className="body__list-items__item-todo__checkbox"
        type="checkbox"
        onChange={() => {
          setCheck(() => !check);
          updateTodo(props.id, name, !check);
        }}
        checked={check}
      ></input>
      <a className="body__list-items__item-todo__editbutton" onClick={() => {}}>
        <p>Edit</p>
      </a>
      <a
        onClick={() => {
          deleteTodo(props.id).then(() => {
            props.onDeleteItem();
          });
        }}
        className="body__list-items__item-todo__deletebutton"
      >
        <p>Delete</p>
      </a>
    </div>
  );
}
