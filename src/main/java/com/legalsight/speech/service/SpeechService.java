package com.legalsight.speech.service;

import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.dto.SpeechListResponse;
import com.legalsight.speech.error.SpeechNotFoundException;
import com.legalsight.speech.mapper.SpeechMapper;
import com.legalsight.speech.repository.SpeechRepository;
import com.legalsight.speech.repository.entity.SpeechEntity;
import com.legalsight.speech.repository.specification.SpeechSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class SpeechService {

    private final SpeechMapper speechMapper;
    private final SpeechRepository speechRepository;

    @Transactional
    public Speech createSpeech(Speech speech) {
        log.debug("Creating speech: `{}`", speech);
        SpeechEntity speechEntity = speechRepository.save(speechMapper.toEntity(speech));
        return speechMapper.toDto(speechEntity);
    }

    public Speech findSpeechById(String id) {
        log.debug("Finding speech by id: `{}`", id);
        SpeechEntity speechEntity = findSpeechEntityById(id);
        return speechMapper.toDto(speechEntity);
    }

    @Transactional
    public Speech updateSpeech(Speech speech) {
        log.debug("Updating speech: `{}`", speech);
        SpeechEntity speechEntity = findSpeechEntityById(speech.getId());
        speechMapper.update(speech, speechEntity);
        speechEntity = speechRepository.save(speechEntity);
        return speechMapper.toDto(speechEntity);
    }

    @Transactional
    public void deleteSpeech(String id) {
        if (speechRepository.existsById(id)) {
            log.debug("Deleting speech with id: `{}`", id);
            speechRepository.deleteById(id);
        } else {
            log.debug("Speech with id {} does not exist", id);
        }
    }

    public SpeechListResponse searchSpeeches(SpeechFilterRequest filterRequest) {
        log.debug("Searching speeches with filter: `{}`", filterRequest);
        Page<SpeechEntity> speechEntities = speechRepository.findAll(new SpeechSpecification(filterRequest), filterRequest.pageRequest());
        List<Speech> speeches = speechEntities.map(speechMapper::toDto).toList();
        return new SpeechListResponse(speeches)
                .totalElements(speechEntities.getTotalElements())
                .totalPages(speechEntities.getTotalPages())
                .currentPage(filterRequest.getPage());
    }

    private SpeechEntity findSpeechEntityById(String id) {
        return speechRepository.findById(id)
                .orElseThrow(() -> new SpeechNotFoundException(String.format("Speech not found with id: %s", id)));
    }
}
