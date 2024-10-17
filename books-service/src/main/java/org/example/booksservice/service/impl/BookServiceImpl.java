package org.example.booksservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.entity.Book;
import org.example.booksservice.repository.BookRepository;
import org.example.booksservice.service.BookService;
import org.example.booksservice.service.IdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final Cloudinary cloudinary;
    private final BookRepository bookRepository;
    private final IdService idService;

    @SneakyThrows
    @Transactional
    public Book createBook(BookDto bookDto) {
        Optional<Book> book = bookRepository.findByTitle(bookDto.getTitle());
        if (book.isPresent()) {
            throw new IllegalArgumentException("Эта книга уже присутствует");
        }

        Map<String, Object> uploadImg = cloudinary.uploader().upload(bookDto.getImgUrl().getBytes(), ObjectUtils.emptyMap());
        Map<String, Object> uploadPdf = cloudinary.uploader().upload(bookDto.getPdfUrl().getBytes(),
                ObjectUtils.asMap("resource_type", "raw"));

        Book newBook = Book.builder()
                .genre(bookDto.getGenre())
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .pdfUrl(uploadPdf.get("url").toString())
                .author(bookDto.getAuthor())
                .imgUrl(uploadImg.get("url").toString())
                .createdAt(LocalDateTime.now())
                .build();


        String genre= idService.getSubscribers();
        if (genre.equalsIgnoreCase(newBook.getGenre())){
            idService.sendMessage();
        }
        return bookRepository.save(newBook);
    }


    public BookResponse mapBookToResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .genre(book.getGenre())
                .imgUrl(book.getImgUrl())
                .pdfUrl(book.getPdfUrl())
                .build();
    }

    public List<BookResponse> getBooksByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds)
                .stream()
                .map(this::mapBookToResponse)
                .collect(Collectors.toList());
    }
}
