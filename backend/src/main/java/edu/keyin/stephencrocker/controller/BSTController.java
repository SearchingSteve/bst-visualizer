package edu.keyin.stephencrocker.controller;

import edu.keyin.stephencrocker.model.BSTData;
import edu.keyin.stephencrocker.service.BSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BSTController {

    @Autowired
    private BSTService bstService;

    @PostMapping("/process-numbers")
    public Map<String, Object> processNumbers(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> numbers = request.get("numbers");
        Map<String, Object> treeJson = bstService.buildTree(numbers);
        bstService.saveTree(numbers, treeJson);
        return treeJson;
    }

    @PostMapping("/process-balanced-numbers")
    public Map<String, Object> processBalancedNumbers(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> numbers = request.get("numbers");
        Map<String, Object> treeJson = bstService.buildBalancedTree(numbers);
        bstService.saveTree(numbers, treeJson);
        return treeJson;
    }

    @GetMapping("/previous-trees")
    public List<BSTData> getPreviousTrees() {
        return bstService.getAllTreeRecords();
    }
}