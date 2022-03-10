import "./App.css";
import Todo from "./components/todo";
import Folder from "./components/folder";

function App() {
  return (
    <div className="body">
      <h1 className="body__h1">To-do List</h1>
      <div className="body__directory">
        <p className="body__directory__text">MainFolder</p>
      </div>
      <div className="body__list-items">
        <Todo name="dormir" />
        <Folder name="Mañana" />
      </div>
      <form></form>
    </div>
  );
}

export default App;
