package com.cloudify.beans;

import com.cloudify.entities.LoyaltyMember;
import com.cloudify.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserServiceApiBean {

    //DELETE IT LATER, DONT NEED IT
    private List<User> users = new ArrayList<>();

    public UserServiceApiBean() {
        // Adding some example users for testing
        users.add(new User(1, "Mica Ivanovic", "mica@mejl", "066255255"));
        users.add(new User(2, "Pera Mijatovic", "pera@mejl", "061288888"));
        users.add(new User(3, "Grujica Sakic", "grujica@mejl", "061266666"));
        users.add(new User(4, "Mojca Jesenk", "mojca@mejl", "061458459"));
    }

    public List<LoyaltyMember> listUsers(int limit, int offset) {
        // Example mock data (replace with actual database logic)
        User mica = new User(1, "Mica Ivanovic", "mica@mejl", "066255255");
        User pera = new User(2, "Pera Mijatovic", "pera@mejl", "061288888");
        User grujica = new User(3, "Grujica Sakic", "grujica@mejl", "061266666");
        User mojca = new User(4, "Mojca Jesenk", "mojca@mejl", "061458459");
        LoyaltyMember member = new LoyaltyMember(1, mica, 99);
        LoyaltyMember member2 = new LoyaltyMember(2, pera, 109);
        LoyaltyMember member3 = new LoyaltyMember(3, grujica, 188);
        LoyaltyMember member4 = new LoyaltyMember(4, mojca, 2009);

        return new ArrayList<LoyaltyMember>() {{
            add(member);
            add(member2);
            add(member3);
            add(member4);
        }};
    }

    public User createUser(User user) {
        // Logic for creating a new user (e.g., database insertion)
        return user;
    }

    public User getUser(int userId) {
        // Example mock data (replace with actual database logic)
        Optional<User> user = users.stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst();

        return user.orElse(null);
    }

    public User updateUser(int userId, User user) {
        // Logic for updating user details
        return user;
    }

    public void deleteUser(int userId) {
        // Logic for deleting a user
    }
}
