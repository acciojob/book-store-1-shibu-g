package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

	
	@Autowired
	BookService service;
    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        return new ResponseEntity<>(service.createBook(book), HttpStatus.CREATED);
    }
    
    @GetMapping("/get-book-bt-id/{id}")
    public ResponseEntity<Book> getbookbyid(@PathVariable int id){
    	return new ResponseEntity<>(service.findBookById(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> get_all_books(){
    	return new ResponseEntity(service.findAllBooks(),HttpStatus.FOUND);
    }
    
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> get_book_by_author(@RequestParam String author){
    	return new ResponseEntity(service.findBooksByAuthor(author),HttpStatus.FOUND);
    }
    
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> get_book_by_genre(@RequestParam String genre){
    	return new ResponseEntity(service.findBooksByGenre(genre),HttpStatus.FOUND);
    }
    
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deletebyid(@PathVariable int id) {
    	service.deleteBookById(id);
    	return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deletealbooks() {
    	service.deleteAllBooks();
    	return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()

    // get request /get-all-books
    // getAllBooks()

    // delete request /delete-all-books
    // deleteAllBooks()

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
}
