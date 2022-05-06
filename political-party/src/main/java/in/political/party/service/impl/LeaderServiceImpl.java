package in.political.party.service.impl;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.political.party.Repository.LeaderRepository;
import in.political.party.Repository.PartyRepository;
import in.political.party.dto.LeaderDto;
import in.political.party.entity.Leader;
import in.political.party.entity.Party;
import in.political.party.service.LeaderService;



@Service
public class LeaderServiceImpl implements LeaderService {

	@Autowired
	private LeaderRepository leaderrepository;

	@Autowired
	private PartyRepository partyrepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LeaderDto registerPoliticalLeader(LeaderDto leaderDto) {
		Leader politicalLeader = convertToEntity(leaderDto);
		Long politicalPartyId = politicalLeader.getPoliticalParty().getPoliticalPartyId();
		Optional<Party> findById = partyrepository.findById(politicalPartyId);
		if(findById.isPresent())
		{
			Leader leader = leaderrepository.save(politicalLeader);
			return convertToDto(leader);
		}
		else
			return null;
	}


	private LeaderDto convertToDto(Leader politicalLeader) {
		LeaderDto politicalLeaderDto = modelMapper.map(politicalLeader, LeaderDto.class);

		return politicalLeaderDto;
	}

	private Leader convertToEntity(LeaderDto postDto)  {
		Leader politicalLeader = modelMapper.map(postDto, Leader.class);
		return politicalLeader;
	}




}
