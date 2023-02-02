package com.ozgurbaybas.stockmanagement.productservice.service;

import com.ozgurbaybas.stockmanagement.productservice.enums.Language;
import com.ozgurbaybas.stockmanagement.productservice.repository.entity.Product;
import com.ozgurbaybas.stockmanagement.productservice.request.ProductCreateRequest;
import com.ozgurbaybas.stockmanagement.productservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(Language language, ProductCreateRequest productCreateRequest);

    Product getProduct(Language language, Long productId);

    List<Product> getProducts(Language language);

    Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest);

    Product deleteProduct(Language language, Long productId);
}
