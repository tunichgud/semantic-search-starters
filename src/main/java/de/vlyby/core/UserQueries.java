package de.vlyby.core;

import java.util.List;

public class UserQueries {

    List<UserQuery> userQueries;

    public UserQueries(List<UserQuery> userQueries) {
        this.userQueries = userQueries;
    }

    public List<UserQuery> getUserQueries() {
        return userQueries;
    }

}
