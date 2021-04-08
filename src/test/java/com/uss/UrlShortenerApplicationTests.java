package com.uss;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uss.controller.URLController;
import com.uss.dto.URLDto;
import com.uss.service.URLService;

@SpringBootTest
class UrlShortenerApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
	@Autowired
	private URLService urlService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void shortenURLTest() {
		
		String longURL = "https://www.naver.com/";
		String localURL = "http://uss.com:8080";
		
		URLDto urlDto = new URLDto();
		urlDto.setId(1l);
		urlDto.setLongURL(longURL);
		
        URLDto resUrlDto =  urlService.shortenURL(urlDto, localURL);
        
        LOGGER.info("resUrlDto " + resUrlDto);
	}
	
//	@Test
//	public void ShortenAbnomalURLTest() {
//		
//		String longURL = "abcde";
//		String localURL = "http://uss.com:8080";
//		
//		URLDto urlDto = new URLDto();
//		urlDto.setId(1l);
//		urlDto.setLongURL(longURL);
//		
//		URLDto resUrlDto =  urlService.shortenURL(urlDto, localURL);
//		
//		LOGGER.info("resUrlDto " + resUrlDto);
//	}
	
	@Test
	public void getLongURLFromIDTest() {
		
		String id = "a";
        String redirectUrlString = urlService.getLongURLFromID(id);
        
        LOGGER.info("redirectUrlString: " + redirectUrlString);
	}
	
	
	
	
}
