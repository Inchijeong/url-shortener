package com.uss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uss.dto.URLDto;
import com.uss.service.URLService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class URLController {
	
    private final URLService urlService;

    @GetMapping("/")
    public String index() {
    	return "/index.html";
    }
    
    @PostMapping("/shortener")
    public String shortenUrl(URLDto urlDto, HttpServletRequest request, Model model, RedirectAttributes rttr) {
        
        String longURL = urlDto.getLongURL();
        String localURL = request.getRequestURL().toString();
        
        URLDto resUrlDto =  urlService.shortenURL(urlDto, localURL);
        
        rttr.addFlashAttribute("resUrlDto",resUrlDto);
        
        return "redirect:/";
    }

    @GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
    	
        String redirectUrlString = urlService.getLongURLFromID(id);
        
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        
        return redirectView;
    }
}
