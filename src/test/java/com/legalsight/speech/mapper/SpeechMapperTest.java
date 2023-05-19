package com.legalsight.speech.mapper;


import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.repository.entity.SpeechEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SpeechMapperTest {

    private final SpeechMapper speechMapper = Mappers.getMapper(SpeechMapper.class);

    @Test
    @DisplayName("Mapping SpeechEntity to Speech")
    void givenSpeechEntity_whenMappingToSpeech_thenCorrectMapping() {
        // Given
        SpeechEntity speechEntity = new SpeechEntity();
        speechEntity.setId("1");
        speechEntity.setSpeechText("Sample speech");
        speechEntity.setAuthor("John Doe");
        speechEntity.setSubjectArea("Law");
        speechEntity.setSpeechDate(LocalDate.now());

        // When
        Speech speech = speechMapper.toDto(speechEntity);

        // Then
        assertThat(speech.getId()).isEqualTo(speechEntity.getId());
        assertThat(speech.getSpeechText()).isEqualTo(speechEntity.getSpeechText());
        assertThat(speech.getAuthor()).isEqualTo(speechEntity.getAuthor());
        assertThat(speech.getSubjectArea()).isEqualTo(speechEntity.getSubjectArea());
        assertThat(speech.getSpeechDate()).isEqualTo(speechEntity.getSpeechDate());

    }

    @Test
    @DisplayName("Mapping Speech to SpeechEntity")
    void givenSpeech_whenMappingToSpeechEntity_thenCorrectMapping() {
        // Given
        Speech speech = new Speech();
        speech.setId("1");
        speech.setSpeechText("Sample speech");
        speech.setAuthor("John Doe");
        speech.setSubjectArea("Law");
        speech.setSpeechDate(LocalDate.now());

        // When
        SpeechEntity speechEntity = speechMapper.toEntity(speech);

        // Then
        assertThat(speechEntity.getId()).isEqualTo(speech.getId());
        assertThat(speechEntity.getSpeechText()).isEqualTo(speech.getSpeechText());
        assertThat(speechEntity.getAuthor()).isEqualTo(speech.getAuthor());
        assertThat(speechEntity.getSubjectArea()).isEqualTo(speech.getSubjectArea());
        assertThat(speechEntity.getSpeechDate()).isEqualTo(speech.getSpeechDate());

    }

    @Test
    @DisplayName("Updating SpeechEntity")
    void givenSpeechAndSpeechEntity_whenUpdatingSpeechEntity_thenCorrectUpdate() {
        // Given
        Speech speech = new Speech();
        speech.setId("1");
        speech.setSpeechText("Sample speech");
        speech.setAuthor("John Doe");
        speech.setSubjectArea("Law");
        speech.setSpeechDate(LocalDate.now());

        SpeechEntity speechEntity = new SpeechEntity();
        speechEntity.setId("1");
        speechEntity.setSpeechText("Existing speech");
        speechEntity.setAuthor("Jane Smith");
        speechEntity.setSubjectArea("Technology");
        speechEntity.setSpeechDate(LocalDate.now());

        // When
        speechMapper.update(speech, speechEntity);

        // Then
        assertThat(speechEntity.getId()).isEqualTo(speech.getId());
        assertThat(speechEntity.getSpeechText()).isEqualTo(speech.getSpeechText());
        assertThat(speechEntity.getAuthor()).isEqualTo(speech.getAuthor());
        assertThat(speechEntity.getSubjectArea()).isEqualTo(speech.getSubjectArea());
        assertThat(speechEntity.getSpeechDate()).isEqualTo(speech.getSpeechDate());

    }
}

