package com.groupHVC.CsHTTT.Controller;

import com.groupHVC.CsHTTT.Model.*;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import com.groupHVC.CsHTTT.Service.*;
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

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private OrderStatusService statusService;

    @GetMapping(value = "/admin/home")
    public String adminPage(Model model) {

        List<UserEntity> users = userService.getUsers();

        model.addAttribute("users", users);

        return "adminUser";
    }

    @RequestMapping(value = "/admin/deleteUser/{userId}", method = RequestMethod.GET)
    public String processDeleteUser(@PathVariable(name = "userId") Long userId) {

        userService.deleteById(userId);
        return "redirect:/admin/home";
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

    @PostMapping("/admin/process_modifyProduct")
    public String processModifyProduct(@ModelAttribute(name = "product") ProductEntity product){


        productService.updateProduct(product);

        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/admin/deleteProduct/{productId}", method = RequestMethod.GET)
    public String processDeleteProduct(@PathVariable(name = "productId") Long productId) {

        productService.deleteById(productId);
        return "redirect:/admin/products";
    }

    @GetMapping(value = "/admin/orders")
    public String adminOrders(Model model) {

        List<CartItemEntity> orders = cartService.listOrder();

        model.addAttribute("orders", orders);

        return "adminOrder";
    }

    @RequestMapping(value = "/admin/orders/{orderId}", method = RequestMethod.GET)
    public String pageUpdateOrder(@PathVariable(name = "orderId") Long orderId, Model model) {

        CartItemEntity order = cartService.cartItem(orderId);

        List<OrderStatusEntity> stat = statusService.getAllStatus();
        model.addAttribute("order", order);
        model.addAttribute("stat", stat);
        return "adminDetailOrder";
    }

    @RequestMapping(value = "/admin/updateOrder", method = RequestMethod.POST)
    public String processUpdateItem(@ModelAttribute(name = "order") CartItemEntity cartItem) {

        cartService.updateOrder(cartItem);
        return "redirect:/admin/orders";
    }
}
