package com.iiht.StockMarket.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.StockMarket.dto.InvalidStockExceptionResponse;
import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;
import com.iiht.StockMarket.exception.InvalidStockException;
import com.iiht.StockMarket.exception.StockNotFoundException;
import com.iiht.StockMarket.services.StockMarketService;

@RestController
@RequestMapping (value = "/stock")
public class StockPriceController {

	@Autowired
	private StockMarketService stockMarketService;
	
	//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/addStock")																						// 2. WORKING
	public ResponseEntity<StockPriceDetailsDTO> addStockDetails(@Valid @RequestBody  StockPriceDetailsDTO stockPriceDetailsDTO,
             BindingResult bindingResult) {
       // System.out.println("stock Details received " + stockPriceDetailsDTO.getCompanyCode());
       //  System.out.println("stock Details received " + stockPriceDetailsDTO.getCurrentStockPrice());
       //   System.out.println("stock Details received " + stockPriceDetailsDTO.getStockPriceDate());
        //   System.out.println("stock Details received " + stockPriceDetailsDTO.getStockPriceTime());
        // updated remved !
        // List<ObjectError> objerror = bindingResult.getAllErrors();
        //         for(int i=0;i<objerror.size();i++)
        //         {
        //             System.out.println(objerror.get(i).toString());
        //         }
       
        if (bindingResult.hasErrors())
            throw new InvalidStockException("Invalid Stock Details!!!");

            StockPriceDetailsDTO stockPriceDetailsDTO2 = stockMarketService.saveStockPriceDetails(stockPriceDetailsDTO);
        
            if(stockPriceDetailsDTO2 !=null )
            {
            return new ResponseEntity<StockPriceDetailsDTO>(
                    stockPriceDetailsDTO2, HttpStatus.OK);
            }
            else
            {
             throw new InvalidStockException("You can't add stock details."); 
    }
} 

    // -------------------------------------------------------------------------------------------------------------------------------
    @DeleteMapping(value = "/deleteStock/{companyCode}") // 3. WORKING
    public ResponseEntity<List<StockPriceDetailsDTO>> deleteStockByCompanyCode(@PathVariable final Long companyCode) {
       // System.out.println("<<<<<<<<<Gathered Company code is >>>>>>>>>>" + companyCode);
        // if(companyInfoService.getCompanyInfoById(companyCode) == null)
          List<StockPriceDetailsDTO> stockPriceDetailsDTO = stockMarketService.deleteStock(companyCode);
        if (stockPriceDetailsDTO.size() == 0)
            throw new StockNotFoundException("Invalid Company Code!!- "+companyCode+" Please enter valid companyCode...");
        else
            return new ResponseEntity<List<StockPriceDetailsDTO>>(stockPriceDetailsDTO, HttpStatus.OK);

    }

    // -------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/getStockByCompanyCode/{companyCode}") // 4. WORKING
    public ResponseEntity<List<StockPriceDetailsDTO>> getStockByCompanyCode(@PathVariable final Long companyCode) {
      //  System.out.println("<<<<<<<<<Gathered Company code is >>>>>>>>>>"+companyCode);
         final List<StockPriceDetailsDTO> stockPriceDetailsDTO = stockMarketService.getStockByCode(companyCode);
        if(stockPriceDetailsDTO ==null || stockPriceDetailsDTO.size()==0)
        throw new StockNotFoundException("Invalid Company Code!!- "+companyCode+" Please enter valid companyCode...");
       
	else
            return new ResponseEntity<List<StockPriceDetailsDTO>>(stockPriceDetailsDTO, HttpStatus.OK);


    }

    // -------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/getStockPriceIndex/{companyCode}/{startDate}/{endDate}") // 5. WORKING
    public ResponseEntity<StockPriceIndexDTO> displayStockPriceIndex(@PathVariable final Long companyCode,
            @PathVariable final Date startDate, @PathVariable final Date endDate) {

        LocalDate start= Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
       LocalDate end= Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

     StockPriceIndexDTO stockPriceIndexDTO =  stockMarketService.getStockPriceIndex(companyCode, start, end);
     if(stockPriceIndexDTO != null)
     {
        return new ResponseEntity<StockPriceIndexDTO>(stockPriceIndexDTO,HttpStatus.OK);
        
    }
    else
    {
          throw new StockNotFoundException("Invalid Company Code!!- "+companyCode+" Please enter valid companyCode...");
    }
            }
    // ===============================================================================================================================
    // UTITLITY EXCEPTION HANDLERS - 2
    // ===============================================================================================================================
    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity<InvalidStockExceptionResponse> companyHandler(final InvalidStockException ex) {
        final InvalidStockExceptionResponse resp = new InvalidStockExceptionResponse(ex.getMessage(),
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
        final ResponseEntity<InvalidStockExceptionResponse> response = new ResponseEntity<InvalidStockExceptionResponse>(
                resp, HttpStatus.BAD_REQUEST);
        return response;
    }

    // ------------------------------------------------------------------------------------------------
    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<InvalidStockExceptionResponse> companyHandler(final StockNotFoundException ex) {
        final InvalidStockExceptionResponse resp = new InvalidStockExceptionResponse(ex.getMessage(),
                System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
        final ResponseEntity<InvalidStockExceptionResponse> response = new ResponseEntity<InvalidStockExceptionResponse>(
                resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}