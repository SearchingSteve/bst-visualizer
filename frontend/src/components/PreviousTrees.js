import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getPreviousTrees } from "../services/api";

const PreviousTrees = () => {
  const [trees, setTrees] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchTrees = async () => {
      try {
        const response = await getPreviousTrees();
        setTrees(response.data);
      } catch (error) {
        console.error("Error fetching previous trees:", error);
        alert("Error fetching previous trees. Please try again later.");
      }
    };
    fetchTrees();
  }, []);

  const parseTreeJson = (jsonString) => {
    try {
      const cleanJson = jsonString.replace(/\\n/g, "").replace(/\\/g, "");
      return JSON.parse(cleanJson);
    } catch (error) {
      console.error("Error parsing tree JSON:", error);
      return null;
    }
  };

  return (
    <div className="previous-trees">
      <h2>Previous Binary Search Trees</h2>

      <div className="trees-list">
        {trees.length === 0 ? (
          <p>No previous trees found.</p>
        ) : (
          <div className="trees-grid">
            {trees.map((tree, index) => {
              return (
                <div key={index} className="tree-card">
                  <h3>Tree #{index + 1}</h3>
                  <p className="input-numbers">Input: {tree.inputNumbers}</p>
                  <button
                    onClick={() =>
                      navigate("/tree", {
                        state: { treeData: parseTreeJson(tree.treeJson) },
                      })
                    }
                  >
                    View Details
                  </button>
                </div>
              );
            })}
          </div>
        )}
      </div>

      <div className="navigation-buttons">
        <button onClick={() => navigate("/")}>Create New Tree</button>
      </div>
    </div>
  );
};

export default PreviousTrees;
