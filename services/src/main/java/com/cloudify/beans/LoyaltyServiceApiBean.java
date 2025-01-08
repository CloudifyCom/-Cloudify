package com.cloudify.beans;

import com.cloudify.entities.LoyaltyMember;
import com.cloudify.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LoyaltyServiceApiBean {

    public List<LoyaltyMember> listLoyaltyMembers() {
        // Example mock data (replace with actual database retrieval logic)
        User mica = new User(1, "Mica", "mica@mejl", "99");
        User pera = new User(2, "Pera", "pera@mejl", "109");
        LoyaltyMember member = new LoyaltyMember(1, mica, 99);
        LoyaltyMember member2 = new LoyaltyMember(2, pera, 109);

        return new ArrayList<LoyaltyMember>() {{
            add(member);
            add(member2);
        }};
    }

    public LoyaltyMember enrollUser(LoyaltyMember member) {
        // Logic to add a user to the loyalty program (e.g., save to the database)
        return member;
    }
}
