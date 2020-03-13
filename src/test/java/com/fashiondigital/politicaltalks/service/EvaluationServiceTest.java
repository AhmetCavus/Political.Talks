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

import com.fashiondigital.politicaltalks.factory.TestDataFactory;
import com.fashiondigital.politicaltalks.model.TalkModel;

@ExtendWith(MockitoExtension.class)
public class EvaluationServiceTest {

	List<TalkModel> talks;
	
	@BeforeEach
	public void Setup() {
		talks = TestDataFactory.createModelDataForEvaluation();
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
	
	
}