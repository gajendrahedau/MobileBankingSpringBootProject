package com.cg.mobilebilling.daoservices;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.mobilebilling.beans.PostpaidAccount;
public interface PostPaidDAOServices extends JpaRepository<PostpaidAccount,Long>{
	@Query("SELECT p FROM PostpaidAccount p WHERE p.customer.customerID=:customerID")
	List<PostpaidAccount> getCustomerPostPaidAccounts(@Param("customerID") int customerID);
	@Transactional
	@Modifying
	@Query("DELETE FROM PostpaidAccount p WHERE p=:postpaidAccount")
	void deletePostPaidAccount(@Param("postpaidAccount") PostpaidAccount postpaidAccount);
}
