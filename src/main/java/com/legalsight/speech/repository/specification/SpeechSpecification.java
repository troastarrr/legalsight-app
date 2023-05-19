package com.legalsight.speech.repository.specification;

import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.repository.entity.SpeechEntity;
import com.legalsight.speech.repository.entity.SpeechEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class SpeechSpecification implements Specification<SpeechEntity> {
    private final transient SpeechFilterRequest speechFilterRequest;
    @Getter
    private final List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<SpeechEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Optional.ofNullable(speechFilterRequest.getSpeech()).ifPresent(speech -> {
            hasAuthor(root, criteriaBuilder, predicates, speech);
            hasSpeechDate(root, criteriaBuilder, predicates, speech);
            hasSubjectArea(root, criteriaBuilder, predicates, speech);
            hasSpeechText(root, criteriaBuilder, predicates, speech);
        });
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void hasSpeechText(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        Optional.ofNullable(speech.getSpeechText()).ifPresent(speechText -> predicates.add(criteriaBuilder.like(root.get(SpeechEntity_.SPEECH_TEXT), "%" + speechText + "%")));
    }

    private void hasSubjectArea(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        Optional.ofNullable(speech.getSubjectArea()).ifPresent(subjectArea -> predicates.add(criteriaBuilder.equal(root.get(SpeechEntity_.SUBJECT_AREA), subjectArea)));
    }

    private void hasSpeechDate(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        Optional.ofNullable(speech.getSpeechDate()).ifPresent(speechDate -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(SpeechEntity_.SPEECH_DATE), speech.getSpeechDate())));
    }

    private void hasAuthor(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        Optional.ofNullable(speech.getAuthor()).ifPresent(author -> predicates.add(criteriaBuilder.equal(root.get(SpeechEntity_.AUTHOR), author)));
    }
}
