package org.pkuse2020grp4.pkusporteventsbackend.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super(String.format("User name `%s` not found!", username));
    }
    public UserNotFoundException(int userId) {
        super(String.format("User ID `%d` not found!", userId));
    }
}
