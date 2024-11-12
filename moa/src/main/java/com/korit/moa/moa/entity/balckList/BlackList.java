package com.korit.moa.moa.entity.balckList;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Black_List")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_list_id")
    private Long blackListId;

    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false, name = "group_id")
    private Long groupId;
}
