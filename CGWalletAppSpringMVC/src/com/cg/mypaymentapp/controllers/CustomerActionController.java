package com.cg.mypaymentapp.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;

import javassist.bytecode.Descriptor.Iterator;

@Controller
public class CustomerActionController {
	@Autowired	
	WalletService ser;
	
	@RequestMapping(value="/registerCustomer")
	public ModelAndView registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result )
	{
		if(result.hasErrors())return new ModelAndView("signin");
			try {
				customer=ser.createAccount(customer);
				return new ModelAndView("welcomepage","customer",customer);
			} catch (InvalidInputException e) {
				
				return new ModelAndView("signin","errorMessage",e.getMessage());
			}			
			
	}
	@RequestMapping(value="/viewwallet")
	public ModelAndView viewCustomer(@RequestParam("mobileNo") String mobileNo )
	{
		Customer customer;
		try {
			customer = ser.showBalance(mobileNo);
			return new ModelAndView("viewwallet","customer",customer);
		} catch (InvalidInputException e) {
			return new ModelAndView("login","errorMessage",e.getMessage());
		}
		
	}
	@RequestMapping(value="/deposit")
	public String getdeposit()
	{
		return "deposit";
	}
	
	@RequestMapping(value="/depositmoney", method = RequestMethod.GET)
	public ModelAndView deposit(HttpServletRequest request)
	{
		Customer customer = null;
		String mob=request.getParameter("mobileNo");
		String bal=request.getParameter("balance");
		
		try {
			customer=ser.depositAmount(mob, new BigDecimal(bal));
		} catch (InvalidInputException e) {
			
			e.printStackTrace();
		}
		
		return new ModelAndView("depositsucess","customer",customer);		
	}
	@RequestMapping(value="/withdraw")
	public String getwithdraw()
	{
		return "withdraw";		
	}
	
	@RequestMapping(value="/withdrawmoney", method = RequestMethod.GET)
	public ModelAndView draw(HttpServletRequest request)
	{
		Customer customer = null;
		String mob=request.getParameter("mobileNo");
		String bal=request.getParameter("balance");
		
		
			try {
				customer=ser.withdrawAmount(mob, new BigDecimal(bal));
			} catch (InvalidInputException | InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return new ModelAndView("withdrawsucess","customer",customer);		
	}
	
	@RequestMapping(value="/fundtransfer")
	public String Funds()
	{
		return "fundtransfer";
	}
	
	@RequestMapping(value="/fundtransfered", method = RequestMethod.GET)
	public ModelAndView fundTransfer(HttpServletRequest request)
	{
		Customer customer = null;
		String source=request.getParameter("sourceMobileNo");
		String target=request.getParameter("destinationMobileNo");
		String amount=request.getParameter("balance");
		
		try {
			customer=ser.fundTransfer(source, target, new BigDecimal(amount));
		} catch (InvalidInputException | InsufficientBalanceException e) {
			
			e.printStackTrace();
		}
		return new ModelAndView("fundstransfered","customer",customer);		
	}
	
	
	
	@RequestMapping(value="/listtransactions/{mobileNo}", method = RequestMethod.GET)
	public ModelAndView listTransactions(@PathVariable String mobileNo)
	{
		List<Transactions> li=new ArrayList<Transactions>();
		li=ser.getTransaction(mobileNo);
		
		return new ModelAndView("transactionslist","transactions1",li);
	}
	
	@RequestMapping(value="/getcustomers")
	public ModelAndView getcustomers()
	{
		List<Transactions> li=new ArrayList<Transactions>();
		li=ser.getcustomers();
		
		return new ModelAndView("allcustomers","customers",li);
	}
	
	@RequestMapping(value="/getcustomer")
	public String getCust()
	{
		return "getcustomer";
	}
	
	@RequestMapping(value="/getcust", method = RequestMethod.GET )
	public ModelAndView getcustomer()
	{
		//List<Transactions> li=new ArrayList<Transactions>();
		BigDecimal bl=new BigDecimal("10000");
		List li=ser.getcustomer(bl);
				
		
		return new ModelAndView("somecustomers","customers",li);
	}
	
	
}
