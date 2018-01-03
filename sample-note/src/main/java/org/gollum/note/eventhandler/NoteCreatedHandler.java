package org.gollum.note.eventhandler;

import org.gollum.note.Singleton;
import org.gollum.note.domain.NoteCreated;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
@Component
@Singleton
public class NoteCreatedHandler extends LoggingEventHandler<NoteCreated> {

}
