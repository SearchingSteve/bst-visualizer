package edu.keyin.stephencrocker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public BSTService(BSTDataRepository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
    }

    public void insert(int value) {
        root = InsertRecursive(root, value);
    }

    private BSTNode InsertRecursive(BSTNode node, int value) {
        if (node == null)
            return new BSTNode(value);
        if (value < node.getValue()) {
            node.setLeft(InsertRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(InsertRecursive(node.getRight(), value));
        }
        return node;
    }

    public Map<String, Object> toJson(BSTNode node) {
        if (node == null)
            return null;
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
        try {
            String jsonString = objectMapper.writeValueAsString(treeJson);
            BSTData data = new BSTData(
                    numbers.toString(),
                    jsonString);
            repository.save(data);
        } catch (Exception e) {
            throw new RuntimeException("Error saving tree data", e);
        }
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

    public Map<String, Object> buildBalancedTree(List<Integer> numbers) {
        Collections.sort(numbers);
        clear();
        root = buildBalancedRecursive(numbers, 0, numbers.size() - 1);
        return toJson(root);
    }

    private BSTNode buildBalancedRecursive(List<Integer> nums, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(nums.get(mid));
        node.setLeft(buildBalancedRecursive(nums, start, mid - 1));
        node.setRight(buildBalancedRecursive(nums, mid + 1, end));
        return node;
    }

    public List<BSTData> getAllTreeRecords() {
        return repository.findAll();
    }
}