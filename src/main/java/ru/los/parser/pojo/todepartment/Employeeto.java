package ru.los.parser.pojo.todepartment;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employeeto {

	@JacksonXmlProperty(localName = "name")
	private String name;
	@JacksonXmlProperty(localName = "post")
	private String post;
	@JacksonXmlProperty(localName = "email")
	private String email;



}
