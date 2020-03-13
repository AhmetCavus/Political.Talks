package com.fashiondigital.politicaltalks.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;

/**
 * {@inheritDoc}
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {
	
	final int queryYear;
	final String querySubject;
	
	public EvaluationServiceImpl(@Qualifier("queryYear") int queryYear, @Qualifier("querySubject") String querySubject) {
		this.queryYear = queryYear;
		this.querySubject = querySubject;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EvaluationModel evaluate(List<TalkModel> talks) {
		var mostSpeeches = queryForMostSpeeches(talks);
		var mostSecurity = queryForMostSecurity(talks);
		var leastWordy = queryForLeastWordy(talks);
		
		return new EvaluationModel(mostSpeeches, mostSecurity, leastWordy);
	}
	
	private String queryForMostSpeeches(List<TalkModel> talks) {
		
		// TODO Use another approach with a database or improve the query. 
		
		// 1. Filter and group the list by the speakers.
		var groupedEntries =
			talks
			.stream()
			.filter(talk -> talk.getLocalDate().getYear() == queryYear)
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.counting()));
		
		// Gets the max value for the condition.
		var mostSpeechesEntry = 
			groupedEntries
			.entrySet()
			.stream()
			.max(Comparator.comparingLong(entry -> entry.getValue()))
			.orElse(null);
		if(mostSpeechesEntry == null) return null;
		
		// Check if more the one results exists.
		// Check if there is more than one result. 
		// If so, then return a zero to satisfy the acceptance criteria.
		var occurences = groupedEntries.values().stream().filter(count -> count == mostSpeechesEntry.getValue()).count();
		String result = null;
		if(occurences == 1) {
			result = mostSpeechesEntry.getKey();
		}
		
		return result;
	}
	
	private String queryForMostSecurity(List<TalkModel> talks) {
		
		// TODO Use another approach with a database or improve the query. 
		
		// Filter and group the list by the speakers.
		var groupedEntries =
			talks
			.stream()
			.filter(talk -> StringUtils.equals(talk.getSubject().strip(), querySubject))
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.counting()));
		if(groupedEntries.isEmpty()) return null;
		
		// Gets the max value for the condition.
		var mostSecurityEntry = 
			groupedEntries
			.entrySet()
			.stream()
			.max(Comparator.comparingLong(entry -> entry.getValue()))
			.orElse(null);
		if(mostSecurityEntry == null) return null;
		
		// Check if more the one results exists.
		// Check if there is more than one result. 
		// If so, then return a zero to satisfy the acceptance criteria.
		var occurences = groupedEntries.values().stream().filter(count -> count == mostSecurityEntry.getValue()).count();
		String result = null;
		if(occurences == 1) {
			result = mostSecurityEntry.getKey();
		}
		
		return result;
	}
	
	private String queryForLeastWordy(List<TalkModel> talks) {
		
		// TODO Use another approach with a database or improve the query.
		
		// Group the list by the speakers.
		var groupedEntries =
			talks
			.stream()
			.collect(Collectors.groupingBy(TalkModel::getSpeaker, Collectors.summarizingInt(talk -> talk.getCountOfWords())));
		if(groupedEntries.isEmpty()) return null;
		
		// Gets the min value for the condition.
		var leastWordsEntry = 
			groupedEntries
			.entrySet()
			.stream()
			.min(Comparator.comparingLong(entry -> entry.getValue().getSum()))
			.orElse(null);
		if(leastWordsEntry == null) return null;
		
		// Check if more the one results exists.
		// Check if there is more than one result. 
		// If so, then return a zero to satisfy the acceptance criteria.
		var occurences = groupedEntries.values().stream().filter(stats -> stats.getSum() == leastWordsEntry.getValue().getSum()).count();
		String result = null;
		if(occurences == 1) {
			result = leastWordsEntry.getKey();
		}
		
		return result;
	}
	
	
}