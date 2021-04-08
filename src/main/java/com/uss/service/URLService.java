package com.uss.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uss.domain.URL;
import com.uss.dto.URLDto;
import com.uss.repository.URLRepository;
import com.uss.utils.Base62Util;
import com.uss.utils.URLUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class URLService {

	private final Base62Util base62Util;
	private final URLUtil urlUtil;
	private final URLRepository urlRepository;	
	
    public URLDto shortenURL(URLDto urlDto, String localURL) {

    	URL savingURL;
    	String shorturl;
    	String baseURL = urlUtil.removeParameter(localURL) + "/";
    	String longURL = urlDto.getLongURL();
        Optional<URL> URLWrapper = urlRepository.findByLongURL(longURL);        
        
        if(!urlUtil.valid(longURL)){
        	throw new IllegalArgumentException("잘못된 URL 타입입니다.");
        }
        
        if(URLWrapper.isPresent()) {
        	URL url = URLWrapper.get();
        	savingURL = URL.builder()
        		.id(url.getId())
        		.longURL(url.getLongURL())
        		.visited(url.getVisited()+1)
        		.build();
        }else {
        	urlDto.setVisited(1);
        	savingURL = urlDto.toEntity();
        }

        String uniqueID = base62Util.encoding(urlRepository.save(savingURL).getId());
        shorturl = baseURL + uniqueID;
        URLDto resUrlDto = new URLDto(savingURL);
        resUrlDto.setShortURL(shorturl);
        
        return resUrlDto;
    }
    
    public String getLongURLFromID(String uniqueID){
    	
        Long id = base62Util.decoding(uniqueID);
        Optional<URL> url = urlRepository.findById(id);
        String longURL = "";
        
        if(url.isPresent()) {
        	longURL = url.get().getLongURL();
        }
        
        return longURL;
    }
    
}
