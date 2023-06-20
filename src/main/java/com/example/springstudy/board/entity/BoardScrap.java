package com.example.springstudy.board.entity;

import com.example.springstudy.user.entity.User;
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
public class BoardScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private User user;

    // == 스크랩 글 정보 ==
    @Column
    private long boardId;

    @Column
    private long boardTypeId;

    @Column
    private long boardUserId;

    @Column
    private String boardTitle;

    @Column
    private LocalDateTime boardContents;

    @Column
    private LocalDateTime boardRegDate;

    @Column
    private LocalDateTime regDate;

}
