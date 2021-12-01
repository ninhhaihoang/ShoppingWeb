package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.CategoryEntity;
import com.groupHVC.CsHTTT.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryEntity> getCategories (){
        return categoryRepository.findAll();
    }

    public CategoryEntity getCate(Long id) {
        return categoryRepository.findById(id).get();
    }
}
