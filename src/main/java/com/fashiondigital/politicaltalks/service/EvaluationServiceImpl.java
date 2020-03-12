package com.fashiondigital.politicaltalks.service;

import java.util.Collections;
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
		var groupedEntries =
			talks
			.stream()
			.filter(talk -> talk.getLocalDate().getYear() == queryYear)
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.counting()));
		String result = null;
		if(!groupedEntries.isEmpty()) {
			var maxValue = Collections.max(groupedEntries.values());
			var occurences = groupedEntries.values().stream().filter(count -> count >= maxValue).count();
			if(occurences == 1) {
				result = groupedEntries.keySet().iterator().next();
			}
		}
		return result;
	}
	
	String queryForMostSecurity(List<TalkModel> talks) {
		var groupedEntries =
			talks
			.stream()
			.filter(talk -> StringUtils.equals(talk.getSubject().strip(), querySubject))
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.counting()));
		String result = null;
		if(!groupedEntries.isEmpty()) {
			var maxValue = Collections.max(groupedEntries.values());
			var occurences = groupedEntries.values().stream().filter(count -> count >= maxValue).count();
			if(occurences == 1) {
				result = groupedEntries.keySet().iterator().next();
			}
		}
		return result;
	}
	
	String queryForLeastWordy(List<TalkModel> talks) {
		var groupedEntries =
			talks
			.stream()
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.summarizingInt(talk -> talk.getCountOfWords())));
		String result = null;
		if(!groupedEntries.isEmpty()) {
			var minValue = 
				groupedEntries
				.values()
				.stream()
				.min(Comparator.comparingLong(stats -> stats.getSum()));
			var occurences = groupedEntries.values().stream().filter(count -> count == minValue).count();
			if(occurences == 1) {
				result = groupedEntries.keySet().iterator().next();
			}
		}
		return result;
	}
}