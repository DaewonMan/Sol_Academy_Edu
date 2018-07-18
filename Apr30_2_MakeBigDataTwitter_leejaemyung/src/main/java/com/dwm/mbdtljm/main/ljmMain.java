package com.dwm.mbdtljm.main;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ljmMain {
	public static void main(String[] args) {
		try {
			Document d = Jsoup.connect("https://twitter.com/search?f=tweets&q=%EC%9D%B4%EC%9E%AC%EB%AA%85&src=typd").get();
			
			Elements es = d.select("p.TweetTextSize.js-tweet-text.tweet-text");
			//Elements es = d.select(".TweetTextSize");
			for (Element element : es) {
				System.out.println(element.text());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
