package ru.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senchenko.dao.ProductDao;
import ru.senchenko.entities.Product;
import ru.senchenko.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements CrudInterface<ProductDao> {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void create(ProductDao dao) {
        Product product = new Product();
        product.setId(dao.getId());
        product.setCreateDate(dao.getCreateDate());
        product.setModifyDate(dao.getModifyDate());
        product.setTitle(dao.getTitle());
        product.setPrice(dao.getPrice());
        product.setDescription(dao.getDescription());
        product.setCategory(dao.getCategory());
        productRepo.save(product);
    }

    @Override
    public List<ProductDao> readAll() {
        return productRepo.findAll().stream().map(ProductDao::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDao> readById(Integer id) {
        return productRepo.findById(id).map(ProductDao::new);
    }

    @Override
    public void delete(Integer id) {
        productRepo.deleteById(id);
    }
}
