package com.cg.mobilebilling.controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.Plan;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.services.BillingServices;
@Controller
public class URIController {
	Customer customer;
	@Autowired
	BillingServices billingServices;
	@RequestMapping("/")
	public String getIndexPage() {
		Plan plan1=new Plan(100, 50, 100, 200, 100, 0.15f, 0.25f, 0.10f, 0.15f, 0.50f, "Mumbai-Pune", "Diwali-Dhamaka");
		billingServices.savingNewPlanInTable(plan1);
		Plan plan2=new Plan(110, 90, 120, 170, 150, 0.12f, 0.20f, 0.05f, 0.25f, 0.60f, "Pune-Goa", "Diwali-Double-Dhamaka");
		billingServices.savingNewPlanInTable(plan2);
		Plan plan3=new Plan(135, 111, 125, 160, 160, 0.17f, 0.22f, 0.10f, 0.28f, 0.63f, "Rajasthan-Gujarat", "Diwali-Special-Dhamaka");
		billingServices.savingNewPlanInTable(plan3);
		Plan plan4=new Plan(120, 123, 122, 175, 154, 0.14f, 0.21f, 0.12f, 0.22f, 0.62f, "Pune-Maharashtra", "Diwali-Hungama");
		billingServices.savingNewPlanInTable(plan4);
		Plan plan5=new Plan(100, 120, 120, 170, 150, 0.12f, 0.22f, 0.15f, 0.24f, 0.64f, "Kota-Juipur", "New-Year-Hungama");
		billingServices.savingNewPlanInTable(plan5);
		Plan plan6=new Plan(130, 110, 130, 190, 150, 0.11f, 0.22f, 0.10f, 0.23f, 0.65f, "Mumabai-Goa", "Ganesh-Chaturthi-Special");
		billingServices.savingNewPlanInTable(plan6);
		Plan plan7=new Plan(120, 100, 120, 170, 150, 0.12f, 0.20f, 0.05f, 0.25f, 0.60f, "Chennai", "Diwali-Special-Dhamaka");
		billingServices.savingNewPlanInTable(plan7);
		Plan plan8=new Plan(130, 80, 110, 160, 180, 0.16f, 0.25f, 0.15f, 0.29f, 0.63f, "Kolkata-Bangal", "Chatth-Pooja-Dhamaka");
		billingServices.savingNewPlanInTable(plan8);
		Plan plan9=new Plan(140, 110, 160, 130, 140, 0.14f, 0.21f, 0.25f, 0.19f, 0.610f, "Delhi-Hariyana", "New-Year-Speacial");
		billingServices.savingNewPlanInTable(plan9);
		Plan plan10	=new Plan(90, 120, 160, 120, 200, 0.14f, 0.22f, 0.15f, 0.25f, 0.40f, "Rajsathan-Gujarat", "Navratri Special");
		billingServices.savingNewPlanInTable(plan10);
		return "indexPage";
	}
	@RequestMapping("/acceptCustomerDetailsPage")
	public String getAcceptCustomerDetailsPage() {
		return "acceptCustomerDetailsPage";
	}
	@RequestMapping("/getPlanAllDetailsPage")
	public ModelAndView getPlanDetailsPage() throws BillingServicesDownException{
		List<Plan>listOfPlans=billingServices.getPlanAllDetails();
		return new ModelAndView("getPlanAllDetailsPage","listOfPlans",listOfPlans);
	}
	@RequestMapping("/openPostpaidMobileAccountPage")
	public String openPostpaidMobileAccountPage() {
		return "openPostpaidMobileAccountPage";
	}
	@RequestMapping("getCustomerDetailsPage")
	public String getCustomerDetailsPage(){
		return "getCustomerDetailsPage";
	}
	@RequestMapping("getPostPaidAccountDetailsPage")
	public String getPostPaidAccountDetailsPage(){
		return "getPostPaidAccountDetailsPage";
	}
	@RequestMapping("getAllCustomerDetailsPage")
	public ModelAndView getAllCustomerDetailsPage() throws BillingServicesDownException{
		List<Customer>listOfCustomers=billingServices.getAllCustomerDetails();
		return new ModelAndView("getAllCustomerDetailsPage","listOfCustomers",listOfCustomers);
	}
	@RequestMapping("changePlanPage")
	public String changePlanPage(){
		return "changePlanPage";
	}
	@RequestMapping("getCustomerAllPostpaidAccountsDetailsPage")
	public String getCustomerAllPostpaidAccountsDetailsPage(){
		return "getCustomerAllPostpaidAccountsDetailsPage";
	}
	@RequestMapping("closeCustomerPostPaidAccountPage")
	public String closeCustomerPostPaidAccountPage(){
		return "closeCustomerPostPaidAccountPage";
	}
	@RequestMapping("deleteCustomerPage")
	public String deleteCustomerPage(){
		return "deleteCustomerPage";
	}
	@RequestMapping("getCustomerPostPaidAccountPlanDetailsPage")
	public String getCustomerPostPaidAccountPlanDetailsPage(){
		return "getCustomerPostPaidAccountPlanDetailsPage";
	}
	@RequestMapping("generateMonthlyMobileBillPage")
	public String generateMonthlyMobileBill(){
		return "generateMonthlyMobileBillPage";
	}
	@RequestMapping("getMobileBillDetailsPage")
	public String getMobileBillDetailsPage(){
		return "getMobileBillDetailsPage";
	}
	
	@RequestMapping("getCustomerPostPaidAccountAllBillDetailsPage")
	public String getCustomerPostPaidAccountAllBillDetails(){
		return "getCustomerPostPaidAccountAllBillDetailsPage";
	}
	
	@ModelAttribute
	public Customer getCustomer() {
		customer=new Customer();
		return customer;
	}
}

