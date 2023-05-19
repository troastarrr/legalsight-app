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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SpeechSpecification implements Specification<SpeechEntity> {
    private final transient SpeechFilterRequest speechFilterRequest;
    @Getter
    private final List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<SpeechEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (speechFilterRequest.getSpeech() != null) {
            Speech speech = speechFilterRequest.getSpeech();
            hasAuthor(root, criteriaBuilder, predicates, speech);
            hasSpeechDate(root, criteriaBuilder, predicates, speech);
            hasSubjectArea(root, criteriaBuilder, predicates, speech);
            hasSpeechText(root, criteriaBuilder, predicates, speech);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void hasSpeechText(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        if (speech.getSpeechText() != null) {
            predicates.add(criteriaBuilder.like(root.get(SpeechEntity_.SPEECH_TEXT), "%" + speech.getSpeechText() + "%"));
        }
    }

    private void hasSubjectArea(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        if (speech.getSubjectArea() != null) {
            predicates.add(criteriaBuilder.equal(root.get(SpeechEntity_.SUBJECT_AREA), speech.getSubjectArea()));
        }
    }

    private void hasSpeechDate(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        if (speech.getSpeechDate() != null) {
            LocalDate startDate = speech.getSpeechDate().minusDays(1);
            LocalDate endDate = speech.getSpeechDate().plusDays(1);
            predicates.add(criteriaBuilder.between(root.get(SpeechEntity_.SPEECH_DATE), startDate, endDate));
        }
    }

    private void hasAuthor(Root<SpeechEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Speech speech) {
        if (speech.getAuthor() != null) {
            predicates.add(criteriaBuilder.equal(root.get(SpeechEntity_.AUTHOR), speech.getAuthor()));
        }
    }
}