package com.swt_II.elearningplatform.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Service for handling PayPal payment operations.
 */
@Service
@RequiredArgsConstructor
public class PaypalService {

    private final APIContext apiContext;

    /**
     * Creates a PayPal payment.
     *
     * @param total the total amount for the payment
     * @param currency the currency for the payment
     * @param method the payment method (e.g., "paypal")
     * @param intent the payment intent (e.g., "sale")
     * @param description a description of the payment
     * @param cancelUrl the URL to redirect to if the payment is canceled
     * @param successUrl the URL to redirect to if the payment is successful
     * @return the created Payment object
     * @throws Exception if an error occurs during payment creation
     */
    public Payment createPayment(Double total,
                                 String currency,
                                 String method,
                                 String intent,
                                 String description,
                                 String cancelUrl,
                                 String successUrl) throws Exception {

        Amount amt = new Amount();
        amt.setCurrency(currency);
        amt.setTotal(String.format(Locale.US, "%.2f", total));

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

    /**
     * Executes an approved PayPal payment.
     *
     * @param paymentId the ID of the payment to execute
     * @param payerId the ID of the payer
     * @return the executed Payment object
     * @throws Exception if an error occurs during payment execution
     */
    public Payment executePayment(String paymentId, String payerId) throws Exception {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
