import React, { useState, useEffect } from "react";
import { processNumbers, getPreviousTrees } from "../services/api";

const TestConnection = () => {
  const [testNumbers, setTestNumbers] = useState("1,2,3,4,5");
  const [result, setResult] = useState(null);
  const [previousTrees, setPreviousTrees] = useState([]);
  const [error, setError] = useState(null);

  const handleTestPost = async () => {
    try {
      const numbers = testNumbers.split(",").map((num) => parseInt(num.trim()));
      const response = await processNumbers(numbers);
      setResult(response.data);
      setError(null);
    } catch (err) {
      setError(err.message);
      setResult(null);
    }
  };

  const handleTestGet = async () => {
    try {
      const response = await getPreviousTrees();
      setPreviousTrees(response.data);
      setError(null);
    } catch (err) {
      setError(err.message);
      setPreviousTrees([]);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>API Connection Test</h2>

      <div style={{ marginBottom: "20px" }}>
        <h3>Test POST Request</h3>
        <input
          type="text"
          value={testNumbers}
          onChange={(e) => setTestNumbers(e.target.value)}
          placeholder="Enter numbers separated by commas"
          style={{ marginRight: "10px" }}
        />
        <button onClick={handleTestPost}>Test POST</button>
        {result && (
          <div style={{ marginTop: "10px" }}>
            <h4>Result:</h4>
            <pre>{JSON.stringify(result, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>Test GET Request</h3>
        <button onClick={handleTestGet}>Test GET</button>
        {previousTrees.length > 0 && (
          <div style={{ marginTop: "10px" }}>
            <h4>Previous Trees:</h4>
            <pre>{JSON.stringify(previousTrees, null, 2)}</pre>
          </div>
        )}
      </div>

      {error && (
        <div style={{ marginTop: "20px", color: "red" }}>
          <h4>Error:</h4>
          <p>{error}</p>
        </div>
      )}
    </div>
  );
};

export default TestConnection;
