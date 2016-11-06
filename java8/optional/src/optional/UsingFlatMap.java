package optional;

import static java.util.Optional.*;

import java.util.Optional;

public class UsingFlatMap {

	public String getPersonCarInsuranceName(Person person) {
		return ofNullable(person)
				.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse("Unknown");
	}

	public class Person {

		public Optional<Car> getCar() {
			return null;
		}

	}

	public class Car {

		public Optional<Insurance> getInsurance() {
			return null;
		}

	}

	public class Insurance {

		private String name;

		public String getName() {
			return name;
		}
	}

}
