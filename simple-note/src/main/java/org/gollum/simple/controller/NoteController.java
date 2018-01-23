package org.gollum.simple.controller;

import org.gollum.simple.application.NoteService;
import org.gollum.simple.dto.NoteDTO;
import org.gollum.simple.query.NoteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService service;

    @Autowired
    private NoteQuery query;

    @RequestMapping("/echo")
    public String echo(@RequestParam String value) {
        return value;
    }

    @RequestMapping("/create")
    public @ResponseBody String create(@RequestParam String title) {
        service.create(title);
        return "OK";
    }

    @RequestMapping("/list")
    public @ResponseBody Iterable<NoteDTO> list() {
        return query.findAll();
    }

    @RequestMapping("/change")
    public @ResponseBody String changeTitle(@RequestParam long id, @RequestParam String title) {
        service.changeTitle(id, title);
        return "OK";
    }

    @RequestMapping("/get")
    public @ResponseBody NoteDTO findOne(@RequestParam long id) {
        return query.findOne(id);
    }

}
