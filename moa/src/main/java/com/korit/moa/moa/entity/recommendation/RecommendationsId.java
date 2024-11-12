package com.korit.moa.moa.entity.recommendation;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecommendationsId implements Serializable {
    private Long groupId;

    private String userId;
}
