package ru.los.parser.pojo.fromemployees;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class Employee {

	@JacksonXmlProperty(localName = "name")
	private String name;
	@JacksonXmlProperty(localName = "post")
	private String post;
	@JacksonXmlProperty(localName = "email")
	private String email;
	@JacksonXmlProperty(localName = "departments")
	@Getter
	private List<Department> departments;

	public Employee() {
	}
}
