package ru.los.parser.pojo.fromemployees;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

@JacksonXmlRootElement( localName = "employees")
public class Employees {


	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "employee")
	private List<Employee> employees;


}
