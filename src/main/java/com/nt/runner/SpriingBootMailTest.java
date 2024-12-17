package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.service.IPurchaseMgmtService;

@Component
public class SpriingBootMailTest implements CommandLineRunner {

	@Autowired
	private IPurchaseMgmtService service;
	
	@Override
	public void run(String... args) throws Exception {
		
		try 
		{
			String resultMsg = service.shopping(new String[] {"shirt","kurti","sarri"},
					                            new Double[] {4000.0, 5000.0, 3000.0}, 
					                            new String[] {"aachaltiwari9323@gmail.com","tanmaym0444@gmail.com"});
					System.out.println(resultMsg);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}//method

}//class
