// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-31, derrick.liang, creation
// ============================================================================
package com.star.demo.dao;

import com.star.demo.domain.Author;

import java.util.List;

/**
 * @author derrick.liang
 */
public interface AuthorDao {
    int add(Author author);

    int update(Author author);

    int delete(String id);

    Author findAuthor(String id);

    List<Author> findAuthorList();
}
