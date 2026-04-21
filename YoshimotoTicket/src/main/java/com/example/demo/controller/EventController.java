package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.EventEntity;
import com.example.demo.repository.EventRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/home") // ブラウザで叩くURL
    public String viewHome(Model model) {
    	// 1. DBから全データを取ってくる
        List<EventEntity> events = eventRepository.findAll();
        // 2. HTML側の ${events} という変数にデータを放り込む
        model.addAttribute("events", events);
        // 3. home.html を表示する
        return "home";
    }
}