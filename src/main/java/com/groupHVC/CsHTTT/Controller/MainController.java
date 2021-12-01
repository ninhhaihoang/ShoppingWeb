package com.groupHVC.CsHTTT.Controller;

import com.groupHVC.CsHTTT.Model.CartItemEntity;
import com.groupHVC.CsHTTT.Model.CategoryEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import com.groupHVC.CsHTTT.Repository.UserRepository;
import com.groupHVC.CsHTTT.Service.CategoryService;
import com.groupHVC.CsHTTT.Service.ProductService;
import com.groupHVC.CsHTTT.Service.ShoppingCartService;
import com.groupHVC.CsHTTT.Utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;



@Controller
public class MainController {

    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoppingCartService cartService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = { "/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model, @Param("keyword") String keyword) {

        List<ProductEntity> suggest = productService.getSuggest();

        List<ProductEntity> products = productService.getIndexProducts();

        model.addAttribute("keyword", keyword);
        model.addAttribute("suggest", suggest);
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET)
    public String getSearch(@Param("keyword") String keyword, Model model) {

        List<ProductEntity> products = productService.getSearch(keyword);

        model.addAttribute("products", products);

        return "products";
    }

    @RequestMapping(value="/j_spring_security_logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/profilePage", method = RequestMethod.GET)
    public String userInfo(Principal principal, Model model) {

        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        UserEntity user = userRepository.findByUsername(userName);
        model.addAttribute("user", user);

        return "userProfile";
    }

    @RequestMapping(value = "/products/all")
    public String getAllProducts(Model model, @Param("keyword") String keyword) {

        List<ProductEntity> products = productService.getSearch(keyword);

        List<CategoryEntity> categories = categoryService.getCategories();

        model.addAttribute("keyword", keyword);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping(value = "/products/ao")
    public String getProductsByCategorya(Model model, @Param("keyword") String keyword) {

        CategoryEntity category = categoryService.getCate(1L);
        List<ProductEntity> products = productService.getProductsByCate(category);

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @GetMapping(value = "/products/quan")
    public String getProductsByCategoryq(Model model, @Param("keyword") String keyword) {

        CategoryEntity category = categoryService.getCate(2L);
        List<ProductEntity> products = productService.getProductsByCate(category);

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @GetMapping(value = "/products/new")
    public String getAllNewProducts(Model model, @Param("keyword") String keyword) {

        List<ProductEntity> products = productService.getNewProducts();

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public String getDetailProduct(Principal principal, @PathVariable(name = "productId") Long productId, Model model
            , @Param("keyword") String keyword) {

        ProductEntity product = productService.getProduct(productId);

        model.addAttribute("product", product);
        model.addAttribute("keyword", keyword);
        return "productDetail";
    }

    @GetMapping("/cart")
    public String showShoppingCart(Model model,Principal principal, @Param("keyword") String keyword) {

        String userName = principal.getName();
        UserEntity user = userRepository.findByUsername(userName);

        List<CartItemEntity> cartItems = cartService.listCartItems(user);

        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");
        model.addAttribute("keyword", keyword);
        return "cart";
    }

    @GetMapping("/confirmCartItem")
    public String confirmCartItem(Principal principal) {

        String userName = principal.getName();
        UserEntity user = userRepository.findByUsername(userName);

        cartService.confirmCartItem(user);

        return "history";
    }

    @GetMapping("/history")
    public String showShoppingHistory(Model model,Principal principal, @Param("keyword") String keyword) {

        String userName = principal.getName();
        UserEntity user = userRepository.findByUsername(userName);

        List<CartItemEntity> cartItems = cartService.listHistoryItems(user);

        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping History");
        model.addAttribute("keyword", keyword);
        return "history";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }
}

