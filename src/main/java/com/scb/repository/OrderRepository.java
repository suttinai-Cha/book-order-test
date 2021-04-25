package com.scb.repository;

import com.scb.model.Orders;
import com.scb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Role> findByAccountId(String accountId);

}
