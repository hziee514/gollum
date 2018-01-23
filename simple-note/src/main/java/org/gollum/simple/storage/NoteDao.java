package org.gollum.simple.storage;

import org.springframework.data.repository.CrudRepository;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
public interface NoteDao extends CrudRepository<NoteBean, Long> {
}
