package org.pkuse2020grp4.pkusporteventsbackend.exception;

public class TagIdNotFoundException extends Exception {
    public TagIdNotFoundException(int tagId) {
        super(String.format("Provided tag ID `%d` not found.", tagId));
    }
    public TagIdNotFoundException() {
        super(String.format("Provided tag ID not found. Please check tag IDs!"));
    }
}
