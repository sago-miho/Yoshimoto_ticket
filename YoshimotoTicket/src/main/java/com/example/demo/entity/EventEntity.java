package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "events") // データベースのテーブル名
@Data // Lombokを使用する場合（Getter/Setterを自動生成）
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //公演名
    @Column(nullable = false)
    private String title;

    //会場
    private String venue;

    //公演日時
    @Column(name = "performance_date", nullable = false)
    private LocalDateTime date; // Thymeleafの ${event.date} に対応

    //出演者名
    @Column(name = "cast_names", columnDefinition = "TEXT")
    private String castNames; // Thymeleafの ${event.cast} に対応

    //チケット価格
    private Integer price;

    //チケット規定枚数
    @Column(name = "total_capacity")
    private Integer totalCapacity; // 規定数

    //チケット残数
    private Integer remaining; // 残り枚数

    // 前回のHTMLで ${event.cast} となっていたので、
    // 変数名を cast にするか、HTML側を castNames に合わせる必要があります。
}