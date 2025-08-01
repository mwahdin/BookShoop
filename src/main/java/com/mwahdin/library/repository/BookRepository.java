package com.mwahdin.library.repository;

import com.mwahdin.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);

    @Query("select b from Book b where b.name like CONCAT('%',:name, '%') ")
    List<Book> findByTitleContaining(String name);

}
