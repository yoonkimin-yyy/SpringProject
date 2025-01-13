package kr.co.green.contact.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSearchDTO {
	private String category ="title";
	private String searchText = "";
}
