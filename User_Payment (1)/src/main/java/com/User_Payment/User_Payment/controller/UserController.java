package com.User_Payment.User_Payment.controller;

import com.User_Payment.User_Payment.Entity.Payment;
import com.User_Payment.User_Payment.Entity.User;
import com.User_Payment.User_Payment.Repo.PaymentRepository;
import com.User_Payment.User_Payment.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/users")
//    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
    	
    	System.out.println(userRepository.findAll());
    	return userRepository.findAll();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
@PutMapping("/users/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    Optional<User> userOptional = userRepository.findById(id);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        user.setName(updatedUser.getName());
        // Update 
        userRepository.save(user);
        return user;
    } else {
       
        throw new ResourceNotFoundException("User not found with id: " + id);
    }
}

//    @PutMapping("/users/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setName(updatedUser.getName());
//                    return userRepository.save(user);
//                })
//                .orElse(null);
//    }
   //     @PutMapping("/users/{id}")
   // public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
   //     return userRepository.findById(id)
   //             .map(user -> {
   //                 user.setName(updatedUser.getName());
   //                 return userRepository.save(user);
   //             })
   //             .orElse(null);
   // }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    payment.setAmount(updatedPayment.getAmount());
                    payment.setPaymentType(updatedPayment.getPaymentType());
                    return paymentRepository.save(payment);
                })
                .orElse(null);
    }

    @DeleteMapping("/payments/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentRepository.deleteById(id);
    }
}
