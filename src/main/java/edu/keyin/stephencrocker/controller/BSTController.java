package edu.keyin.stephencrocker.controller;

import edu.keyin.stephencrocker.model.BSTData;
import edu.keyin.stephencrocker.service.BSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BSTController {

    @Autowired
    private BSTService bstService;

    @GetMapping("/enter-numbers")
    public String showInputPage() {
        return "input-page";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbersStr, Model model) {
        try {
            List<Integer> numbers = bstService.parseNumbers(numbersStr);
            Map<String, Object> treeJson = bstService.buildTree(numbers);
            bstService.saveTree(numbers, treeJson);

            model.addAttribute("treeJson", treeJson);
            model.addAttribute("inputNumbers", numbersStr);
            return "result-page";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid input. Please enter comma-separated numbers.");
            return "input-page";
        }
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<BSTData> records = bstService.getAllTreeRecords();
        model.addAttribute("treeRecords", records);
        return "previous-trees";
    }
}