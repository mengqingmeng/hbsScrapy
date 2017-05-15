package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.beautyPediaSprider.PediaService;
import com.example.entity.BeautyCategory;
import com.example.scrapy.GcftScrapy;

import com.example.util.UnirestUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by mqm on 2017/5/10.
 */
@Controller
public class HomeController {
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    GcftScrapy gcftScrapy;

    @RequestMapping("/gcft")
    public String home() {
        return "gcft";
    }

    @RequestMapping("/BeautyPedia")
    public String BeautyPedia(ModelMap model) {
        List<BeautyCategory> categoryList = PediaService.updateCategory();
        List<BeautyCategory> categoryListS = new ArrayList<BeautyCategory>();
        List<BeautyCategory> categoryListM = new ArrayList<BeautyCategory>();
        List<BeautyCategory> categoryListB = new ArrayList<BeautyCategory>();
        for (BeautyCategory b : categoryList) {
            switch (b.getType().ordinal()) {
                case 0:
                    categoryListS.add(b);
                    break;
                case 1:
                    categoryListM.add(b);
                    break;
                case 2:
                    categoryListB.add(b);
                    break;
            }
        }
        model.put("categoryListB", categoryListB);
        model.put("categoryListM", categoryListM);
        model.put("categoryListS", categoryListS);
        return "BeautyPedia";
    }
    @ResponseBody
    @RequestMapping("/getinfo")
    public String Getinfo(@RequestParam("proId") Integer proId){
		System.out.println(proId);
		PediaService.main(proId);
    	return "结束";
	}
    @ResponseBody
    @RequestMapping("/startGcft")
    public String startGcft(@RequestParam("fromPage") Integer fromePage,
                            @RequestParam("endPage") Integer endPage,
                            @RequestParam("ku") String ku) throws Exception {
        gcftScrapy.Scrapy(fromePage, endPage, ku);
        return "succ";
    }

    @RequestMapping("/ewg")
    public String ewg(ModelMap model) throws Exception {
        String url = "https://www.ewg.org/skindeep/";
        Document doc = Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select(".menuhover");
        JSONArray jobs = new JSONArray();//要执行的爬虫任务列表
        if (newsHeadlines.size()>0){
            for (Element element:newsHeadlines) {
                Elements children = element.children();
                if (children.size() > 0) {
                    Element submenu = children.get(0);//分类名称
                    Element sub = children.get(1);//分类内容

                    JSONObject job = new JSONObject();
                    job.put("name", submenu.text());

                    //分类内容中，又有细分类，所以这里建个数组存放，细分类包含名称和链接+名称
                    JSONArray classifies = new JSONArray();
                    for (Element ul : sub.getElementsByTag("ul")) {
                        JSONObject typeWithUrls = new JSONObject();//细分类
                        String type = ul.child(0).getElementsByTag("h2").text();//细分类名称
                        typeWithUrls.put("type", type);
                        JSONArray urlWithNames = new JSONArray();//细分类链接+名称
                        Elements as = ul.getElementsByTag("a");
                        for (Element a : as) {//添加链接和名称
                            JSONObject urlWithName = new JSONObject();
                            urlWithName.put("url", a.attr("href"));
                            urlWithName.put("name", a.text());
                            urlWithNames.add(urlWithName);
                        }
                        typeWithUrls.put("urls", urlWithNames);

                        classifies.add(typeWithUrls);
                    }

                    job.put("classifies", classifies);
                    jobs.add(job);
                }
            }
        }
        logger.info("jobs:"+jobs.toString());
        model.put("jobs",jobs);
        return "ewg";
    }
}