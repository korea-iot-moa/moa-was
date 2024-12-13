package com.korit.moa.moa.entity.balckList;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Black_List")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_list_id")
    private Long blackListId;


    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "group_id", nullable = false, unique = true)
    private Long groupId;
}

