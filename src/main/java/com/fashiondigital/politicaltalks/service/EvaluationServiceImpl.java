package com.fashiondigital.politicaltalks.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;

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
		var mostSpeechesEntry = 
			groupedEntries
			.entrySet()
			.stream()
			.max(Comparator.comparingLong(entry -> entry.getValue()))
			.orElse(null);
		if(mostSpeechesEntry == null) return null;
		var occurences = groupedEntries.values().stream().filter(count -> count == mostSpeechesEntry.getValue()).count();
		String result = null;
		if(occurences == 1) {
			result = mostSpeechesEntry.getKey();
		}
		return result;
	}
	
	String queryForMostSecurity(List<TalkModel> talks) {
		var groupedEntries =
			talks
			.stream()
			.filter(talk -> StringUtils.equals(talk.getSubject().strip(), querySubject))
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.counting()));
		if(groupedEntries.isEmpty()) return null;
		var mostSecurityEntry = 
			groupedEntries
			.entrySet()
			.stream()
			.max(Comparator.comparingLong(entry -> entry.getValue()))
			.orElse(null);
		if(mostSecurityEntry == null) return null;
		var occurences = groupedEntries.values().stream().filter(count -> count == mostSecurityEntry.getValue()).count();
		String result = null;
		if(occurences == 1) {
			result = mostSecurityEntry.getKey();
		}
		return result;
	}
	
	String queryForLeastWordy(List<TalkModel> talks) {
		var groupedEntries =
			talks
			.stream()
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.summarizingInt(talk -> talk.getCountOfWords())));
		if(groupedEntries.isEmpty()) return null;
		var leastWordsEntry = 
			groupedEntries
			.entrySet()
			.stream()
			.min(Comparator.comparingLong(entry -> entry.getValue().getSum()))
			.orElse(null);
		if(leastWordsEntry == null) return null;
		var occurences = groupedEntries.values().stream().filter(stats -> stats.getSum() == leastWordsEntry.getValue().getSum()).count();
		String result = null;
		if(occurences == 1) {
			result = leastWordsEntry.getKey();
		}
		return result;
	}
}