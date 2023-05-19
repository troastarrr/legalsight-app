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
    public Speech add(Speech speech) {
        log.debug("Saving speech `{}`", speech);
        return speechMapper.toDto(speechRepository.save(speechMapper.toEntity(speech)));
    }

    public Speech findById(String id) {
        log.debug("Finding speech with id `{}`", id);
        return speechMapper.toDto(findAndThrow(id));
    }

    @Transactional
    public Speech update(Speech speech) {
        log.debug("Updating speech with id `{}`", speech.getId());
        SpeechEntity speechEntityToUpdate = findAndThrow(speech.getId());
        speechMapper.update(speech, speechEntityToUpdate);
        return speechMapper.toDto(speechRepository.save(speechEntityToUpdate));
    }

    @Transactional
    public void deleteSpeech(String id) {
        log.debug("Deleting speech with id `{}`", id);
        if (speechRepository.existsById(id)) {
            speechRepository.deleteById(id);
        }
        log.debug("Unable to find and delete speech with id `{}`", id);
    }

    public SpeechListResponse findByFilter(SpeechFilterRequest speechFilterRequest) {
        log.debug("finding speech with request `{}`", speechFilterRequest);
        Page<SpeechEntity> speechEntities = speechRepository.findAll(new SpeechSpecification(speechFilterRequest), speechFilterRequest.pageRequest());
        List<Speech> speechList = speechEntities.map(speechMapper::toDto).stream().toList();
        return new SpeechListResponse(speechList)
                .totalElements(speechEntities.getTotalElements())
                .totalPages(speechEntities.getTotalPages())
                .currentPage(speechFilterRequest.getPage());
    }

    private SpeechEntity findAndThrow(String id) {
        return speechRepository.findById(id)
                .orElseThrow(() -> new SpeechNotFoundException(String.format("Unable to find speech id `%s`", id)));
    }
}
