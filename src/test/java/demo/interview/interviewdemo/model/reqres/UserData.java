package demo.interview.interviewdemo.model.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {
	private String lastName;
	private Integer id;
	private String avatar;
	private String firstName;
	private String email;

	public UserData() {}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	public Integer getId() {
		return id;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getEmail() {
		return email;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
