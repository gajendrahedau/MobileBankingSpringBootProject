package com.cg.mobilebilling.daoservices;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
public interface BillingDAOServices extends JpaRepository<Bill,Integer>{
	/* @Query("DELETE FROM Bill b WHERE b.postpaidAccount.customer.mobileNo=:mobileNo")
	
	long insertPostPaidAccount(int customerID, PostpaidAccount account);
	boolean updatePostPaidAccount(int customerID, PostpaidAccount account);
	int insertMonthlybill(int customerID, long mobileNo, Bill bill);
	int insertPlan(Plan plan) throws PlanDetailsNotFoundException;
	Customer getCustomer(int customerID);
	List<Customer>  getAllCustomers();
	List<Plan> getAllPlans();
	Plan getPlan(int planID) ;
	PostpaidAccount getCustomerPostPaidAccount(int customerID, long mobileNo);
	Plan getPlanDetails(int customerID, long mobileNo);
	*/
	
	@Query("SELECT b FROM Bill b WHERE b.postpaidAccount.mobileNo=:mobileNo AND b.billMonth=:billMonth")
	Bill getMonthlyBill(@Param("billMonth") String billMonth,@Param("mobileNo") long mobileNo);
	@Query("SELECT b FROM Bill b WHERE b.postpaidAccount.mobileNo=:mobileNo")
	List<Bill> getCustomerPostPaidAccountAllBills(@Param("mobileNo") long mobileNo);
	@Transactional
	@Modifying
	@Query("DELETE FROM Bill b WHERE b.postpaidAccount.mobileNo=:mobileNo")
	void deleteBill(@Param("mobileNo") long mobileNo);
}














