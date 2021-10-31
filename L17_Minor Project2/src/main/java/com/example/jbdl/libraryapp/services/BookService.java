package com.example.jbdl.libraryapp.services;

import com.example.jbdl.libraryapp.models.Author;
import com.example.jbdl.libraryapp.models.Book;
import com.example.jbdl.libraryapp.repositories.BookRepository;
import com.example.jbdl.libraryapp.requests.BookCreateRequest;
import com.example.jbdl.libraryapp.requests.BookUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public void addBook(BookCreateRequest bookCreateRequest){

        // Author object -> save it in the db
        // Book Object + Link the authorId
        // Save the book object

        Author author = authorService.getAuthorByEmail(bookCreateRequest.getAuthorEmail());

        if(author == null){
            author = Author.builder()
                .name(bookCreateRequest.getAuthorName())
                .age(bookCreateRequest.getAuthorAge())
                .country(bookCreateRequest.getAuthorCountry())
                .email(bookCreateRequest.getAuthorEmail())
                .build();

            author = authorService.addAuthor(author);
        }

        Book book = bookCreateRequest.toBook();
        book.setAuthor(author);

        bookRepository.save(book);
    }

    // select * from book where id = 1;
    public Book getBookById(int id) throws ChangeSetPersister.NotFoundException {
//        return bookRepository.findById(id).orElse(null); // Find by primary key

        return bookRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public Book addOrUpdateBook(Book book){
       return  bookRepository.save(book);
    }

    public void updateBook(int bookId, BookUpdateRequest bookUpdateRequest){
        // cost - present, genre - not present, name - not present
        // update book b set b.cost = :cost, b.genre = :genre, b.name = :name where b.id = :id

        bookRepository.updateBook(bookUpdateRequest.getCost(), bookUpdateRequest.getGenre(), bookUpdateRequest.getName(), bookId);

//        String q = "update Book b set ";
//        if(bookUpdateRequest.getCost() != null){
//            q += " b.cost = " + bookUpdateRequest.getCost();
//        }
//
//        if(bookUpdateRequest.getName() != null){
//            q += " b.name = " + bookUpdateRequest.getName();
//        }
//
//        if(bookUpdateRequest.getGenre() != null){
//            q += " b.genre = " + bookUpdateRequest.getGenre();
//        }
//
//        q += " where b.id " + bookId;


    }
}
