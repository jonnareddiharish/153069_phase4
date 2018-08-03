package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.mypaymentapp.beans.Customer;

public interface WalletRepo extends JpaRepository<Customer, String> {
	@Query("select c from Customer c where c.wallet.balance=?1")
	public List getcustomer(BigDecimal amount);
}
