package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "events") // データベースのテーブル名
@Data // Lombokを使用する場合（Getter/Setterを自動生成）
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String venue;

    @Column(name = "performance_date", nullable = false)
    private LocalDateTime date; // Thymeleafの ${event.date} に対応

    @Column(name = "cast_names", columnDefinition = "TEXT")
    private String cast; // Thymeleafの ${event.cast} に対応

    private Integer price;

    @Column(name = "total_capacity")
    private Integer totalCapacity; // 規定数

    private Integer remaining; // 残り枚数

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 前回のHTMLで ${event.cast} となっていたので、
    // 変数名を cast にするか、HTML側を castNames に合わせる必要があります。
}