package com.legalsight.speech.mapper;

import com.legalsight.mapper.BaseMapper;
import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.repository.entity.SpeechEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SpeechMapper extends BaseMapper<Speech, SpeechEntity> {
    void update(Speech source, @MappingTarget SpeechEntity target);
}
