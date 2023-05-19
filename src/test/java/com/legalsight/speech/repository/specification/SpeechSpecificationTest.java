package com.legalsight.speech.repository.specification;


import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.repository.entity.SpeechEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SpeechSpecificationTest {

    @Mock
    private Root<SpeechEntity> root;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Object> criteriaQuery;

    @Test
    @DisplayName("Speech Specification Test")
    void givenSpeechFilterRequest_whenToPredicate_thenReturnPredicate() {
        // Given
        SpeechFilterRequest speechFilterRequest = new SpeechFilterRequest();
        Speech speech = new Speech();
        speech.setSpeechText("test");
        speech.setAuthor("John Doe");
        speech.setSubjectArea("Law");
        speech.setSpeechDate(LocalDate.of(2023, 1, 1));
        speechFilterRequest.setSpeech(speech);

        SpeechSpecification specification = new SpeechSpecification(speechFilterRequest);

        // When
        specification.toPredicate(root, criteriaQuery, criteriaBuilder);

        // Then
        assertThat(specification.getPredicates()).hasSize(4);
    }

    @Test
    @DisplayName("Speech Specification No Filter Request Test")
    void givenNoSpeechFilterRequest_whenToPredicate_thenReturnPredicate() {
        // Given
        SpeechFilterRequest speechFilterRequest = new SpeechFilterRequest();

        SpeechSpecification specification = new SpeechSpecification(speechFilterRequest);

        // When
        specification.toPredicate(root, criteriaQuery, criteriaBuilder);

        // Then
        assertThat(specification.getPredicates()).isEmpty();
    }
}

