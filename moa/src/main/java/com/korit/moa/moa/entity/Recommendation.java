package com.korit.moa.moa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    @Id
    @Column(name = "user_id",nullable = false,length = 255)
    private  String userId;

    @Column(name = "group_id",nullable = false)
    private  Long groupId;
}
