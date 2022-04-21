package ru.senchenko.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.senchenko.dao.ProductDao;
import ru.senchenko.entities.Picture;
import ru.senchenko.entities.Product;
import ru.senchenko.repositories.ProductRepo;
import ru.senchenko.service.PictureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements CrudInterface<ProductDao> {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepo productRepo;
    private final PictureService pictureService;

    @Autowired
    public ProductService(ProductRepo productRepo, PictureService pictureService) {
        this.productRepo = productRepo;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public void create(ProductDao dao) throws IOException {
        Product product = new Product();
        product.setId(dao.getId());
        product.setCreateDate(dao.getCreateDate());
        product.setModifyDate(dao.getModifyDate());
        product.setTitle(dao.getTitle());
        product.setPrice(dao.getPrice());
        product.setDescription(dao.getDescription());
        product.setCategory(dao.getCategory());
        if (dao.getNewPicture() != null) {
            for (MultipartFile newPicture : dao.getNewPicture()) {
                logger.info("Product {}, file '{}', size {}, content type {}", dao.getId(), newPicture.getOriginalFilename(),
                        newPicture.getSize(), newPicture.getContentType());
                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }
                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        product
                        ));
            }
        }
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
