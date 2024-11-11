package com.korit.moa.moa.entity.blackList;

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
    private Long black_list_id;

    @JoinColumn(nullable = false)
    private String user_id;

    @JoinColumn(nullable = false)
    private Long group_id;
}
