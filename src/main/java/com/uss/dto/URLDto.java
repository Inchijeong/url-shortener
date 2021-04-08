package com.uss.dto;

import com.uss.domain.BaseTime;
import com.uss.domain.URL;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class URLDto extends BaseTime{
	
	private Long id;
    private String longURL;
    private String shortURL;
    private Integer visited;
	
	public URLDto(URL url) {
		this.id = url.getId();
		this.longURL = url.getLongURL();
		this.visited = url.getVisited();
	}
	
	public URL toEntity() {
		return URL.builder()
				.id(id)
				.longURL(longURL)
				.visited(visited)
				.build();
	}
}
