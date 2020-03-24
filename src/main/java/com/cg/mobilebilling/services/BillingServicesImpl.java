package com.cg.mobilebilling.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.daoservices.BillingDAOServices;
import com.cg.mobilebilling.daoservices.CustomerDAOServices;
import com.cg.mobilebilling.daoservices.PlanDAOServices;
import com.cg.mobilebilling.daoservices.PostPaidDAOServices;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
	@Autowired
	private BillingDAOServices billingDAOServices;
	@Autowired
	private PostPaidDAOServices postPaidDAOServices;
	@Autowired
	private CustomerDAOServices customerDAOServices;
	@Autowired
	private PlanDAOServices planDAOServices; 
	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		return planDAOServices.findAll();
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer)
			throws BillingServicesDownException {
		customer=customerDAOServices.save(customer);
		return customer;
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer=customerDAOServices.findById(customerID).orElseThrow(()-> new CustomerDetailsNotFoundException("Customer with customer id "+customerID+" does not exist."));
		Plan plan=planDAOServices.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("Plan with plan id "+planID+" does not exist."));
		PostpaidAccount postpaidAccount=new PostpaidAccount(plan, customer);
		postpaidAccount=postPaidDAOServices.save(postpaidAccount);
		return postpaidAccount.getMobileNo();
	}

	@Override
	public int generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillingServicesDownException, PlanDetailsNotFoundException,BillDetailsNotFoundException {
		customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Sorry!! Post Paid Account does not exist."));
		PostpaidAccount postpaidAccount=getPostPaidAccountDetails(customerID, mobileNo);
		Bill bill1=getMobileBillDetails(customerID, mobileNo, billMonth);
		if(bill1!=null) throw new InvalidBillMonthException("Bill is already generated for "+billMonth+" month.");
		Bill bill=new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, postpaidAccount, billMonth);
		if(noOfLocalSMS>postpaidAccount.getPlan().getFreeLocalSMS())
			bill.setLocalSMSAmount((noOfLocalSMS-postpaidAccount.getPlan().getFreeLocalSMS())*postpaidAccount.getPlan().getLocalSMSRate());
		if(noOfStdSMS>postpaidAccount.getPlan().getFreeStdSMS())
			bill.setStdSMSAmount((noOfStdSMS-postpaidAccount.getPlan().getFreeStdSMS())*postpaidAccount.getPlan().getStdSMSRate());
		if(noOfLocalCalls>postpaidAccount.getPlan().getFreeLocalCalls())
			bill.setLocalCallAmount((noOfLocalCalls-postpaidAccount.getPlan().getFreeLocalCalls())*postpaidAccount.getPlan().getLocalCallRate());
		if(noOfStdCalls>postpaidAccount.getPlan().getFreeStdCalls())
			bill.setStdCallAmount((noOfStdCalls-postpaidAccount.getPlan().getFreeStdCalls())*postpaidAccount.getPlan().getStdCallRate());
		if(internetDataUsageUnits>postpaidAccount.getPlan().getFreeInternetDataUsageUnits())
			bill.setInternetDataUsageAmount((internetDataUsageUnits-postpaidAccount.getPlan().getFreeInternetDataUsageUnits())*postpaidAccount.getPlan().getInternetDataUsageRate());
		float initialAmount=bill.getLocalSMSAmount()+bill.getLocalCallAmount()+bill.getStdSMSAmount()+bill.getStdCallAmount()+bill.getInternetDataUsageAmount()+postpaidAccount.getPlan().getMonthlyRental();
		bill.setServicesTax((initialAmount*5)/100);
		bill.setTotalBillAmount(initialAmount+bill.getServicesTax());
		billingDAOServices.save(bill);
		return bill.getBillID();
	}
	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		return customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer with customer id "+customerID+" does not exist."));
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {
		return customerDAOServices.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Sorry!! Post Paid Account does not exist."));
		return postPaidDAOServices.findById(mobileNo).orElseThrow(()->new PostpaidAccountNotFoundException("Sorry!! Post Paid account does not exist."));
	}
	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Sorry!! Customer Id "+customerID+" does not exist."));
		return postPaidDAOServices.getCustomerPostPaidAccounts(customerID);
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {
		customerDAOServices.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Sorry!! Customer Id "+customerID+" does not exist."));
		getPostPaidAccountDetails(customerID, mobileNo);
		return billingDAOServices.getMonthlyBill(billMonth, mobileNo);
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			BillDetailsNotFoundException {
		getCustomerDetails(customerID);
		getPostPaidAccountDetails(customerID, mobileNo);
		List<Bill>listOfBill=billingDAOServices.getCustomerPostPaidAccountAllBills(mobileNo);
		return listOfBill;
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
		PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {	
		getCustomerDetails(customerID);
		PostpaidAccount postpaidAccount=new PostpaidAccount((getPostPaidAccountDetails(customerID, mobileNo)).getMobileNo(),planDAOServices.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("Plan with plan id "+planID+" does not exist.")),getCustomerDetails(customerID));
		postPaidDAOServices.save(postpaidAccount);
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		getCustomerDetails(customerID);
		getPostPaidAccountDetails(customerID, mobileNo);
		billingDAOServices.deleteBill(mobileNo);
		postPaidDAOServices.deleteById(mobileNo);
		return true;
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillDetailsNotFoundException {
		Customer customer=getCustomerDetails(customerID);
		List<PostpaidAccount>postpaidAccounts=getCustomerAllPostpaidAccountsDetails(customerID);
		for (PostpaidAccount postpaidAccount : postpaidAccounts) {
			List<Bill>bills=getCustomerPostPaidAccountAllBillDetails(customerID, postpaidAccount.getMobileNo());
			for (Bill bill : bills) 
				billingDAOServices.deleteBill(postpaidAccount.getMobileNo());
			postPaidDAOServices.deletePostPaidAccount(postpaidAccount);
		}
		customerDAOServices.deleteById(customerID);
		return true;
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		return (getPostPaidAccountDetails(customerID, mobileNo)).getPlan();
	}
	@Override
	public Plan savingNewPlanInTable(Plan plan){
		plan=planDAOServices.save(plan);
		return plan;
	}
}