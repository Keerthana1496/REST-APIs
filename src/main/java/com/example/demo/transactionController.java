package com.example.demo;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.*;
import java.text.SimpleDateFormat;
import java.util.Date; 
import java.text.DateFormat;
import java.text.ParseException;


@RestController
public class transactionController {
	
	@RequestMapping(value="/transaction",consumes= MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE, method =RequestMethod.POST )
	public ResponseEntity<transaction> TransactionMethod(@RequestBody transaction transact) throws ParseException
	{
		Date date=new Date();
		SimpleDateFormat myFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String userdate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (transact.getTime()));
		String currentdate=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
		Date date1 = myFormat.parse(userdate);
		Date date2 = myFormat.parse(currentdate);
		long diff = date2.getTime() - date1.getTime();
		int seconds = (int) diff / 1000;
		if(seconds<60)
			return new ResponseEntity<transaction>(transact, HttpStatus.CREATED);
		else
			return new ResponseEntity<transaction>(transact, HttpStatus.NO_CONTENT) ;
	}
}
