package com.iiht.StockMarket.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

public class CompanyDetailsDTO {
    //updated--
 @NotNull(message = "Company Code should'nt be null")
@Column(unique = true)
	private Long companyCode;

    @NotNull
	@NotBlank(message = "StockExchange Name should'nt be blank.")
	@Length(min = 3, max = 100,message = "StockExchange name should be minimun 3 charecters and maximum 100 characters")
	private String stockExchange;

	@NotBlank(message = "Company Name should'nt be blank.")
	@Length(min = 3, max = 100,message = "Company name should be minimun 3 charecters and maximum 100 characters")
	private String companyName;

	 @NotBlank(message = "Company CEO Name should'nt be blank.")
	@Length(min = 5, max = 100,message = "Company CEO name should be minimun 5 charecters and maximum 100 characters")
	private String companyCEO;

	 @NotNull(message = "Please enter company turnover")
    @Column(precision = 10, scale = 2)
	private Double turnover;

	@NotEmpty(message = "Please enter BoardOfDirectors")
	@Length(min = 5, max = 200,message = "BoardOfDirectors should be minimun 5 charecters and maximum 200 characters")
	private String boardOfDirectors;

	@NotEmpty(message = "Please enter company profile")
	@Length(min = 5, max = 255,message = "Company profile should be minimun 5 charecters and maximum 255 characters")
	private String companyProfile;
	
	//---------------------------------------------------------------------------------------------------------------------------------
	public CompanyDetailsDTO() {
		super();
	}
	public CompanyDetailsDTO(Long companyCode, String stockExchange, String companyName, String companyCEO, Double turnover, String boardOfDirectors, String companyProfile) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.turnover = turnover;
		this.boardOfDirectors = boardOfDirectors;
		this.companyProfile = companyProfile;
	}

	//---------------------------------------------------------------------------------------------------------------------------------
	public Long getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public Double getTurnover() {
		return turnover;
	}
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}
	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyProfile() {
		return companyProfile;
	}
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardOfDirectors == null) ? 0 : boardOfDirectors.hashCode());
		result = prime * result + ((companyCEO == null) ? 0 : companyCEO.hashCode());
		result = prime * result + ((companyCode == null) ? 0 : companyCode.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyProfile == null) ? 0 : companyProfile.hashCode());
		result = prime * result + ((stockExchange == null) ? 0 : stockExchange.hashCode());
		result = prime * result + ((turnover == null) ? 0 : turnover.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDetailsDTO other = (CompanyDetailsDTO) obj;
		if (boardOfDirectors == null) {
			if (other.boardOfDirectors != null)
				return false;
		} else if (!boardOfDirectors.equals(other.boardOfDirectors))
			return false;
		if (companyCEO == null) {
			if (other.companyCEO != null)
				return false;
		} else if (!companyCEO.equals(other.companyCEO))
			return false;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyProfile == null) {
			if (other.companyProfile != null)
				return false;
		} else if (!companyProfile.equals(other.companyProfile))
			return false;
		if (stockExchange == null) {
			if (other.stockExchange != null)
				return false;
		} else if (!stockExchange.equals(other.stockExchange))
			return false;
		if (turnover == null) {
			if (other.turnover != null)
				return false;
		} else if (!turnover.equals(other.turnover))
			return false;
		return true;
    }
    


}