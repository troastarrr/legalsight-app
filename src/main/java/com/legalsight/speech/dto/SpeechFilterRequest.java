package com.legalsight.speech.dto;

import com.legalsight.speech.domain.Speech;
import com.legalsight.speech.repository.entity.SpeechEntity_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class SpeechFilterRequest {
    private static final String DESC = "desc";
    private static final Sort DEFAULT_SORT = Sort.by(Sort.Direction.DESC, SpeechEntity_.AUTHOR);
    private static final PageRequest DEFAULT_PAGE_REQUEST = PageRequest.of(0, 50, DEFAULT_SORT);
    private Speech speech;
    private int page;
    private int perPage;
    private String sortDir;
    private String sortBy;

    public SpeechFilterRequest(final Speech speech) {
        this.speech = speech;
    }

    public PageRequest pageRequest() {
        return Optional.of(page)
                .flatMap(p -> Optional.of(perPage)
                        .map(pp -> PageRequest.of(p - 1, pp, sortBy())))
                .orElse(DEFAULT_PAGE_REQUEST);
    }

    private Sort sortBy() {
        Sort.Direction direction = DESC.equals(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return StringUtils.isEmpty(sortBy) || StringUtils.isEmpty(sortDir)
                ? DEFAULT_SORT
                : Sort.by(direction, sortBy);
    }
}
