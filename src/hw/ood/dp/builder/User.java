package hw.ood.dp.builder;

import java.util.Date;

public class User {
	private final String firstName; // !! final
	private final String lastName; // !! final
	private int age;
	private String phone;
	private String address;
	private Gender gender;
	private Date LoginTime;
	private Date registrationTime;
	
	// Q: how to define its constructor? 
	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}
	
	
	// nested, static class
	public static class UserBuilder {
		private final String firstName; // these two are required!
		private final String lastName;
		private int age = 0; // default value is 0;
		private Gender gender;
		private String phone = ""; // default empty string
		private String address; // default null;
		
		public UserBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		// all the following methods are used to set values for optional fields
		public UserBuilder age(int age) {
			this.age = age;
			return this;  // return this to make expression chainable.
			
		}
		
		public UserBuilder gender(Gender gender) {
			this.gender = gender;
			return this;
		}
		
		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}
		
		// !!! key method to make builder create an user instance here
		public User build() {
			return new User(this);
		}
	}

	public static void main(String[] args) {
		User user = new UserBuilder("San", "Zhang")
				.age(25)
				.phone("12345678")
				.address("somewhere")
				.gender(Gender.Male)
				.build();
		
		System.out.println(user);
				
	}

}
