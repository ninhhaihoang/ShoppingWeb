package com.groupHVC.CsHTTT.Controller;

import com.groupHVC.CsHTTT.Model.CategoryEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import com.groupHVC.CsHTTT.Service.CategoryService;
import com.groupHVC.CsHTTT.Service.ProductService;
import com.groupHVC.CsHTTT.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/admin/home")
    public String adminPage(Model model) {

        List<UserEntity> users = userService.getUsers();

        model.addAttribute("users", users);

        return "adminUser";
    }

    @GetMapping(value = "/admin/products")
    public String adminProduct(Model model) {

        List<ProductEntity> products = productService.getProducts();

        model.addAttribute("products", products);

        return "adminProduct";
    }

    @RequestMapping(value = "/admin/page_addProduct", method = RequestMethod.GET)
    public String viewAddProductPage(Model model){
        model.addAttribute("product", new ProductEntity());

        List<CategoryEntity> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        return "adminProductAdd";
    }

    @PostMapping("/admin/process_addProduct")
    public String processAddProduct(@ModelAttribute(name = "product") ProductEntity product,
                                    @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setMainPicture(fileName);

        ProductEntity saveProduct = productRepository.save(product);

        String uploadDir = "./product-mainPicture/" + saveProduct.getProductId();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file:" + fileName);
        }
        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/admin/products/{productId}", method = RequestMethod.GET)
    public String pageModifyProduct(@PathVariable(name = "productId") Long productId, Model model) {

        ProductEntity product = productService.getProduct(productId);
        List<CategoryEntity> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);

        return "adminProductModify";
    }

    @RequestMapping(value = "/admin/deleteProduct/{productId}", method = RequestMethod.GET)
    public String processDeleteProduct(@PathVariable(name = "productId") Long productId) {

        productService.deleteById(productId);
        return "redirect:/admin/products";
    }
}
