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
import com.example.demo.service.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService eventService; 

    /**
     * ホーム画面表示（一覧）
     */
    @GetMapping("/home")
    public String viewHome(Model model) {

        // Serviceクラスから公演一覧情報の取得
        List<EventEntity> events = eventService.findAll();

        // 画面に渡す
        model.addAttribute("events", events);

        return "home"; 
    }

    /**
     * 公演追加画面を表示
     */
    @GetMapping("/event/new")
    public String showNewEventForm(Model model) {

        // 空の EventEntity を画面に渡す（フォーム用）
        model.addAttribute("event", new EventEntity());

        return "event-new";
    }

    /**
     * 公演を保存（POST）
     */
    @PostMapping("/event/new")
    public String create(@Validated @ModelAttribute("event") EventEntity event,
                         BindingResult result,
                         Model model) {

        // 入力チェック（バリデーションエラー）
        if (result.hasErrors()) {
            return "event-new"; // 入力画面に戻る
        }

        // Service に保存処理を依頼
        eventService.save(event);

        return "redirect:/home"; // 保存後一覧へ
    }

    /**
     * 公演詳細画面
     */
    @GetMapping("/event/detail/{id}")
    public String viewDetail(@PathVariable("id") Long id, Model model) {
    	//対象の情報をServiceクラスから取得する
        Optional<EventEntity> eventOpt = eventService.findById(id);

        if (eventOpt.isPresent()) {
            model.addAttribute("event", eventOpt.get());
            return "event-detail"; // 詳細画面へ
        } else {
            return "redirect:/home"; // なければ一覧へ
        }
    }

    /**
     * チケット購入処理（POST）
     */
    @PostMapping("/event/purchase/{id}")
    public String purchaseTicket(@PathVariable("id") Long id) {

        // 購入処理
        eventService.purchaseTicket(id);

        // 処理後、同じ詳細画面へ戻る
        return "redirect:/event/detail/" + id;
    }
}
