package com.fashiondigital.politicaltalks.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;

@Service
public class EvaluationService {
	
	public EvaluationModel evaluate(List<TalkModel> talks) {
		return new EvaluationModel();
	}
}