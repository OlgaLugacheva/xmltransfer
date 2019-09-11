package ru.los.parser.pojo.todepartment;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Departmentto {

	private String name;

	@JacksonXmlElementWrapper(localName = "employees")
	@JacksonXmlProperty(localName = "employee")
	private List<Employeeto> employees;

}
