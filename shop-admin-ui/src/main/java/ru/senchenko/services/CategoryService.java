package ru.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senchenko.dao.CategoryDao;
import ru.senchenko.entities.Category;
import ru.senchenko.repositories.CategoryRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CrudInterface<CategoryDao> {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    @Transactional
    public void create(CategoryDao dao) {
        Category category = new Category();
        category.setId(dao.getId());
        category.setCreateDate(dao.getCreateDate());
        category.setModifyDate(dao.getModifyDate());
        category.setTitle(dao.getTitle());
        category.setArticle(dao.getArticle());
        categoryRepo.save(category);
    }

    @Override
    public List<CategoryDao> readAll() {
        return categoryRepo.findAll().stream().map(CategoryDao::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDao> readById(Integer id) {
        return categoryRepo.findById(id).map(CategoryDao::new);
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.deleteById(id);
    }
}
