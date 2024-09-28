package com.productmanagement.ProductManagement.controller;

import com.productmanagement.ProductManagement.dto.ProductDTO;
import com.productmanagement.ProductManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getproducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/updateproduct")
    public ProductDTO editProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public String removeProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/product/{productId}")
    public ProductDTO getProduct(@PathVariable Integer productId) {
        return productService.getProduct(productId);
    }
}
