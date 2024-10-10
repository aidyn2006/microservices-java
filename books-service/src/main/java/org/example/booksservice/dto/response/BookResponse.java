package org.example.booksservice.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    String title;
    String author;
    String genre;
    String description;
    String imgUrl;
    String pdfUrl;
}
