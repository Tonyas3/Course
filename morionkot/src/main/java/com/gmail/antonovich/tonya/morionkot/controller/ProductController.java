package com.gmail.antonovich.tonya.morionkot.controller;

import com.gmail.antonovich.tonya.morionkot.dto.*;
import com.gmail.antonovich.tonya.morionkot.exeption.NoSuchEntityException;
import com.gmail.antonovich.tonya.morionkot.service.ProductService;
import com.gmail.antonovich.tonya.morionkot.util.Mapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {this.productService = productService;}

    @GetMapping("products")
    public List<ProductDto> findProducts() {return Mapper.mapAll(productService.findProducts(), ProductDto.class);}

    @PostMapping("image-product")
    public String uploadImage(@RequestParam("file") MultipartFile image) throws Exception {
        return  productService.saveImages(image);
    }

    @PostMapping("add-product")
    public void addProduct(@RequestBody NewProductDto newProductDto) throws NoSuchEntityException {
        productService.addProduct(newProductDto);
    }

    @PostMapping("delete-image")
    public List<ImageDto> deleteImage(@RequestBody DeleteImageDto deleteImageDto) throws NoSuchEntityException, IOException {
        return productService.deleteImage(deleteImageDto);
    }

    @PostMapping("change-product")
    public void updateProduct(@RequestBody ProductDto productDto) throws NoSuchEntityException {
        productService.updateProduct(productDto);
    }

    @PostMapping("delete-product")
    public void deleteProduct(@RequestBody ProductDto productDto) throws NoSuchEntityException {
        productService.deleteProduct(productDto);
    }

    @PostMapping("product-from-category")
    public List<ProductDto> getProductsFromCategory(@RequestBody CategoryDto category) throws NoSuchEntityException {
        return productService.getProductsFromCategory(category);
    }
}
