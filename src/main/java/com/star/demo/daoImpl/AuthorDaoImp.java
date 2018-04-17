// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-31, derrick.liang, creation
// ============================================================================
package com.star.demo.daoImpl;

import com.star.demo.dao.AuthorDao;
import com.star.demo.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author derrick.liang
 */

@Repository
public class AuthorDaoImp implements AuthorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Author author) {
//        String id = UUID.randomUUID().toString();
        String sql = "insert into t_author(id, real_name, nick_name) values(?, ?, ?)";
        return jdbcTemplate.update(sql, author.getId(), author.getRealName(), author.getNickName());
    }

    @Override
    public int update(Author author) {
        String sql = "update t_author set real_name = ?, nick_name = ? where id = ?";
        return jdbcTemplate.update(sql, author.getRealName(), author.getNickName(), author.getId());
    }

    @Override
    public int delete(String id) {
        String sql = "delete from t_author where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Author findAuthor(String id) {
        String sql = "select * from t_author where id = ?";
        List<Author> list;
        list = jdbcTemplate.query(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Author.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Author> findAuthorList() {
        String sql = "select * from t_author";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class));
    }
}
