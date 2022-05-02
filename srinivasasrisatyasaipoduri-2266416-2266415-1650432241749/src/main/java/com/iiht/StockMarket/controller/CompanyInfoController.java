package com.iiht.StockMarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.dto.InvalidCompanyExceptionResponse;
import com.iiht.StockMarket.exception.CompanyNotFoundException;
import com.iiht.StockMarket.exception.InvalidCompanyException;
import com.iiht.StockMarket.services.CompanyInfoService;

@RestController
@RequestMapping (value = "/company")
public class CompanyInfoController {

	@Autowired
	private CompanyInfoService companyInfoService;

   	//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(value="/addCompany")																					// 3. WORKING
	public ResponseEntity<CompanyDetailsDTO> addCompanyDetails( @Valid @RequestBody  CompanyDetailsDTO companyDetailsDTO,
             BindingResult bindingResult) throws InvalidCompanyException {
        String message="";
        boolean flag=true;
        // updated remved !
        if (bindingResult.hasErrors())
          {
            for(FieldError err : bindingResult.getFieldErrors())
            {
                if(flag)
                {
                    message=err.getDefaultMessage();
                    flag=false;
                }
                else
                {
                    message=message+ " || "+err.getDefaultMessage();
                }
            }
            throw new InvalidCompanyException(message);
        }

         if(companyInfoService.getCompanyInfoById(companyDetailsDTO.getCompanyCode()) !=null)
            {
                throw new InvalidCompanyException("company code already exists " + companyDetailsDTO.getCompanyCode());
            }
            

                 return new ResponseEntity<CompanyDetailsDTO>(companyInfoService.saveCompanyDetails(companyDetailsDTO),
                    HttpStatus.OK);
    }

    // -------------------------------------------------------------------------------------------------------------------------------
    @DeleteMapping(value = "/deleteCompany/{companyCode}") // 4. WORKING
    // public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@RequestParam("companyCode") Long companyCode)
    public ResponseEntity<CompanyDetailsDTO> deleteCompanyDetails(@PathVariable("companyCode")  Long companyCode)

    {
            // System.out.println("<<<<<<<<<Gathered Company code is >>>>>>>>>>" + companyCode);
             CompanyDetailsDTO companyDetailsDTO = companyInfoService.deleteCompany(companyCode);
        if (companyDetailsDTO == null)
            throw new CompanyNotFoundException("Invalid Company Code!!- "+companyCode+" Please enter valid companyCode...");
        else
            return new ResponseEntity<CompanyDetailsDTO>(companyDetailsDTO, HttpStatus.OK);
    }

    // -------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/getCompanyInfoById/{companyCode}")															// 5. WORKING
	public ResponseEntity<CompanyDetailsDTO> getCompanyDetailsById(@PathVariable("companyCode")  long companyCode) {
       
        final CompanyDetailsDTO companyDetailsDTO = companyInfoService.getCompanyInfoById(companyCode);
        if(companyDetailsDTO == null)
       throw new CompanyNotFoundException("Invalid Company Code!!- "+companyCode+" Please enter valid companyCode...");
       
		else	
			return new ResponseEntity<CompanyDetailsDTO>(companyDetailsDTO, HttpStatus.OK);
	}

    // -------------------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/getAllCompanies", produces = "application/json") // 6. WORKING
    public ResponseEntity<List<CompanyDetailsDTO>> getAllCompanies() {
        return new ResponseEntity<List<CompanyDetailsDTO>>(companyInfoService.getAllCompanies(), HttpStatus.OK);
    }

    // ================================================================================================
    // UTITLITY EXCEPTION HANDLERS - 2
    // ================================================================================================
    @ExceptionHandler(InvalidCompanyException.class)
    public ResponseEntity<InvalidCompanyExceptionResponse> companyHandler( InvalidCompanyException ex) {
        final InvalidCompanyExceptionResponse resp = new InvalidCompanyExceptionResponse(ex.getMessage(),
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
        final ResponseEntity<InvalidCompanyExceptionResponse> response = new ResponseEntity<InvalidCompanyExceptionResponse>(
                resp, HttpStatus.BAD_REQUEST);
        return response;
    }

    // ------------------------------------------------------------------------------------------------
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<InvalidCompanyExceptionResponse> companyHandler(final CompanyNotFoundException ex) {
        final InvalidCompanyExceptionResponse resp = new InvalidCompanyExceptionResponse(ex.getMessage(),
                System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
        final ResponseEntity<InvalidCompanyExceptionResponse> response = new ResponseEntity<InvalidCompanyExceptionResponse>(
                resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}