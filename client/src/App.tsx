import React from "react";
import { ErrorBoundary } from "react-error-boundary";
import { ActivitiesList } from "./components/ActivitiesList";
import { useConsole } from "./hooks";
import "./App.scss";

const FallbackComponent = () => {
  return <div>Something went wrong</div>;
};

const App = () => {
  const { consoleError } = useConsole();

  return (
    <div>
      <header className="app-header">
        <h3>Activities</h3>
      </header>
      <ErrorBoundary
        FallbackComponent={FallbackComponent}
        onError={(error) => consoleError(JSON.stringify(error))}
      >
        <main className="app-main" role="main">
          <ActivitiesList />
        </main>
      </ErrorBoundary>
    </div>
  );
};

export default App;
