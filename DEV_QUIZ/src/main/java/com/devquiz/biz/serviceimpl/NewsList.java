package com.devquiz.biz.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class NewsList {
	  	private String title;
	    private String link;

	    public NewsList(String title, String link) {
	        this.title = title;
	        this.link = link;
	    }
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}
		

		public static List<NewsList> News() {
	        String url = "https://www.itworld.co.kr/topnews";
	        List<NewsList> newsList = new ArrayList<NewsList>();

	        try {
	            Document doc = Jsoup.connect(url).get();
	            Elements newsCards = doc.select(".card-article");

	            for (Element newsCard : newsCards) {
	                String title = newsCard.select(".card-title a").text();
	                String link = "https://www.itworld.co.kr" + newsCard.select(".card-title a").attr("href");

	                NewsList newsItem = new NewsList(title, link);
	                newsList.add(newsItem);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return newsList;
	    }
		
}
