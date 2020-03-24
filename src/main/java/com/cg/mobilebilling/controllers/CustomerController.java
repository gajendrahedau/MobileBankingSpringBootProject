package com.cg.mobilebilling.controllers;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cg.mobilebilling.beans.Address;
import com.cg.mobilebilling.beans.Bill;
import com.cg.mobilebilling.beans.Customer;
import com.cg.mobilebilling.beans.PostpaidAccount;
import com.cg.mobilebilling.exceptions.BillDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.BillingServicesDownException;
import com.cg.mobilebilling.exceptions.CustomerDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.InvalidBillMonthException;
import com.cg.mobilebilling.exceptions.PlanDetailsNotFoundException;
import com.cg.mobilebilling.exceptions.PostpaidAccountNotFoundException;
import com.cg.mobilebilling.services.BillingServices;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.util.Date;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.cg.mobilebilling.beans.Bill;
@Controller
public class CustomerController {
	@Autowired
	private BillingServices billingServices;
	@RequestMapping("/acceptCustomerDetails")
	public ModelAndView acceptCustomerDetails(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("emailID") String emailID,@RequestParam("dateOfBirth") String dateOfBirth,@RequestParam("city") String city,@RequestParam("state") String state,@RequestParam("pinCode") int pinCode) throws BillingServicesDownException {
		Customer customer=new Customer(firstName, lastName, emailID, dateOfBirth,new Address(pinCode, city, state));
		customer=billingServices.acceptCustomerDetails(customer);
		return new ModelAndView("newAccepCustomerDetailsPage","customer",customer);
	}
	@RequestMapping("/openPostpaidMobileAccount")
	public ModelAndView openPostpaidMobileAccount(@RequestParam("customerID") int customerID,@RequestParam("planID") int planID){
		long mobileNo=0;
		PostpaidAccount postpaidAccount=null;
		try {
			mobileNo = billingServices.openPostpaidMobileAccount(customerID, planID);
			postpaidAccount = billingServices.getPostPaidAccountDetails(customerID, mobileNo);
		} catch (PlanDetailsNotFoundException | CustomerDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("openPostpaidMobileAccountPage","postpaidAccount",e.getMessage());	
		}
		catch (PostpaidAccountNotFoundException e) {
			return new ModelAndView("openPostpaidMobileAccountPage","postpaidAccount",e.getMessage());	
		}
		return new ModelAndView("newOpenPostpaidMobileAccountPage","postpaidAccount",postpaidAccount);	
	}
	@RequestMapping("/getCustomerDetails")
	public ModelAndView getCustomerDetails(@RequestParam("customerID") int customerID){
		Customer customer=null;
		try {
			customer = billingServices.getCustomerDetails(customerID);
		} catch (CustomerDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getCustomerDetailsPage","customers",e.getMessage());	
		}
		return new ModelAndView("newGetCustomerDetailsPage","customer",customer);	
	}
	@RequestMapping("/getPostPaidAccountDetails")
	public ModelAndView getPostPaidAccountDetailsPage(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo){
		PostpaidAccount postpaidAccount=null;
		try {
			postpaidAccount = billingServices.getPostPaidAccountDetails(customerID, mobileNo);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getPostPaidAccountDetailsPage","postpaidAccount",e.getMessage());	
		}
		return new ModelAndView("newGetPostPaidAccountDetailsPage","postpaidAccount",postpaidAccount);	
	}
	@RequestMapping("/changePlane")
	public ModelAndView changePlane(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo,@RequestParam("planID") int planID){
		PostpaidAccount postPaidAccount=null;
		try {
			billingServices.changePlan(customerID, mobileNo, planID);
			postPaidAccount=billingServices.getPostPaidAccountDetails(customerID, mobileNo);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | PlanDetailsNotFoundException
				| BillingServicesDownException e) {
			return new ModelAndView("changePlanPage","postPaidAccount",e.getMessage());	
		}
		return new ModelAndView("newChangePlanPage","postPaidAccount",postPaidAccount);	
	}
	@RequestMapping("/getCustomerAllPostpaidAccountsDetails")
	public ModelAndView getCustomerAllPostpaidAccountsDetails(@RequestParam("customerID") int customerID){
		List<PostpaidAccount> lists=null;
		try {
			lists = billingServices.getCustomerAllPostpaidAccountsDetails(customerID);
		} catch (CustomerDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getCustomerAllPostpaidAccountsDetailsPage","lists",e.getMessage());	
		}
		return new ModelAndView("newGetCustomerAllPostpaidAccountsDetailsPage","lists",lists);	
	}
	@RequestMapping("closeCustomerPostPaidAccount")
	public ModelAndView closeCustomerPostPaidAccountPage(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo){
		try {
			billingServices.closeCustomerPostPaidAccount(customerID, mobileNo);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("closeCustomerPostPaidAccountPage","message",e.getMessage());
		}
		String message="Customer with customer Id "+customerID+" and mobile number "+(mobileNo)+" has been closed.";
		return new ModelAndView("closeCustomerPostPaidAccountPage","message",message);
	}
	@RequestMapping("deleteCustomer")
	public ModelAndView deleteCustomerPage(@RequestParam("customerID") int customerID) {
		try {
			billingServices.deleteCustomer(customerID);
		} catch (BillingServicesDownException | CustomerDetailsNotFoundException | PostpaidAccountNotFoundException
				| BillDetailsNotFoundException e) {
			return new ModelAndView("deleteCustomerPage","message",e.getMessage());
		}
		String message="Customer Account with customer Id "+customerID+" has been deleted.";
		return new ModelAndView("deleteCustomerPage","message",message);
	}
	@RequestMapping("/getCustomerPostPaidAccountPlanDetails")
	public ModelAndView getCustomerPostPaidAccountPlanDetails(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo) {
		PostpaidAccount postpaidAccount=null;
		try {
			postpaidAccount = billingServices.getPostPaidAccountDetails(customerID, mobileNo);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getCustomerPostPaidAccountPlanDetailsPage","postpaidAccount",e.getMessage());	
		}
		return new ModelAndView("newGetCustomerPostPaidAccountPlanDetailsPage","postpaidAccount",postpaidAccount);	
	}
	@RequestMapping("/generateMonthlyMobileBill")
	public ModelAndView generateMonthlyMobileBill(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo,
			@RequestParam("billMonth") String billMonth,@RequestParam("noOfLocalSMS")  int noOfLocalSMS,@RequestParam("noOfStdSMS") int noOfStdSMS, 
			@RequestParam("noOfLocalCalls") int noOfLocalCalls,@RequestParam("noOfStdCalls")  int noOfStdCalls,@RequestParam("internetDataUsageUnits")  int internetDataUsageUnits){
		int billID=0;
		try {
			billID = billingServices.generateMonthlyMobileBill(customerID, mobileNo, billMonth, noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
				| BillingServicesDownException | PlanDetailsNotFoundException | BillDetailsNotFoundException e) {
			return new ModelAndView("generateMonthlyMobileBillPage","billID",e.getMessage());	
		}
		return new ModelAndView("generateMonthlyMobileBillPage","billID","Your Bill Id is "+billID);	
	}
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++)
		paragraph.add(new Paragraph(" "));
		}
	@RequestMapping("/newGetMobileBillDetailsPage")
	public ModelAndView getMobileBillDetailsPage(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo,@RequestParam("billMonth") String billMonth) {
		Bill bill=null;
			try {
			bill = billingServices.getMobileBillDetails(customerID, mobileNo, billMonth);
			String FILE = "E:\\billdetails1.pdf";
			Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
			Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
			Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(FILE));
				document.open();
				Paragraph preface = new Paragraph();
				addEmptyLine(preface, 1);
				preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(),
						smallBold));
				addEmptyLine(preface, 1);
				preface.add(new Paragraph("Bill Details for " + bill.getPostpaidAccount().getCustomer().getFirstName()
						+ " " + bill.getPostpaidAccount().getCustomer().getLastName() + " of the month "
						+ bill.getBillMonth(), catFont));
				addEmptyLine(preface, 1);
				document.add(preface);
				PdfPTable table = new PdfPTable(2);
				PdfPCell c1 = new PdfPCell(new Phrase("Bill ID"));
				PdfPCell c2 = new PdfPCell(new Phrase("" + bill.getBillID()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Mobile No"));
				c2 = new PdfPCell(new Phrase("" + bill.getPostpaidAccount().getMobileNo()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Bill Month"));
				c2 = new PdfPCell(new Phrase("" + bill.getBillMonth()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Local SMS Amount"));
				c2 = new PdfPCell(new Phrase("" + bill.getLocalSMSAmount()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Std SMS Amount"));
				c2 = new PdfPCell(new Phrase("" + bill.getStdSMSAmount()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Local Call Amount"));
				c2 = new PdfPCell(new Phrase("" + bill.getLocalCallAmount()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Std Call Amount"));
				c2 = new PdfPCell(new Phrase("" + bill.getStdCallAmount()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Internet Data Usage Amount"));
				c2 = new PdfPCell(new Phrase("" + bill.getInternetDataUsageAmount()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Service Tax"));
				c2 = new PdfPCell(new Phrase("" + bill.getServicesTax()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				table = new PdfPTable(2);
				c1 = new PdfPCell(new Phrase("Total Amount"));
				c2 = new PdfPCell(new Phrase("" + bill.getTotalBillAmount()));
				table.addCell(c1);
				table.addCell(c2);
				document.add(table);
				document.close();
				Process p = Runtime.getRuntime()
						.exec("rundll32 url.dll,FileProtocolHandler E:\\billdetails1.pdf");
				p.waitFor();
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
				| BillDetailsNotFoundException | BillingServicesDownException e) {
			return new ModelAndView("getMobileBillDetailsPage","bill",e.getMessage());
		}
		catch (Exception e) {
			return new ModelAndView("getMobileBillDetailsPage","bill",e.getMessage());
		}
		return new ModelAndView("newGetMobileBillDetailsPage","bill",bill);	
	}
	@RequestMapping("/newGetCustomerPostPaidAccountAllBillDetailsPage")
	public ModelAndView getCustomerPostPaidAccountAllBillDetailsPage(@RequestParam("customerID") int customerID,@RequestParam("mobileNo") long mobileNo){
		List<Bill> listOfAllBill=null;
		try {
			listOfAllBill = billingServices.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo);
		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | BillingServicesDownException
				| BillDetailsNotFoundException e) {
			return new ModelAndView("getCustomerPostPaidAccountAllBillDetailsPage","listOfAllBill",e.getMessage());	
		}		
		return new ModelAndView("newGetCustomerPostPaidAccountAllBillDetailsPage","listOfAllBill",listOfAllBill);	
	}
}