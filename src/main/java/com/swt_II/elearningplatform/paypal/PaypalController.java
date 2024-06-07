package com.swt_II.elearningplatform.paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    private  final PaypalService paypalService;

    @GetMapping("/pay")
    public String home(){
        return "paypal";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(){
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
           Payment payment = paypalService.createPayment(1.00, "EUR", "paypal", "sale", "payment description", cancelUrl, successUrl);
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
