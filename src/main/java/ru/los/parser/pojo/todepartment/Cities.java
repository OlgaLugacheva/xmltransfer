package ru.los.parser.pojo.todepartment;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor

@JacksonXmlRootElement(localName = "cities")
public class Cities {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "city")
	private List<City> cities;

}
