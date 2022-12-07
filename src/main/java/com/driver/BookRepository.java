package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
	Map<Integer,Book>books;

    public BookRepository(){
		books=new HashMap<>();
    }

    public Book save(Book book){
    	int number=books.size()+1;
    	book.setId(number);
    	books.put(number,book);
        return book;
    }

    public Book findBookById(int id){

        return books.get(id);
    }

    public List<Book> findAll(){
    	List<Book>book=new ArrayList<>();
    	for(Book b: books.values())
    		 book.add(b);
    	
        return book;
    }

    public void deleteBookById(int id){
    	books.remove(id);
        return;
    }

    public void deleteAll(){
    	books.clear();
        return;
    }

    public List<Book> findBooksByAuthor(String author){
    	List<Book>authors=new ArrayList<>();
    	for(Book b:books.values()) {
    		if(b.getAuthor().equals(author)) {
    			authors.add(b);
    		}
    	}
        return authors;
    }

    public List<Book> findBooksByGenre(String genre){
    	List<Book>authors=new ArrayList<>();
    	for(Book b:books.values()) {
    		if(b.getGenre().equals(genre)) {
    			authors.add(b);
    		}
    	}
        return authors;
    }
}
