package com.iiht.StockMarket.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;


@Transactional
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	@Autowired
	private CompanyInfoRepository repository; 
	
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
		        
         CompanyDetails companyDetails=StockMarketUtility.convertToCompanyDetails(companyDetailsDTO);
         if(companyDetails !=null) 
        return StockMarketUtility.convertToCompanyDetailsDTO(repository.save(companyDetails));
        else
        return null;
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO deleteCompany(Long companyCode) {
			        
        Integer value;
        CompanyDetailsDTO companyDetails=getCompanyInfoById(companyCode);
        if(companyDetails == null)
             return null;
        else
          value= repository.deleteByCompanyCode(companyCode);
                if(value!=null)
              return companyDetails;
                else
    		    return null;
	};
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		
	       
        CompanyDetails companyDetails=repository.findById(companyCode).orElse(null);
       // System.out.println("**********Compnay details for******* "+ companyCode + " " +companyDetails);
        if(companyDetails!=null)
            return StockMarketUtility.convertToCompanyDetailsDTO(companyDetails);
		return null;
	};
	
	//----------------------------------------------------------------------------
	public List<CompanyDetailsDTO> getAllCompanies() {

		List<CompanyDetails> companyInfo = repository.findAll();
        //1787342 -removed ! as it is throwing null allways.
		if(CollectionUtils.isEmpty(companyInfo))
			return null;
		else
            return companyInfo.stream().map(StockMarketUtility::convertToCompanyDetailsDTO).collect(Collectors.toList());
          
	};
}