package edu.keyin.stephencrocker.service;

import edu.keyin.stephencrocker.model.BSTData;
import edu.keyin.stephencrocker.repository.BSTDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BSTServiceTest {
    @Autowired
    private BSTService bstService;

    @MockBean
    private BSTDataRepository repository;

    @BeforeEach
    void setUp() {
        bstService.clear();
        reset(repository);
    }

    @Test
    void testInsertAndJsonConversion() {
        bstService.insert(9);
        bstService.insert(3);

        Map<String, Object> treeJson = bstService.toJson(bstService.getRoot());

        assertNotNull(treeJson);
        assertEquals(9, treeJson.get("value"));
        assertNotNull(treeJson.get("left"));
    }

    @Test
    void testSaveTree() {
        bstService.insert(9);
        bstService.insert(3);

        Map<String, Object> treeJson = bstService.toJson(bstService.getRoot());
        List<Integer> numbers = List.of(9, 3);

        bstService.saveTree(numbers, treeJson);

        verify(repository, times(1)).save(any(BSTData.class));
    }

    @Test
    void testEmptyTree() {
        assertNull(bstService.toJson(bstService.getRoot()));
    }
}