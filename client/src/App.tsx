import { ActivitiesList } from "./components/ActivitiesList";
import "./App.scss";

function App() {
  return (
    <div>
      <header className="app-header">
        <h3>Activities</h3>
      </header>
      <main className="app-main">
        <ActivitiesList />
      </main>
    </div>
  );
}

export default App;
