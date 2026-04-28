package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EventEntity;
import com.example.demo.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository; 

    /**
     * 全件取得（一覧）
     */
    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    /**
     * ID で対象の情報を1件取得（詳細）
     */
    public Optional<EventEntity> findById(Long id) {
        return eventRepository.findById(id);
    }

    /**
     * 新規 or 更新保存
     */
    public EventEntity save(EventEntity event) {
        return eventRepository.save(event); // DB に保存
    }

    /**
     * チケット購入処理
     */
    public void purchaseTicket(Long id) {

        // DB から対象の公演を取得
        EventEntity event = eventRepository.findById(id).orElse(null);
        if (event == null) return; // なければ終了

        // 残り枚数がある場合のみ減らす
        if (event.getRemaining() > 0) {
            event.setRemaining(event.getRemaining() - 1);

            // 更新した内容を DB に保存
            eventRepository.save(event);
        }
    }
}
