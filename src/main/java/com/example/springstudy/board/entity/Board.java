package com.example.springstudy.board.entity;

import com.example.springstudy.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private BoardType boardType;

    @Column
    private String title;

    @Column
    private LocalDateTime contents;

    @Column
    private LocalDateTime regDate;

    @Column
    private boolean topYn;

    @Column
    private LocalDate publishStartDate;

    @Column
    private LocalDate publishEndDate;

}
