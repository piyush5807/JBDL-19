package com.example.jbdl.demojpa.repositories;

import com.example.jbdl.demojpa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query("select * from Book b where b.authorName = :author_name")
    @Query(value = "select * from books_table b where b.author_name = :author_name", nativeQuery = true)
    List<Book> findBookByAuthorName(String author_name);

    @Query("select b from Book b where b.cost < ?1")
    List<Book> findBooksByCost(int cost);

    @Modifying
    @Transactional
    @Query(value = "update books_table b set b.cost = ?2 where b.id = ?1", nativeQuery = true)
    void updateBook(int bookId, int updatedCost);


    // getBy, findBy - property name then it's going to work otherwise it won't
    @Query(value = "select * from books_table b where b.book_name = :book_name", nativeQuery = true)
    List<Book> findByBookName(String book_name);

    // find all the books written by a particular author

    // find all the books which are written by a particular author and the cost to be greater than some given cost

    // 1. JPQL - Java persistence query language
    // 2. Native SQL -
}
