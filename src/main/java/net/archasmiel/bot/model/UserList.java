package net.archasmiel.bot.model;

import java.util.*;
import java.util.stream.Collectors;

public class UserList {

	private final Map<String, UserData> users;

	public UserList() {
		users = new HashMap<>();
	}



	public Map<String, UserData> getUsers() {
		return users;
	}



	public void add(String id, String url, List<String> restricted) {
		if (users.containsKey(id)) {
			UserData data = users.get(id);
			data.setUrl(url);
			data.setRestricted(restricted);
			return;
		}
		users.put(id, new UserData(url, restricted));
	}

	public void add(long userID, String url, List<String> restricted) {
		String id = String.valueOf(userID);
		if (users.containsKey(id)) {
			UserData data = users.get(id);
			data.setUrl(url);
			data.setRestricted(restricted);
			return;
		}
		users.put(id, new UserData(url, restricted));
	}

	public void add(String id, String url) {
		add(id, url, new ArrayList<>());
	}

	public void add(String id, String url, String... restricted) {
		add(id, url, Arrays.stream(restricted).collect(Collectors.toCollection(ArrayList::new)));
	}

	public void add(long userID, String url) {
		add(userID, url, new ArrayList<>());
	}

	public void add(long userID, String url, String... restricted) {
		add(userID, url, Arrays.stream(restricted).collect(Collectors.toCollection(ArrayList::new)));
	}

	public String getUrl(String id) {
		String res = users.get(id).getURL();
		if (res == null)
			throw new IllegalStateException("No user found");
		return res;
	}

	public List<String> getRestricted(String id) {
		return users.get(id).getRestricted();
	}

	@Override
	public String toString() {
		return users.toString();
	}
}
