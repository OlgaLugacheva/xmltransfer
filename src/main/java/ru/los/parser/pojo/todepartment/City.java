package ru.los.parser.pojo.todepartment;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class City {

	@JacksonXmlProperty(localName = "name")
	private String name;

	@JacksonXmlProperty(localName = "count")
	private int count;

	@JacksonXmlElementWrapper(localName = "departments")
	@JacksonXmlProperty(localName = "department")
	private List<Departmentto> departments;
}
