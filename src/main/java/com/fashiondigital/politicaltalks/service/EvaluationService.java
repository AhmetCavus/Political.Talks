package com.fashiondigital.politicaltalks.service;

import java.util.List;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;

public interface EvaluationService {
	EvaluationModel evaluate(List<TalkModel> talks);
}