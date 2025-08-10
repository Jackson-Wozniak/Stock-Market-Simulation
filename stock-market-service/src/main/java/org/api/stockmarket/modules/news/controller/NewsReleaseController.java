package org.api.stockmarket.modules.news.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.news.service.NewsReleaseService;
import org.api.stockmarket.modules.stocks.exception.StockNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
@AllArgsConstructor
public class NewsReleaseController {

    private final NewsReleaseService newsReleaseService;


}
