package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
