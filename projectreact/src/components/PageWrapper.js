export default function PageWrapper(props) {
  return (
    <div className="body">
      <h1 className="body__h1">To-do List</h1>
      <div className="body__directory">
        <p className="body__directory__text">MainFolder</p>
      </div>
      <div className="body__list-items">{props.children}</div>
      <form></form>
    </div>
  );
}
