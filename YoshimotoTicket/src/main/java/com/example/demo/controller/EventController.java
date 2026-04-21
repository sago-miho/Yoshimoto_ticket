package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EventEntity;
import com.example.demo.repository.EventRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    /**
     * ホーム画面を表示する
     */
    @GetMapping("/home")
    public String viewHome(Model model) {
        List<EventEntity> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "home"; // src/main/resources/templates/home.html を呼び出す
    }

    /**
     * 公演追加画面を表示する
     */
    @GetMapping("/event/new")
    public String showNewEventForm(Model model) {
        // フォームを空の状態で表示するために、空のエンティティを渡す
        model.addAttribute("event", new EventEntity()); 
        return "event-new";
    }
    
    /**
     * 公演を保存する（バリデーションチェック付き）
     */
    @PostMapping("/event/new")
    public String create(@Validated @ModelAttribute("event") EventEntity event, 
                         BindingResult result, 
                         Model model) {
        
        // 入力エラーがあった場合
        if (result.hasErrors()) {
            // エラーがあった場合、入力途中の内容を保持したまま "event-new" 画面を表示
            return "event-new";
        }

        // エラーがなければデータベースに保存
        eventRepository.save(event);
        // 保存後は一覧画面（/home）にリダイレクト
        return "redirect:/home";
    }


    /**
     * 公演詳細画面を表示する
     */
    @GetMapping("/event/detail/{id}")
    public String viewDetail(@PathVariable("id") Long id, Model model) {
        Optional<EventEntity> eventOpt = eventRepository.findById(id);
        if (eventOpt.isPresent()) {
            model.addAttribute("event", eventOpt.get());
            return "event-detail";
        } else {
            return "redirect:/home";
        }
    }
    
 // 予約処理（POSTリクエスト）
    @PostMapping("/event/purchase/{id}")
    public String purchaseTicket(@PathVariable("id") Long id) {
        // データベースから公演情報を取得
        EventEntity event = eventRepository.findById(id).get();

        // 在庫が0より大きい場合のみ引き算する
        if (event.getRemaining() > 0) {
            event.setRemaining(event.getRemaining() - 1);
            // データベースを更新（上書き保存）
            eventRepository.save(event);
        }

        //更新後の詳細画面へリダイレクト（再表示）
        return "redirect:/event/detail/" + id;
    }
}