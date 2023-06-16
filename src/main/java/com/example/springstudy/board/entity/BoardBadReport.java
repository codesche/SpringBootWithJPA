package com.example.springstudy.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardBadReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // == 신고자 정보 ==
    @Column
    private long userId;

    @Column
    private String userName;

    @Column
    private String userEmail;

    // == 신고 게시글 정보 ==
    @Column
    private long boardId;

    @Column
    private long boardUserId;

    @Column
    private String boardTitle;

    @Column
    private LocalDateTime boardContents;

    @Column
    private LocalDateTime boardRegDate;

    // == 신고 내용 ==
    @Column
    private String comments;

    @Column
    private LocalDateTime regDate;



}
