package com.swt_II.elearningplatform.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaypalService {

   private  final APIContext apiContext;
   public Payment createPayment(Double total,
                                String currency,
                                String method,
                                String intent,
                                String description,
                                String cancelUrl,
                                String successUrl) throws Exception {

       Amount amt = new Amount();
       amt.setCurrency(currency);
       amt.setTotal(String.format(Locale.US,"%.2f", total));

       Transaction transaction = new Transaction();
       transaction.setDescription(description);
       transaction.setAmount(amt);


       List<Transaction> transactions = new ArrayList<>();
       transactions.add(transaction);


       Payer payer = new Payer();
       payer.setPaymentMethod(method);

       Payment payment = new Payment();
       payment.setIntent(intent);
       payment.setPayer(payer);
       payment.setTransactions(transactions);

       payment.setRedirectUrls(new RedirectUrls().setCancelUrl(cancelUrl).setReturnUrl(successUrl));

       return payment.create(apiContext);
   }

   public Payment executePayment(String paymentId, String payerId) throws Exception {
       Payment payment = new Payment();
       payment.setId(paymentId);
       PaymentExecution paymentExecute = new PaymentExecution();
       paymentExecute.setPayerId(payerId);
       return payment.execute(apiContext, paymentExecute);
   }


}
