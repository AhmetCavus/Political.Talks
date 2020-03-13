package com.fashiondigital.politicaltalks.service;

import java.util.List;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;

/**
 * A service for evaluating the speeches of the politicians.
 * @author Ahmet Cavus
 */
public interface EvaluationService {
	
	/**
	 * Evaluates the speeches from a list of items of type {@link TalkModel}.
	 * @param talks The source for the evaluation.
	 * @return the evaluation result of type {@link EvaluationModel}
	 */
	EvaluationModel evaluate(List<TalkModel> talks);
}