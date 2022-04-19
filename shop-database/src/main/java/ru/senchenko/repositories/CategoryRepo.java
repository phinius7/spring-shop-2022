package ru.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.senchenko.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id);
}
