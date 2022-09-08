package net.archasmiel.bot.model;

import java.util.*;

public class UserList {

	private final Map<String, String> users;

	public UserList() {
		users = new HashMap<>();
	}

	public void add(String id, String url) {
		users.put(id, url);
	}

	public String getUrl(String id) {
		String res = users.get(id);
		if (res == null)
			throw new IllegalStateException("No user found");
		return res;
	}

	@Override
	public String toString() {
		return users.toString();
	}
}
