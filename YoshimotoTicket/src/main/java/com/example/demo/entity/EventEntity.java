package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "events")
@Data
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 公演名：空文字禁止、50文字以内
    @NotBlank(message = "公演名を入力してください")
    @Size(max = 50, message = "公演名は50文字以内で入力してください")
    @Column(nullable = false)
    private String title;

    // 会場：空文字禁止
    @NotBlank(message = "会場名を入力してください")
    @Column(nullable = false)
    private String venue;

    // 公演日時：必須、かつ現在より未来であること
    @NotNull(message = "日時を選択してください")
    @Future(message = "過去の日時は設定できません")
    @Column(name = "performance_date", nullable = false)
    private LocalDateTime date;

    // 出演者名：空欄禁止）
    @NotBlank(message = "出演者を入力してください" )
    @Column(name = "cast_names", columnDefinition = "TEXT")
    private String castNames;

    // チケット価格：必須、0円以上
    @NotNull(message = "価格を入力してください")
    @Min(value = 0, message = "価格は0円以上にしてください")
    private Integer price;

    // チケット規定枚数：必須、1枚以上
    @NotNull(message = "チケット枚数を入力してください")
    @Min(value = 1, message = "枚数は1枚以上にしてください")
    @Column(name = "total_capacity")
    private Integer totalCapacity;

    // チケット残数：必須、0枚以上（最初は規定枚数と同じにする想定）
    @NotNull(message = "初期残数を入力してください")
    @Min(value = 0, message = "残数は0枚以上にしてください")
    private Integer remaining;

}