import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import InputForm from "./components/InputForm";
import TreeDisplay from "./components/TreeDisplay";
import PreviousTrees from "./components/PreviousTrees";
import TestConnection from "./components/TestConnection";

import "./App.css";

function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<InputForm />} />
          <Route path="/tree" element={<TreeDisplay />} />
          <Route path="/previous" element={<PreviousTrees />} />
          <Route path="/test" element={<TestConnection />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
