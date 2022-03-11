export default function Modal(props) {
  return (
    <div className="body__list-items__modal">
      <button
        className="body__list-items__modal-exit"
        onClick={() => {
          let input = document.querySelector("#editNameText");
          input.value = "";
          props.onClose();
        }}
      >
        X
      </button>
      <div className="body__list-items__modal-title">
        <span>Edit Item name</span>
      </div>
      <input type="text" id="editNameText"></input>
      <button
        className="body__list-items__modal-complete"
        onClick={() => {
          let input = document.querySelector("#editNameText");
          let newName = input.value;
          input.value = "";
          props.onEdit(newName);
        }}
      >
        Complete
      </button>
    </div>
  );
}
