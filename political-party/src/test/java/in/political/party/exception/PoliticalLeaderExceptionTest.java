package in.political.party.exception;

import static in.political.party.testutils.TestUtils.currentTest;
import static in.political.party.testutils.TestUtils.exceptionTestFile;
import static in.political.party.testutils.TestUtils.testAssert;
import static in.political.party.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import in.political.party.controller.LeaderController;
import in.political.party.dto.LeaderDto;
import in.political.party.service.LeaderService;
import in.political.party.testutils.MasterData;

@WebMvcTest(LeaderController.class)
@AutoConfigureMockMvc
public class PoliticalLeaderExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LeaderService leaderService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalLeaderInvalidDataException() throws Exception {
		LeaderDto politicalLeaderDto = MasterData.getLeaderDto();
		LeaderDto savedPoliticalLeaderDto = MasterData.getLeaderDto();
		savedPoliticalLeaderDto.setPoliticalPartyId(1L);

		politicalLeaderDto.setCandidateName("Ab");

		when(this.leaderService.registerPoliticalLeader(politicalLeaderDto)).thenReturn(savedPoliticalLeaderDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/leader/register-leader")
				.content(MasterData.asJsonString(politicalLeaderDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	

}
