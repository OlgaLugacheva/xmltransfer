package ru.los.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import ru.los.parser.pojo.fromemployees.Employee;
import ru.los.parser.pojo.fromemployees.Employees;
import ru.los.parser.pojo.todepartment.Cities;
import ru.los.parser.pojo.todepartment.City;
import ru.los.parser.pojo.todepartment.Departmentto;
import ru.los.parser.pojo.todepartment.Employeeto;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.log4j.helpers.UtilLoggingLevel.SEVERE;


public class Transfer {
	private static final Logger LOGGER = Logger.getLogger(Transfer.class);

	public static void main(String[] args) {
		try {
			serializeToXml(deserializeFromXML("employees.xml"), "departments.xml");
		} catch (IOException e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
		}

	}

	/**
	 * Метод десериализации XML
	 *
	 * @param fileName имя файла
	 */
	public static Map<String, Map<String, List<Employee>>> deserializeFromXML(String fileName) {

		validateXml(fileName);

		Map<String, Map<String, List<Employee>>> cityMAp = new HashMap<>();
		try {

			XmlMapper xmlMapper = new XmlMapper();
			String readContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(fileName).toURI())));
			Employees deserializedData = xmlMapper.readValue(readContent, Employees.class);
			Map<String, List<Employee>> departmantList = new HashMap<>();
			deserializedData.getEmployees().forEach(employee -> employee.getDepartments().forEach(department -> {
				{
					if (departmantList.containsKey(department.getName())) {
						departmantList.get(department.getName()).add(employee);
					} else {
						departmantList.put(department.getName(), new ArrayList<>(Collections.singletonList(employee)));
					}
				}
				if (cityMAp.containsKey(department.getCity())) {
					cityMAp.get(department.getCity()).put(department.getName(), departmantList.get(department.getName()));
				} else {
					HashMap<String, List<Employee>> deps = new HashMap<>();
					deps.put(department.getName(), departmantList.get(department.getName()));
					cityMAp.put(department.getCity(), deps);
				}
			}));

		} catch (IOException | URISyntaxException e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
		}
		return cityMAp;
	}

	private static void validateXml(String fileName) {
		File xml;
		try {
			xml = new File(Paths.get(ClassLoader.getSystemResource(fileName).toURI()).toString());

			File xsd = new File(Paths.get(ClassLoader.getSystemResource("employees.xsd").toURI()).toString());

			SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
							.newSchema(xsd)
							.newValidator()
							.validate(new StreamSource(xml));
		} catch (SAXException | IOException | URISyntaxException e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Сериализация в XML
	 */
	public static void serializeToXml(Map<String, Map<String, List<Employee>>> cityMAp, String fileName) throws IOException {
		XmlMapper xmlMapper;
		List<City> cityList = new ArrayList<>();
		cityMAp.forEach((city, departments) -> {
			List<Departmentto> departmentList = new ArrayList<>();
			departments.forEach((department, value) -> {
				List<Employeeto> employList = value.stream().map(employee -> new Employeeto(employee.getName(), employee.getPost(), employee.getEmail())).collect(Collectors.toList());
				departmentList.add(new Departmentto(department, employList));
			});
			cityList.add(new City(city, departmentList.size(), departmentList));
		});

		Cities cities = new Cities(cityList);
		xmlMapper = new XmlMapper();
		xmlMapper.writeValue(new File(fileName), cities);
	}
}
