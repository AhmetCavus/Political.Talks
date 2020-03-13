package com.fashiondigital.politicaltalks.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.TypeMap;
import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.factory.TestDataFactory;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@ExtendWith(MockitoExtension.class)
public class TalksServiceTest {

	String testCsvPath = "talks_01.csv";
	String testRawData = ",,,";
	String testUrl = "https://political.talks.com";
	
	@Mock
	private RestService restServiceMock;
	
	@Mock
	private CsvService<TalkDto> csvServiceMock;
	
	@Mock
	private TypeMap<TalkDto, TalkModel> mapperMock;
	
	@InjectMocks
	TalksServiceImpl sut;
	
	@Test
	public void talksService_Request_EqualsTalkDtos() throws IOException, CsvRequiredFieldEmptyException, InterruptedException, ExecutionException {
		
		// Arrange
		var expectedResult = TestDataFactory.createDtoDataForEvaluation();
		when(restServiceMock.requestRawString(testUrl)).thenReturn(testRawData);
		when(csvServiceMock.readCsv(testCsvPath)).thenReturn(expectedResult);
		when(csvServiceMock.writeCsv("tmp", testRawData)).thenReturn(testCsvPath);
		
		// Act
		var result = sut.request(testUrl);
		
		// Assert
		assertEquals(expectedResult, result.get());
	}
	
	@Test
	public void talksService_ConvertToModels_NotNull() {
		
		// Arrange
		var talkDto = new TalkDto();
		var talkDtos = Arrays.asList(talkDto);
		var expectedResult = new TalkModel();
		
		when(mapperMock.map(talkDto)).thenReturn(expectedResult);
		
		// Act
		var result = sut.convertToModels(talkDtos);
		
		// Assert
		assertThat(result).isNotEmpty();
	}
	
}