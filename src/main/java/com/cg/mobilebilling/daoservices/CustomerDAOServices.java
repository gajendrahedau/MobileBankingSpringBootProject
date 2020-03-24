package com.cg.mobilebilling.daoservices;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cg.mobilebilling.beans.Customer;
public interface CustomerDAOServices extends JpaRepository<Customer,Integer>{
	@Transactional
	@Modifying
	@Query("DELETE FROM Customer c WHERE c.customerID=:customerID")
	void deleteCustomer(@Param("customerID") int customerID);
}
