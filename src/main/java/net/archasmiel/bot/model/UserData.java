package net.archasmiel.bot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserData {

	private String url;
	private List<String> restricted;

	public UserData(String url) {
		this.url = url;
		this.restricted = new ArrayList<>();
	}

	public UserData(String url, String... restricted) {
		this.url = url;
		this.restricted = Stream.of(restricted).collect(Collectors.toCollection(ArrayList::new));
	}

	public UserData(String url, List<String> restricted) {
		this.url = url;
		this.restricted = restricted;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRestricted(List<String> restricted) {
		this.restricted = restricted;
	}

	public void setRestricted(String... restricted) {
		this.restricted = Stream.of(restricted).collect(Collectors.toCollection(ArrayList::new));
	}

	public String getURL() {
		return url;
	}

	public List<String> getRestricted() {
		return restricted;
	}

	public boolean restrict(String lesson) {
		if (restricted.size() <= 10) {
			restricted.add(lesson);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "UserData{" +
			"url='" + url + '\'' +
			", restricted=" + restricted +
			'}';
	}
}
