package com.fashiondigital.politicaltalks.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;

import lombok.RequiredArgsConstructor;

@Service
public class EvaluationServiceImpl implements EvaluationService {
	
	final int queryYear;
	final String querySubject;
	
	public EvaluationServiceImpl(@Qualifier("queryYear") int queryYear, @Qualifier("querySubject") String querySubject) {
		this.queryYear = queryYear;
		this.querySubject = querySubject;
	}
	
	public EvaluationModel evaluate(List<TalkModel> talks) {
		var mostSpeeches = queryForMostSpeeches(talks);
		var mostSecurity = queryForMostSecurity(talks);
		var leastWordy = queryForLeastWordy(talks);
		
		return new EvaluationModel(mostSpeeches, mostSecurity, leastWordy);
	}
	
	String queryForMostSpeeches(List<TalkModel> talks) {
		return query(talks, talk -> talk.getLocalDate().getYear() == queryYear);
	}
	
	String queryForMostSecurity(List<TalkModel> talks) {
		return query(talks, talk -> StringUtils.equals(talk.getSubject().strip(), querySubject));
	}
	
	String queryForLeastWordy(List<TalkModel> talks) {
		return
			talks
				.stream()
				.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.summarizingInt(talk -> talk.getCountOfWords())))
				.entrySet()
				.stream()
				.min(Comparator.comparingLong(entry -> entry.getValue().getSum()))
				.map(Map.Entry::getKey)
				.orElse(null);
	}
	
	private String query(List<TalkModel> talks, Predicate<? super TalkModel> filterQuery) {
		return
			talks
			.stream()
			.filter(filterQuery)
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.counting()))
			.entrySet()
			.stream()
			.max(Comparator.comparingInt(entry -> entry.getValue().intValue()))
			.map(Map.Entry::getKey)
			.orElse(null);
	}
}