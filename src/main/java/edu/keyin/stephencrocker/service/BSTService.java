package edu.keyin.stephencrocker.service;

import edu.keyin.stephencrocker.model.BSTNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class BSTService {
    private BSTNode root;

    public void insert(int value) {
        root = bstInsertRecursive(root, value);
    }

    private BSTNode bstInsertRecursive(BSTNode node, int value) {
        if (node == null) return new BSTNode(value);
        if (value < node.getValue()) {
            node.setLeft(bstInsertRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(bstInsertRecursive(node.getRight(), value));
        }
        return node;
    }

    public Map<String, Object> toJson(BSTNode node) {
        if (node == null) return null;
        Map<String, Object> jsonNode = new HashMap<>();
        jsonNode.put("value", node.getValue());
        jsonNode.put("left", toJson(node.getLeft()));
        jsonNode.put("right", toJson(node.getRight()));
        return jsonNode;
    }

    public void clear() {
        root = null;
    }
}