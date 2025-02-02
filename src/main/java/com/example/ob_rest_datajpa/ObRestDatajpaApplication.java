package com.example.ob_rest_datajpa;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository=context.getBean(BookRepository.class);

		//CRUD
		//crear libro
		Book book1=new Book(null,"Homo Deus","Yuval Noah",450,29.99, LocalDate.of(2000,12,1),true);
		Book book2=new Book(null,"Homo Sapiens","Yuval Noah",450,19.99, LocalDate.of(2008,12,1),true);

		//almacenar libro
		repository.save(book1);
		repository.save(book2);

		//recuperar todos los libros
		System.out.println("Numero de libros en base de datoa: "+repository.findAll().size());

		//borrar un libro
		//repository.deleteById(1L);
		System.out.println("Numero de libros en base de datoa: "+repository.findAll().size());
	}

}
