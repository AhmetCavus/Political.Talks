package com.fashiondigital.politicaltalks.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fashiondigital.politicaltalks.model.TalkModel;

@ExtendWith(MockitoExtension.class)
public class EvaluationServiceTest {

	@ParameterizedTest
	@MethodSource("testParameterProvider")
	public void evaluationService_Evaluate_ValidateMostSpeeches(MostSpeechesTestParameter testParameter) {
		// Arrange
		var talks = createDataForEvaluation();
		var sut = new EvaluationServiceImpl(testParameter.getYearOfSpeak(), StringUtils.EMPTY);
		
		// Act
		var result = sut.evaluate(talks);
		
		// Assert
		assertThat(StringUtils.compare(result.getMostSpeeches(), testParameter.getExpectedResult())).isEqualTo(0);
	}
	
	static Stream<MostSpeechesTestParameter> testParameterProvider() {
		return Stream.of(
			new MostSpeechesTestParameter(2012, "Alexander Abel"),
			new MostSpeechesTestParameter(2013, null),
			new MostSpeechesTestParameter(2014, null)
		);
	}
	
	private List<TalkModel> createDataForEvaluation() {
		return
		Arrays.asList(
			new TalkModel("Alexander Abel", "Bildungspolitik", LocalDate.parse("2012-10-30"), 5310),	
			new TalkModel("Bernhard Belling,", "Kohlesubventionen,", LocalDate.parse("2012-11-05"), 1210),	
			new TalkModel("Caesare Collins", "Kohlesubventionen", LocalDate.parse("2012-11-06"), 1119),	
			new TalkModel("Alexander Abel", "Innere Sicherheit", LocalDate.parse("2012-12-11"), 911),
			new TalkModel("Hans Zimmermann", "Innere Sicherheit", LocalDate.parse("2014-12-11"), 1411),
			new TalkModel("Julian Mauer", "Kohlesubventionen", LocalDate.parse("2014-01-11"), 1411)
		);
	}
}
