export default function Folder(props) {
  return (
    <div className="body__list-items__item">
      <a href={"/" + props.name}> {props.name} </a>
    </div>
  );
}
