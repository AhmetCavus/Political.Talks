package com.fashiondigital.politicaltalks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.TypeMap;
import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@ExtendWith(MockitoExtension.class)
public class TalksServiceTest {

	String testCsvPath = "talks_01.csv";
	
	String testUrl = "https://political.talks.com";
	
	@Mock
	private RestService restServiceMock;
	
	@Mock
	private CsvService<TalkDto> csvServiceMock;
	
	@Mock
	private TypeMap<TalkDto, TalkModel> mapperMock;
	
	@InjectMocks
	private TalksService talksService;
	
	@Test
	public void talksService_Request_ReturnsTalksDto() throws IOException, CsvRequiredFieldEmptyException {
		
		// Arrange
		var expectedResult = createDataForEvaluation();
		when(restServiceMock.requestRawString(testUrl)).thenReturn(testCsvPath);
		when(csvServiceMock.readCsv(testCsvPath)).thenReturn(expectedResult);
		
		var sut = new TalksServiceImpl(restServiceMock, csvServiceMock, mapperMock);
		
		// Act
		var future = sut.request(testUrl);
		future.thenAcceptAsync(talksDto -> {
			// Assert
			assertEquals(expectedResult, talksDto);
		});
	}
	
	private List<TalkDto> createDataForEvaluation() {
		return
		Arrays.asList(
			new TalkDto("Alexander Abel", "Bildungspolitik", "2012-10-30", 5310),	
			new TalkDto("Bernhard Belling,", "Kohlesubventionen,", "2012-11-05", 1210),	
			new TalkDto("Caesare Collins", "Kohlesubventionen", "2012-11-06", 1119),	
			new TalkDto("Alexander Abel", "Innere Sicherheit", "2012-12-11", 911),
			new TalkDto("Max Mustermann", "Innere Sicherheit", "2014-12-11", 10),
			new TalkDto("Julian Mauer", "Kohlesubventionen", "2014-01-11", 501),
			new TalkDto("Max Mustermann", "Kohlesubventionen", "2015-01-04", 20),
			new TalkDto("Julian Mauer", "Finanzwesen", "2015-01-11", 120),
			new TalkDto("Max Mustermann", "Kohlesubventionen", "2016-01-09", 30)
		);
	}
}