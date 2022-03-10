import "./App.css";
import Todo from "./components/todo";
import Folder from "./components/folder";
import PageWrapper from "./components/PageWrapper";
import listTodos from "./listTodos.json";

function App() {
  return (
    <PageWrapper>
      {listTodos.map((todo) => {
        return <Todo name={todo.name} checked={todo.checked}></Todo>;
      })}
      <Folder name="Mañana" />
    </PageWrapper>
  );
}

export default App;
