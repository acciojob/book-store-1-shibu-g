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
     
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
    	Book newbook=new Book(book.getName(),book.getGenre(),book.getAuthor());
    	newbook.setId(id);
    	++id;
    	bookList.add(newbook);
    	
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }
    
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getbookbyid(@PathVariable int id){
    	Book b1=new Book();
    	for(Book b:bookList) {
    		if(b.getId()==id) {
    			b1=b;
    			break;
    		}
    	}
    	
    	return new ResponseEntity<>(b1, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> get_all_books(){
    	List<Book>books=new ArrayList();
    	for(Book b:bookList) {
    		books.add(b);
    	}
    	
    	return new ResponseEntity(books,HttpStatus.FOUND);
    }
    
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> get_book_by_author(@RequestParam String author){
    	List<Book>books=new ArrayList();
    	for(Book b:bookList) {
    		if(b.getAuthor().equals(author)) {
    			books.add(b);
    		}
    	}
    	
    	
    	return new ResponseEntity(books,HttpStatus.FOUND);
    }
    
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> get_book_by_genre(@RequestParam String genre){
    	List<Book>books=new ArrayList();
    	for(Book b:bookList) {
    		if(b.getGenre().equals(genre)) {
    			books.add(b);
    		}
    	}
    	return new ResponseEntity(books,HttpStatus.FOUND);
    }
    
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deletebyid(@PathVariable int id) {
    	for(Book b:bookList) {
    		if(b.getId()==id) {
    			bookList.remove(b);
    			break;
    		}
    	}
    	return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deletealbooks() {
    	
    	bookList.clear();
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
