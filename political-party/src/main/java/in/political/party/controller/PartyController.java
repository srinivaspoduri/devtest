package in.political.party.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.political.party.dto.PartyDto;
import in.political.party.exceptions.InvalidDataException;
import in.political.party.exceptions.LeaderIdNotFoundException;
import in.political.party.exceptions.PartyNotFoundException;
import in.political.party.service.PartyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/politics/api/v1/party")
public class PartyController {

	@Autowired
	private PartyService partyService;

	@PostMapping("/register-party")
	public ResponseEntity<PartyDto> createParty(@Valid @RequestBody PartyDto politicalPartyDto, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())
         {
           
           throw new InvalidDataException("Invalid data Recived to register new Party");
       }
		PartyDto registerParty = partyService.registerParty(politicalPartyDto);
		return new ResponseEntity<PartyDto>(registerParty, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{leaderId}")
	public ResponseEntity<String> deletePartyLeader(@PathVariable("leaderId")  Long leaderId) {
		
	 PartyDto leaderPartyDetails = partyService.getLeaderPartyDetails(leaderId);
		if(leaderPartyDetails!=null)
		{
			boolean deleteLeader = partyService.deleteLeader(leaderId);
			if(deleteLeader)
			return new ResponseEntity<String>("Leader Deleted Successfully", HttpStatus.OK);
			else
				
				throw new LeaderIdNotFoundException(" leader Code Does not Exists.." + leaderId);
		}
		else
			throw new PartyNotFoundException("Party not present to delete the leader "+leaderId);
						
	}

	
	
	

}
