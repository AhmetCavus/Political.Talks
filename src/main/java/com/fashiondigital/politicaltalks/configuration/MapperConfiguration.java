package com.fashiondigital.politicaltalks.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fashiondigital.politicaltalks.entity.TalkEntity;
import com.fashiondigital.politicaltalks.model.TalkModel;

@Configuration
public class MapperConfiguration {
	
	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public TypeMap<TalkEntity, TalkModel> createTalkMapper(ModelMapper modelMapper) {
		Converter<TalkEntity, TalkModel> toTalkModel =
			    ctx -> ctx.getSource() == null ? null : createTalkModel(ctx.getSource());
	    var typeMap =
			modelMapper
			.createTypeMap(TalkEntity.class, TalkModel.class)
			.setConverter(toTalkModel);
	    return typeMap;
	}
	
	private TalkModel createTalkModel(TalkEntity talkDto) {
		try {
			var localDate = LocalDate.parse(talkDto.getLocalDate().strip());
			return
				new TalkModel(
					talkDto.getSpeaker(), 
					talkDto.getSubject(), 
					localDate, 
					talkDto.getCountOfWords());
		} catch (DateTimeParseException e) {
			Logger.getGlobal().log(Level.WARNING, e.getMessage());
			return
				new TalkModel(
					talkDto.getSpeaker(), 
					talkDto.getSubject(), 
					null, 
					talkDto.getCountOfWords());
		}
	}
}