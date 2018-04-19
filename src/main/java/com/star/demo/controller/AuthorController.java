// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-31, derrick.liang, creation
// ============================================================================
package com.star.demo.controller;

import com.star.demo.domain.Author;
import com.star.demo.service.AuthorService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author derrick.liang
 */

@RestController
@RequestMapping(value = "/author")
public class AuthorController {
    @Autowired private AuthorService authorService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Author author) {
        String id = author.getId();
        if (Strings.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        author.setId(id);
        authorService.add(author);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Author get(@PathVariable(value = "id") String id) {
        return authorService.findAuthor(id);
    }

    @RequestMapping(value = "/getAll")
    public List<Author> getAll() {
        return authorService.findAutorList();
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        authorService.delete(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public void update(@RequestBody Author author, @PathVariable(value = "id") String id) {
        author.setId(id);
        authorService.update(author);
    }

}

