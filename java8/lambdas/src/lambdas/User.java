package lambdas;

import java.util.Collection;

public class User {
	private String name;
	private int age;
	private Collection<Role> roles;

	public String getName() {
		return name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

class Role {
	private String name;
	private Collection<Permission> permissions;

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Permission {

	public static final Object EDIT = null;
	
}
