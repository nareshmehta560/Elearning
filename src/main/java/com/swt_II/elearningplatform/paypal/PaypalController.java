package com.swt_II.elearningplatform.paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.cart.CartService;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    @Autowired
    private  final PaypalService paypalService;
    @Autowired
    private  final UserService userService;
    @Autowired
    private  final CartService cartService;

    @GetMapping("/pay")
    public String home(@RequestParam("amount") String amount, Model model){
        model.addAttribute("amount", amount);
        return "paypal";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(@RequestParam("amount") String amount){
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Double amountInt = Double.valueOf(amount);
           Payment payment = paypalService.createPayment(amountInt, "EUR", "paypal", "sale", "payment description", cancelUrl, successUrl);
            for(Links links : payment.getLinks()) {
                if(links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (Exception e) {
            log.error("error occured::" , e);

        }
        return new RedirectView("/payment/error");
    }
    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")) {
                User user = userService.getCurrentUser();
                List<Course> courses = cartService.getCartItemsForUser(user);
                Cart cart = cartService.getCartForUser(user);
                for(Course course : courses) {
                    user.getCourses().add(course);
                }
                userService.saveUser(user);
                cart.getCourses().clear();
                return "paymentSuccess";
            }
        } catch (Exception e) {
            log.error("error occured::" , e);
        }
        return "paymentError";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }
    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }

}
