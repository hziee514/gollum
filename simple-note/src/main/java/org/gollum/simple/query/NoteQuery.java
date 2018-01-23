package org.gollum.simple.query;

import org.gollum.common.util.Assertion;
import org.gollum.simple.Singleton;
import org.gollum.simple.dto.NoteDTO;
import org.gollum.simple.storage.NoteBean;
import org.gollum.simple.storage.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@Component
@Singleton
public class NoteQuery {

    @Autowired
    private NoteDao dao;

    public List<NoteDTO> findAll() {
        List<NoteDTO> result = new ArrayList<>();
        dao.findAll().forEach(a -> result.add(new NoteDTO(a.getId(), a.getTitle())));
        return result;
    }

    public NoteDTO findOne(long id) {
        NoteBean bean = dao.findOne(id);
        Assertion.notNull(bean, "NoteBean[%d]", id);
        return new NoteDTO(bean.getId(), bean.getTitle());
    }

}
