package com.groupHVC.CsHTTT.Controller;

import com.groupHVC.CsHTTT.Model.CartItemEntity;
import com.groupHVC.CsHTTT.Model.ProductEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.ProductRepository;
import com.groupHVC.CsHTTT.Repository.UserRepository;
import com.groupHVC.CsHTTT.Service.ProductService;
import com.groupHVC.CsHTTT.Service.ShoppingCartService;
import com.groupHVC.CsHTTT.Utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = { "/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage() {
        return "index";
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

    @GetMapping(value = "/products/all")
    public String getAllProducts(Principal principal, Model model) {

//        String userName = principal.getName();
//
//        UserEntity user = userRepository.findByUsername(userName);
//        model.addAttribute("user", user);

        List<ProductEntity> products = productService.getProducts();

        model.addAttribute("products", products);

        return "products";
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public String getDetailProduct(Principal principal, @PathVariable(name = "productId") Long productId, Model model) {

//        String userName = principal.getName();
//
//        UserEntity user = userRepository.findByUsername(userName);
//        model.addAttribute("user", user);

        ProductEntity product = productService.getProduct(productId);

        model.addAttribute("product", product);

        return "productDetail";
    }

    @GetMapping("/cart")
    public String showShoppingCart(Model model,Principal principal) {

        String userName = principal.getName();
        UserEntity user = userRepository.findByUsername(userName);

        List<CartItemEntity> cartItems = cartService.listCartItems(user);

        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "cart";
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

