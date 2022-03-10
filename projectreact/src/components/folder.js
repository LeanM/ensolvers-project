import { useState } from "react";
import { deleteFolder } from "../client";

export default function Folder(props) {
  const [name, setName] = useState(props.name);
  return (
    <div className="body__list-items__item-folder">
      <a
        className="body__list-items__item-folder__folderlink"
        onClick={() => {
          props.onEnterFolder(props.id, props.name);
        }}
      >
        <span className="body__list-items__item-folder__text">{name}</span>
      </a>
      <a
        className="body__list-items__item-folder__editbutton"
        onClick={() => {}}
      >
        <span>Edit</span>
      </a>
      <a
        onClick={() => {
          deleteFolder(props.id).then(() => {
            props.onDeleteItem();
          });
        }}
        className="body__list-items__item-folder__deletebutton"
      >
        <span>Delete</span>
      </a>
    </div>
  );
}
