package com.fashiondigital.politicaltalks.factory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.model.TalkModel;

public class TestDataFactory {

	public static List<TalkModel> createModelDataForEvaluation() {
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
	
	public static List<TalkDto> createDtoDataForEvaluation() {
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
