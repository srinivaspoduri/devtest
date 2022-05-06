package in.political.party.service.impl;


import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.political.party.Repository.LeaderRepository;
import in.political.party.Repository.PartyRepository;
import in.political.party.dto.PartyDto;
import in.political.party.entity.Leader;
import in.political.party.entity.Party;
import in.political.party.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {

	@Autowired
	private PartyRepository politicalPartyRepository;
	
	@Autowired
	private LeaderRepository politicalLeaderRepository;

@Autowired
private ModelMapper modelMapper;

	@Override
	public PartyDto registerParty(PartyDto politicalPartyDto) {
		Party politicalParty = convertToEntity(politicalPartyDto);
		Party party = politicalPartyRepository.save(politicalParty);
		return convertToDto(party);
	}

	@Override
	public boolean deleteLeader(Long leaderId) {
		// TODO Auto-generated method stub
		
		Optional<Leader> politicalLeader= politicalLeaderRepository.findById(leaderId);
		if(politicalLeader.isPresent())
		{
		 politicalLeaderRepository.deleteById(leaderId);
		return true;
		}
		else
		{
			return false;
		}
	}

	public PartyDto getLeaderPartyDetails(Long leaderId)
	{
		
		List<Leader> partyusingLeaderId = politicalLeaderRepository.findByPoliticalLeaderId(leaderId);
		if(!partyusingLeaderId.isEmpty())
		
		return convertToDto(partyusingLeaderId.get(0).getPoliticalParty());
		else
			return null;
	}

	private PartyDto convertToDto(Party politicalparty) {
		PartyDto politicalpartyDto = modelMapper.map(politicalparty, PartyDto.class);

		return politicalpartyDto;
	}

	private Party convertToEntity(PartyDto postDto)  {
		Party politicalparty = modelMapper.map(postDto, Party.class);
		return politicalparty;
	}

}
