package com.legalsight.speech.repository.entity;

import com.legalsight.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "speeches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeechEntity extends BaseEntity {
    @Column(name = "speech_text")
    private String speechText;

    @Column(name = "author")
    private String author;

    @Column(name = "subject_area")
    private String subjectArea;

    @Column(name = "speech_date")
    private LocalDate speechDate;

    @Override
    public String toString() {
        return "SpeechEntity{" +
                "id='" + getId() + '\'' +
                ", createTime=" + getCreateTime() +
                ", modifyTime=" + getModifyTime() +
                ", version=" + getVersion() +
                ", text='" + speechText + '\'' +
                ", author='" + author + '\'' +
                ", subjectArea='" + subjectArea + '\'' +
                ", dateRange=" + speechDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpeechEntity that = (SpeechEntity) o;
        return Objects.equals(speechText, that.speechText) &&
                Objects.equals(author, that.author) &&
                Objects.equals(subjectArea, that.subjectArea) &&
                Objects.equals(speechDate, that.speechDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speechText, author, subjectArea, speechDate);
    }
}

