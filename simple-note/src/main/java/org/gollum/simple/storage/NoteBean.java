package org.gollum.simple.storage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@Entity(name = "note")
public class NoteBean {

    @Id
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
