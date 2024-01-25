package com.example.artemisPractise.user.controller;


import com.example.artemisPractise.user.entity.PaymentDetails;
import com.example.artemisPractise.user.listener.MessageConsumer;
import com.example.artemisPractise.user.service.impl.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final PaymentServiceImpl paymentServiceImpl;

    public HomeController(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @GetMapping("/")
    public String test(){
        return "hello world";
    }

    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public void payment(@RequestBody PaymentDetails paymentDetails){
        logger.info(paymentDetails.toString());
        this.paymentServiceImpl.pay(paymentDetails);
    }
}
