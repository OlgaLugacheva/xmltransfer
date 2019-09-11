package ru.los.parser.pojo.fromemployees;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public  class Department {
	@NonNull
	private String name;
	@NonNull
	private String city;

}
