import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const TreeDisplay = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const treeData = location.state?.treeData;

  if (!treeData) {
    return (
      <div className="tree-display">
        <h2>No Tree Data Available</h2>
        <button onClick={() => navigate("/")}>Go Back to Input</button>
      </div>
    );
  }

  const renderTree = (node, depth = 1) => {
    if (!node) return "null";

    const indent = "  ".repeat(depth);
    return (
      <div>
        {indent}
        {"{"}
        <div>
          {indent} "value": {node.value},
        </div>
        <div>
          {indent} "left":{" "}
          {node.left ? renderTree(node.left, depth + 1) : "null"},
        </div>
        <div>
          {indent} "right":{" "}
          {node.right ? renderTree(node.right, depth + 1) : "null"}
        </div>
        {indent}
        {"}"}
      </div>
    );
  };

  return (
    <div className="tree-display">
      <h2>Tree Structure</h2>
      <pre>
        {"{"}
        <div> "root": {renderTree(treeData)}</div>
        {"}"}
      </pre>
      <button onClick={() => navigate("/")}>Create New Tree</button>
      <button onClick={() => navigate("/previous")}>View Previous Trees</button>
    </div>
  );
};

export default TreeDisplay;
