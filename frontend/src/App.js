import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TestConnection from "./components/TestConnection";
import "./App.css";

function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<TestConnection />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
