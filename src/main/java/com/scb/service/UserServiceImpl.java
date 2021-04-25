package com.scb.service;

import com.scb.model.Account;
import com.scb.model.Book;
import com.scb.model.Orders;
import com.scb.model.Role;
import com.scb.repository.AccountRepository;
import com.scb.repository.BookRepository;
import com.scb.repository.OrderRepository;
import com.scb.repository.RoleRepository;
import com.scb.security.config.PasswordEncoderConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    AccountRepository accountRepository;

    OrderRepository orderRepository;

    RoleRepository roleRepository;

    PasswordEncoderConfiguration passwordEncoder;

    BookRepository bookRepository;

    @Transactional
    @Override
    public Long addUUser(Account user) {
        user.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        Optional<Role> role = roleRepository.findByName("ROLE_USER");
        if (role.isPresent()) {
            Set<Role> roles = new HashSet<>();
            roles.add(role.get());
            user.setRoles(roles);
            user = accountRepository.save(user);
        }

        return user.getId();
    }

    @Override
    @Transactional
    public Account getUserInfo(String userName) {
        Optional<Account> userInfo = accountRepository.findByUsername(userName);
        if (!userInfo.isPresent()) {
            return new Account();
        }
        return userInfo.get();
    }

    @Override
    public void removeUser(String userName) {
        Account userInfo = getUserInfo(userName);
        accountRepository.delete(userInfo);
//        orderRepository.deleteOrder(userInfo.getId());
    }

    @Override
    public Double bookOrder(String userName, List<Long> orders) {
        Account userInfo = getUserInfo(userName);
        List<Orders> listOrder = new ArrayList<>();
        Orders order;
        Book bookInfo;
        BigDecimal total = new BigDecimal(0);
        for (Long orderId :
                orders) {
            order = new Orders();
             bookInfo = bookRepository.getOne(orderId);
            order.setPrice(bookInfo.getPrice());
            total = total.add(new BigDecimal(bookInfo.getPrice()));
            order.setBookId(orderId);
            order.setStatus("PENDING");
            order.setCreateDate(new Date());
            order.setAccount(userInfo);
            listOrder.add(order);
        }
        orderRepository.saveAll(listOrder);
        return total.doubleValue();
    }
}
