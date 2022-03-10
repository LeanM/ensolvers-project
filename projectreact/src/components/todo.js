export default function Todo(props) {
  return (
    <div className="body__list-items__item">
      {props.name}
      <input type="checkbox"></input>
    </div>
  );
}
