package org.gollum.simple.dto;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
public class NoteDTO {

    private long id;

    private String title;

    public NoteDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
