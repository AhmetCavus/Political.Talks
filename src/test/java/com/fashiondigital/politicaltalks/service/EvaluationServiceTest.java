package com.fashiondigital.politicaltalks.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fashiondigital.politicaltalks.model.TalkModel;

@ExtendWith(MockitoExtension.class)
public class EvaluationServiceTest {

	List<TalkModel> talks;
	
	@BeforeEach
	public void Setup() {
		talks = createDataForEvaluation();
	}
	
	@ParameterizedTest
	@MethodSource("testParameterMostSpeechesProvider")
	public void evaluationService_Evaluate_ValidateMostSpeeches(MostSpeechesTestParameter testParameter) {
		// Arrange
		var sut = new EvaluationServiceImpl(testParameter.getYearOfSpeak(), StringUtils.EMPTY);
		
		// Act
		var result = sut.evaluate(talks);
		
		// Assert
		assertThat(StringUtils.compare(result.getMostSpeeches(), testParameter.getExpectedResult())).isEqualTo(0);
	}
	
	@ParameterizedTest
	@MethodSource("testParameterMostSecurityProvider")
	public void evaluationService_Evaluate_ValidateMostSecurity(MostSecurityTestParameter testParameter) {
		// Arrange
		var sut = new EvaluationServiceImpl(0, testParameter.getSubject());
		
		// Act
		var result = sut.evaluate(talks);
		
		// Assert
		assertThat(StringUtils.compare(result.getMostSecurity(), testParameter.getExpectedResult())).isEqualTo(0);
	}
	
	@Test
	public void evaluationService_Evaluate_ValidateLeastWordy() {
		// Arrange
		var sut = new EvaluationServiceImpl(0, StringUtils.EMPTY);
		
		// Act
		var result = sut.evaluate(talks);
		
		// Assert
		assertThat(StringUtils.compare(result.getLeastWordy(), "Max Mustermann")).isEqualTo(0);
	}
	
	static Stream<MostSpeechesTestParameter> testParameterMostSpeechesProvider() {
		return Stream.of(
			// > 1 speech case
			new MostSpeechesTestParameter(2012, "Alexander Abel"),
			
			// No speeches case
			new MostSpeechesTestParameter(2013, null),
			
			// Equal speeches case
			new MostSpeechesTestParameter(2015, null),
			
			// Only one speech case
			new MostSpeechesTestParameter(2016, "Max Mustermann")
		);
	}
	
	static Stream<MostSecurityTestParameter> testParameterMostSecurityProvider() {
		return Stream.of(
			// > 1 speech case
			new MostSecurityTestParameter("Kohlesubventionen", "Max Mustermann"),
			
			// No speeches case
			new MostSecurityTestParameter("Gesundheitswesen", null),
			
			// Equal speeches case
			new MostSecurityTestParameter("Innere Sicherheit", null),
			
			// Only one speech case
			new MostSecurityTestParameter("Bildungspolitik", "Alexander Abel")
		);
	}
	
	private List<TalkModel> createDataForEvaluation() {
		return
		Arrays.asList(
			new TalkModel("Alexander Abel", "Bildungspolitik", LocalDate.parse("2012-10-30"), 5310),	
			new TalkModel("Bernhard Belling,", "Kohlesubventionen,", LocalDate.parse("2012-11-05"), 1210),	
			new TalkModel("Caesare Collins", "Kohlesubventionen", LocalDate.parse("2012-11-06"), 1119),	
			new TalkModel("Alexander Abel", "Innere Sicherheit", LocalDate.parse("2012-12-11"), 911),
			new TalkModel("Max Mustermann", "Innere Sicherheit", LocalDate.parse("2014-12-11"), 10),
			new TalkModel("Julian Mauer", "Kohlesubventionen", LocalDate.parse("2014-01-11"), 501),
			new TalkModel("Max Mustermann", "Kohlesubventionen", LocalDate.parse("2015-01-04"), 20),
			new TalkModel("Julian Mauer", "Finanzwesen", LocalDate.parse("2015-01-11"), 120),
			new TalkModel("Max Mustermann", "Kohlesubventionen", LocalDate.parse("2016-01-09"), 30)
		);
	}
}