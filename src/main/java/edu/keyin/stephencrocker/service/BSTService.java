package edu.keyin.stephencrocker.service;

import edu.keyin.stephencrocker.model.BSTNode;
import edu.keyin.stephencrocker.model.BSTData;
import edu.keyin.stephencrocker.repository.BSTDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BSTService {
    private BSTNode root;
    private final BSTDataRepository repository;  

    @Autowired
    public BSTService(BSTDataRepository repository) {  
        this.repository = repository;
    }

    public void insert(int value) {
        root = InsertRecursive(root, value);  
    }

    private BSTNode InsertRecursive(BSTNode node, int value) {
        if (node == null) return new BSTNode(value);
        if (value < node.getValue()) {
            node.setLeft(InsertRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(InsertRecursive(node.getRight(), value));
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

    public void saveTree(List<Integer> numbers, Map<String, Object> treeJson) {
        BSTData data = new BSTData(
                numbers.toString(),
                treeJson.toString()
        );
        repository.save(data);
    }

    public BSTNode getRoot() {
        return root;
    }

    public List<Integer> parseNumbers(String numbersStr) throws NumberFormatException {
        return Arrays.stream(numbersStr.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Map<String, Object> buildTree(List<Integer> numbers) {
        clear();
        numbers.forEach(this::insert);
        return toJson(root);
    }

    public List<BSTData> getAllTreeRecords() {
        return repository.findAll();
    }
}