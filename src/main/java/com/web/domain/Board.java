package com.web.domain;

import com.web.domain.enums.BoardType;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   @Id
   @Column
   // 키 생성을 DB에 위임하는 IDENTITY strategy 사용
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idx;

   @Column
   private String title;

   @Column
   private String subTitle;

   @Column
   private String content;

   @Column
   // 실제 자바 enum형 이지만 DB의 String형으로 변환해서 저장
   @Enumerated(EnumType.STRING)
   private BoardType boardType;

   @Column
   private LocalDateTime createdDate;

   @Column
   private LocalDateTime updatedDate;

   /*
   도메인 Board와 Board 필드값으로 갖고있는 User 도메인을 1:1 관계로 설정하는 annotation,
   실제로 DB에 저장될때 User 객체가 아니라 User의 PK인 user_idx 값이 저장,
   [eager] - Board 도메인을 조회 할 때 즉시 관련 User 객체와 함께 조회,
   [lazy] - User 객체를 조회하는 시점이 아닌 객체가 실제로 사용될 때 조회.
   */
   @OneToOne(fetch = FetchType.LAZY)
   private User user;

   @Builder
   public Board(String title, String subTitle, String content, BoardType boardType,
                LocalDateTime createdDate, LocalDateTime updatedDate, User user) {
       this.title = title;
       this.subTitle = subTitle;
       this.content = content;
       this.boardType = boardType;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.user = user;
   }

}