package s.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s.model.book;
import s.services.BookServices;

@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookServices bookServices;

	public BookController(BookServices bookServices) {
		this.bookServices = bookServices;
	}

	@GetMapping
	public List<book> getAllBooks() {
		return bookServices.getAllBooks();
	}

	@GetMapping("/{id}")
	public book getBookById(@PathVariable Long id) {
		return bookServices.getBookById(id);
	}

	@PostMapping
	public book addBook(@RequestBody book book) {
		return bookServices.addBook(book);
	}

	@PutMapping("/{id}")
	public book updateBook(@PathVariable Long id, @RequestBody book book) {
		return bookServices.updateBook(id, book);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookServices.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
}
