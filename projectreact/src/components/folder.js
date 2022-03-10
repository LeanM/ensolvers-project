export default function Folder(props) {
  return (
    <div className="body__list-items__item">
      <a>
        <span className="body__list-items__item-text">{props.name}</span>
      </a>
    </div>
  );
}
