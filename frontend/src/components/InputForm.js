import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { processNumbers, processBalancedNumbers } from "../services/api";

const InputForm = () => {
  const [numbers, setNumbers] = useState("");
  const [isBalanced, setIsBalanced] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const numbersList = numbers.split(",").map((num) => parseInt(num.trim()));
      const response = isBalanced
        ? await processBalancedNumbers(numbersList)
        : await processNumbers(numbersList);

      navigate("/tree", { state: { treeData: response.data } });
    } catch (error) {
      console.error("Error processing numbers:", error);
      alert("Error processing numbers. Please check your input format.");
    }
  };

  return (
    <div className="input-form">
      <h2>Binary Search Tree Visualizer</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="numbers">Enter numbers (comma-separated):</label>
          <input
            id="numbers"
            type="text"
            value={numbers}
            onChange={(e) => setNumbers(e.target.value)}
            placeholder="e.g., 1, 2, 3, 4, 5"
            required
          />
        </div>

        <div className="form-group">
          <label>
            <input
              type="checkbox"
              checked={isBalanced}
              onChange={(e) => setIsBalanced(e.target.checked)}
            />
            Create balanced tree
          </label>
        </div>

        <div className="button-group">
          <button type="submit">Create Tree</button>
          <button type="button" onClick={() => navigate("/previous")}>
            View Previous Trees
          </button>
        </div>
      </form>
    </div>
  );
};

export default InputForm;
