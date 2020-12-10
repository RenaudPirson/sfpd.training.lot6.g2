package be.sfpd.blog.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import be.sfpd.blog.model.Profile;
import be.sfpd.blog.repository.MockDatabase;

public class ProfileService {
	static Map<Long, Profile> data =  MockDatabase.getProfiles();

	public ProfileService(){
		data.put(1l, new Profile("X1RPN", "x1rpn@sfpd.be", 1, LocalDate.now()));
		data.put(2l, new Profile("X1VBA", "x1vba@sfpd.be", 2, LocalDate.now()));
	}

	public Collection<Profile> getProfiles() {
		return data.values();
	}

	public Optional<Profile> getProfileByName(String name) {
		return data.values().stream().filter(p -> p.getUserName().equals(name)).findFirst();
	}
}
