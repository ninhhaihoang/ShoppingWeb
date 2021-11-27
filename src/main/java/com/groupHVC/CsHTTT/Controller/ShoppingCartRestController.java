package com.groupHVC.CsHTTT.Controller;

import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.UserRepository;
import com.groupHVC.CsHTTT.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Long productId,
                                   @PathVariable("qty") Integer quantity,
                                   Principal principal) {
        try {
            String userName = principal.getName();

            System.out.println("addProductToCart: " + productId + " - " + quantity);

            UserEntity user = userRepository.findByUsername(userName);

            Integer addedQuantity = cartService.addProduct(productId, quantity, user);

            System.out.println("item added");

            return addedQuantity + "item(s) of this product were added to your shopping cart.";
        } catch (Exception e) {
            return "You must login to add this product to  your shopping cart.";
        }
    }

    @PostMapping("/cart/update/{pid}/{qty}")
    public String updateQuantity(@PathVariable("pid") Long productId,
                                   @PathVariable("qty") Integer quantity,
                                   Principal principal) {

            String userName = principal.getName();

            UserEntity user = userRepository.findByUsername(userName);

            Long subtotal = cartService.updateQuantity(productId, quantity, user);

            return String.valueOf(subtotal);

    }

    @PostMapping("/cart/remove/{pid}")
    public String removeProduct(@PathVariable("pid") Long productId,
                                 Principal principal) {

        String userName = principal.getName();

        UserEntity user = userRepository.findByUsername(userName);

        cartService.removeProduct(productId, user);

        return "The product has been removed from your shopping cart.";

    }
}
