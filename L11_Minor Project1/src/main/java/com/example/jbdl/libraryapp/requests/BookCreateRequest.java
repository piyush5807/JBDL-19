package com.example.jbdl.libraryapp.requests;

import com.example.jbdl.libraryapp.models.Book;
import com.example.jbdl.libraryapp.models.Genre;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    // These are books parameter
    private String name;
    private Genre genre;
    private int cost;

    // These are needed for author creation if required
    private String authorName;
    private String authorCountry;
    private int authorAge;
    private String authorEmail;

    
    public Book toBook(){
        return Book.builder()
                .cost(this.getCost())
                .name(this.getName())
                .genre(this.getGenre())
                .available(true)
                .build();

    }

}
