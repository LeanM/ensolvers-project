export default function Todo(props) {
  return (
    <div className="body__list-items__item">
      <span className="body__list-items__item-text">{props.name}</span>
      <input
        className="body__list-items__item-checkbox"
        type="checkbox"
        checked={props.checked}
      ></input>
    </div>
  );
}
