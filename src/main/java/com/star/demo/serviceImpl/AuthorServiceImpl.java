// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-31, derrick.liang, creation
// ============================================================================
package com.star.demo.serviceImpl;

import com.star.demo.dao.AuthorDao;
import com.star.demo.domain.Author;
import com.star.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author derrick.liang
 */

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public int add(Author author) {
        return authorDao.add(author);
    }

    @Override
    public int update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public int delete(String id) {
        return authorDao.delete(id);
    }

    @Override
    public Author findAuthor(String id) {
        return authorDao.findAuthor(id);
    }

    @Override
    public List<Author> findAutorList() {
        return authorDao.findAuthorList();
    }
}
