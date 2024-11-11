package com.korit.moa.moa.entity.Recommendation;

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
    private Integer groupId;
    private String userId;
}
