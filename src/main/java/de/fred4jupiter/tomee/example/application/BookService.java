/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package de.fred4jupiter.tomee.example.application;

import de.fred4jupiter.tomee.example.entities.Book;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;
import java.util.List;

@Stateless
public class BookService {

    @PersistenceContext(unitName = "book-pu")
    private EntityManager entityManager;

    @Resource
    private DataSource myDataSource;

    public void addBook(Book book) {
        entityManager.persist(book);

        // just for demo
        if (myDataSource != null) {
            System.out.println("DataSource injected");
        }
    }

    public List<Book> getAllBooks() {
        CriteriaQuery<Book> cq = entityManager.getCriteriaBuilder().createQuery(Book.class);
        cq.select(cq.from(Book.class));
        return entityManager.createQuery(cq).getResultList();
    }
}
