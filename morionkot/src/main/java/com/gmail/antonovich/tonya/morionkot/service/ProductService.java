package com.gmail.antonovich.tonya.morionkot.service;

import com.gmail.antonovich.tonya.morionkot.dto.*;
import com.gmail.antonovich.tonya.morionkot.entity.Category;
import com.gmail.antonovich.tonya.morionkot.entity.Characteristic;
import com.gmail.antonovich.tonya.morionkot.entity.Image;
import com.gmail.antonovich.tonya.morionkot.entity.Product;
import com.gmail.antonovich.tonya.morionkot.exeption.NoSuchEntityException;
import com.gmail.antonovich.tonya.morionkot.repository.CategoryRepository;
import com.gmail.antonovich.tonya.morionkot.repository.CharacteristicRepository;
import com.gmail.antonovich.tonya.morionkot.repository.ImageRepository;
import com.gmail.antonovich.tonya.morionkot.entity.*;
import com.gmail.antonovich.tonya.morionkot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CharacteristicRepository characteristicRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CharacteristicRepository characteristicRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.characteristicRepository = characteristicRepository;
        this.imageRepository = imageRepository;
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public String saveImages(MultipartFile image) throws Exception {
        String rootPath = System.getProperty("user.dir");
        String imageName = "";
        if (image != null) {
            byte[] bytes = image.getBytes();
            String customImageName = String.valueOf(UUID.randomUUID());
            String imageExtension = Optional.ofNullable(image.getOriginalFilename())
                    .filter(f -> f.contains("."))
                    .map(f -> f.substring(image.getOriginalFilename().lastIndexOf(".") + 1))
                    .orElseThrow(() -> new Exception("error"));
            imageName = customImageName + "." + imageExtension;
            File newFile = new File(rootPath + "/src/main/resources/images/" + imageName);
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(bytes);
            fos.close();
        }

        return imageName;
    }

    public void addProduct(NewProductDto newProductDto) throws NoSuchEntityException {

        Category category = categoryRepository.findByName(newProductDto.category)
                .orElseThrow(() -> new NoSuchEntityException("category not found"));

        Product product = new Product();
        product.setName(newProductDto.name);
        product.setDescription(newProductDto.description);
        product.setCategory(category);

        productRepository.save(product);

        for (int i = 0; i < newProductDto.price.size(); i++) {
            Characteristic characteristic = new Characteristic();
            characteristic.setPrice(newProductDto.price.get(i));
            characteristic.setWeight(newProductDto.weight.get(i));
            characteristic.setProduct(product);
            characteristicRepository.save(characteristic);
        }

        for (String imageName : newProductDto.image) {
            Image image = new Image();
            image.setName(imageName);
            image.setProduct(product);
            imageRepository.save(image);
        }

    }

    public List<ImageDto> deleteImage(DeleteImageDto deleteImageDto) throws NoSuchEntityException, IOException {
        List<Image> images = imageRepository.findByProductId(deleteImageDto.idProduct)
                .orElseThrow(() -> new NoSuchEntityException("images not found"));
        for(Image image: images){
            if(image.getName().equals(deleteImageDto.imageName)){
                imageRepository.delete(image);
            }
        }
        List<Image> imagesNew = imageRepository.findByProductId(deleteImageDto.idProduct)
                .orElseThrow(() -> new NoSuchEntityException("images not found"));
        List<ImageDto> imagesList = new ArrayList<>();
        for (Image image: imagesNew){
            ImageDto imageDto = new ImageDto(image.getName());
            imagesList.add(imageDto);
        }

        String rootPath = System.getProperty("user.dir");
        try { Files.deleteIfExists(Paths.get(rootPath + "/src/main/resources/images/" + deleteImageDto.imageName)); }
        catch(NoSuchFileException e) { System.out.println("No such file/directory exists"); }
        catch (Exception e){ System.out.println("error");}

        return imagesList;
    }

    public void updateProduct(ProductDto productDto) throws NoSuchEntityException {
        Product product = productRepository.findById(productDto.id)
                .orElseThrow(() -> new NoSuchEntityException("product not found"));
        Category category = categoryRepository.findByName(productDto.category.name)
                .orElseThrow(() -> new NoSuchEntityException("category not found"));
        List<Characteristic> characteristics = characteristicRepository.findByProductId(productDto.id)
                .orElseThrow(() -> new NoSuchEntityException("characteristic not found"));
        List<Image> images = imageRepository.findByProductId(productDto.id)
                .orElseThrow(() -> new NoSuchEntityException("images not found"));
        for(CharacteristicDto characteristicDto: productDto.characteristics){
            if(characteristicDto.id != null){
                for(Characteristic characteristic: characteristics){
                    if(characteristic.getId().equals(characteristicDto.id)) {
                        characteristic.setPrice(characteristicDto.price);
                        characteristic.setWeight(characteristicDto.weight);
                        characteristicRepository.save(characteristic);
                    }
                }
            }
            else {
                Product product1 = productRepository.findById(productDto.id)
                        .orElseThrow(() -> new NoSuchEntityException("product not found"));
                Characteristic characteristic = new Characteristic();
                characteristic.setWeight(characteristicDto.weight);
                characteristic.setPrice(characteristicDto.price);
                characteristic.setProduct(product1);
                characteristicRepository.save(characteristic);
            }
        }
        for(ImageDto imageDto: productDto.images){
            if(imageDto.id == null){
                Product product1 = productRepository.findById(productDto.id)
                        .orElseThrow(() -> new NoSuchEntityException("product not found"));
                Image image = new Image();
                image.setName(imageDto.name);
                image.setProduct(product1);
                imageRepository.save(image);
            }
        }

        product.setCategory(category);
        product.setName(productDto.name);
        product.setDescription(productDto.description);
        productRepository.save(product);
    }

    public void deleteProduct(ProductDto productDto) throws NoSuchEntityException {
        Product product = productRepository.findById(productDto.id)
                .orElseThrow(() -> new NoSuchEntityException("product not found"));
        List<Image> images = imageRepository.findByProductId(productDto.id)
                .orElseThrow(() -> new NoSuchEntityException("images not found"));
        String rootPath = System.getProperty("user.dir");
        for (Image image: images){
            try { Files.deleteIfExists(Paths.get(rootPath + "/src/main/resources/images/" + image.getName())); }
            catch(NoSuchFileException e) { System.out.println("No such file/directory exists"); }
            catch (Exception e){ System.out.println("error");}
        }

        productRepository.delete(product);
    }

    public List<ProductDto> getProductsFromCategory(CategoryDto categoryDto) throws NoSuchEntityException {
        List<ProductDto> productList = new ArrayList<>();
        try {
            Category category = categoryRepository.findById(categoryDto.id)
                    .orElseThrow(() -> new NoSuchEntityException("category not found"));
            List<Product> products = productRepository.findByCategoryId(category.getId())
                    .orElseThrow(() -> new NoSuchEntityException("products not found"));
            for (Product product : products) {
                List<CharacteristicDto> characteristicDto = new ArrayList<>();
                List<Characteristic> characteristics = product.getCharacteristics();
                for (Characteristic characteristic : characteristics) {
                    CharacteristicDto characteristicDto1 = new CharacteristicDto(characteristic.getId(), characteristic.getPrice(), characteristic.getWeight());
                    characteristicDto.add(characteristicDto1);
                }

                List<ImageDto> imageDtos = new ArrayList<>();
                List<Image> images = product.getImages();
                for (Image image : images) {
                    ImageDto imageDto = new ImageDto(image.getName());
                    imageDtos.add(imageDto);
                }
                ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getDescription(), characteristicDto, imageDtos, categoryDto);
                productList.add(productDto);
            }
            return productList;
        }
        catch (NoSuchEntityException e){
            return productList;
        }
    }


}
