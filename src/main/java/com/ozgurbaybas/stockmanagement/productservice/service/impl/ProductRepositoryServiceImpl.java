package com.ozgurbaybas.stockmanagement.productservice.service.impl;

import com.ozgurbaybas.stockmanagement.productservice.enums.Language;
import com.ozgurbaybas.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.ozgurbaybas.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.ozgurbaybas.stockmanagement.productservice.exception.exceptions.ProductNotFoundException;
import com.ozgurbaybas.stockmanagement.productservice.repository.ProductRepository;
import com.ozgurbaybas.stockmanagement.productservice.repository.entity.Product;
import com.ozgurbaybas.stockmanagement.productservice.request.ProductCreateRequest;
import com.ozgurbaybas.stockmanagement.productservice.request.ProductUpdateRequest;
import com.ozgurbaybas.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        try {
            Product product = Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);
            log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception exception) {
            throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "product request: " + productCreateRequest.toString());
        }
    }

    //bugsfixed
    @Override
    public Product getProduct(Language language, Long productId) {

        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(),productId);
        Product product = productRepository.getByProductIdAndDeletedFalse(productId);
        if (Objects.isNull(product)){
            throw new ProductNotFoundException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Product not found for product id:" +productId);
        }
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), product);
        return product;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
