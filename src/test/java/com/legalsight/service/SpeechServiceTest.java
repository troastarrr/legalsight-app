package com.legalsight.service;


import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.dto.SpeechFilterRequest;
import com.legalsight.speech.dto.SpeechListResponse;
import com.legalsight.speech.error.SpeechNotFoundException;
import com.legalsight.speech.mapper.SpeechMapper;
import com.legalsight.speech.repository.SpeechRepository;
import com.legalsight.speech.repository.entity.SpeechEntity;
import com.legalsight.speech.repository.specification.SpeechSpecification;
import com.legalsight.speech.service.SpeechService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeechServiceTest {
    @Mock
    private SpeechMapper speechMapper;

    @Mock
    private SpeechRepository speechRepository;

    @InjectMocks
    private SpeechService speechService;

    @Test
    @DisplayName("Add Speech Test")
    void givenNewSpeech_whenAddSpeech_thenReturnAddedSpeech() {
        // Given
        Speech speech = new Speech();
        SpeechEntity speechEntity = new SpeechEntity();
        when(speechMapper.toEntity(any())).thenReturn(speechEntity);
        when(speechMapper.toDto(any())).thenReturn(speech);

        // When
        Speech result = speechService.createSpeech(speech);

        // Then
        assertThat(result).isEqualTo(speech);
        verify(speechRepository).save(speechEntity);
    }

    @Test
    @DisplayName("Find Speech By Id Test")
    void givenExistingSpeechId_whenFindSpeechById_thenReturnFoundSpeech() {
        // Given
        String id = "123";
        SpeechEntity speechEntity = new SpeechEntity();
        Speech speech = new Speech();
        when(speechRepository.findById(id)).thenReturn(java.util.Optional.of(speechEntity));
        when(speechMapper.toDto(speechEntity)).thenReturn(speech);

        // When
        Speech result = speechService.findSpeechById(id);

        // Then
        assertThat(result).isEqualTo(speech);
    }

    @Test
    @DisplayName("Find Speech By Id Test (Not Found)")
    void givenNonExistingSpeechId_whenFindSpeechById_thenThrowSpeechNotFoundException() {
        // Given
        String id = "123";
        when(speechRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Then
        assertThatThrownBy(() -> {
            // When
            speechService.findSpeechById(id);
        }).isInstanceOf(SpeechNotFoundException.class);
    }

    @Test
    @DisplayName("Update Speech Test")
    void givenExistingSpeech_whenUpdateSpeech_thenReturnUpdatedSpeech() {
        // Given
        Speech speech = new Speech();
        speech.setId("123");
        SpeechEntity speechEntityToUpdate = new SpeechEntity();
        when(speechRepository.findById(speech.getId())).thenReturn(java.util.Optional.of(speechEntityToUpdate));
        when(speechRepository.save(speechEntityToUpdate)).thenReturn(speechEntityToUpdate);
        when(speechMapper.toDto(speechEntityToUpdate)).thenReturn(speech);

        // When
        Speech result = speechService.updateSpeech(speech);

        // Then
        assertThat(result).isEqualTo(speech);
        verify(speechMapper).update(eq(speech), any(SpeechEntity.class));
        verify(speechRepository).save(speechEntityToUpdate);
    }

    @Test
    @DisplayName("Delete Speech Test")
    void givenExistingSpeechId_whenDeleteSpeech_thenSpeechIsDeleted() {
        // Given
        String id = "123";
        when(speechRepository.existsById(id)).thenReturn(true);

        // When
        speechService.deleteSpeech(id);

        // Then
        verify(speechRepository).deleteById(id);
    }

    @Test
    @DisplayName("Find Speeches By Filter Test")
    void givenSpeechFilterRequest_whenFindByFilter_thenReturnFilteredSpeeches() {
        // Given
        SpeechFilterRequest filterRequest = new SpeechFilterRequest();
        filterRequest.setPage(1);
        filterRequest.setPerPage(10);
        PageRequest pageRequest = PageRequest.of(filterRequest.getPage(), filterRequest.getPerPage());
        SpeechEntity speechEntity = new SpeechEntity();
        Page<SpeechEntity> speechEntities = new PageImpl<>(Collections.singletonList(speechEntity));
        when(speechRepository.findAll(any(SpeechSpecification.class), any(PageRequest.class))).thenReturn(speechEntities);
        when(speechMapper.toDto(speechEntity)).thenReturn(new Speech());

        // When
        SpeechListResponse result = speechService.searchSpeeches(filterRequest);

        // Then
        assertThat(result.data()).hasSize(1);
        assertThat(result.totalElements()).isEqualTo(1);
        assertThat(result.totalPages()).isEqualTo(1);
        assertThat(result.currentPage()).isOne();
    }
}
