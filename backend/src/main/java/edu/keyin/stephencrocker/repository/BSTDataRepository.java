package edu.keyin.stephencrocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.keyin.stephencrocker.model.BSTData;

public interface BSTDataRepository extends JpaRepository<BSTData, Long> {
}