import { useState } from "react";
import { deleteFolder, updateFolder } from "../client";
import Modal from "./modal";

export default function Folder(props) {
  const [name, setName] = useState(props.name);
  const [openModal, setOpenModal] = useState(false);

  return (
    <div className="body__list-items__item-folder">
      {openModal && (
        <Modal
          onClose={() => {
            setOpenModal(false);
          }}
          onEdit={(newName) => {
            setOpenModal(false);
            setName(newName);
            updateFolder(props.id, newName);
          }}
        ></Modal>
      )}
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
        onClick={() => {
          setOpenModal(true);
        }}
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
