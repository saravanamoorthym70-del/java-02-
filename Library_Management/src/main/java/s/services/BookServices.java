package s.services;

import java.util.List;

import org.springframework.stereotype.Service;

import s.model.Author;
import s.model.book;
import s.repository.Bookrepository;

@Service
public class BookServices {
    private final Bookrepository repository;

    public BookServices(Bookrepository repository) {
        this.repository = repository;
    }

    public book getBook() {
        return new book("java", new Author("James Gosling", "james@gosling.com"), 599.0f);
    }

    public book addBook(book newBook) {
        return repository.save(newBook);
    }

    public book updateBook(Long id, book updateBook) {
        book existingBook = getBookById(id);
        existingBook.setTitle(updateBook.getTitle());
        existingBook.setAuthor(updateBook.getAuthor());
        existingBook.setPrice(updateBook.getPrice());
        return repository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        repository.deleteById(id);
    }

    public List<book> getAllBooks() {
        return repository.findAll();
    }

    public book getBookById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<book> getBooksByAuthor(String author) {
        return repository.findByAuthorName(author);
    }

    public List<book> getBooksByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<book> getBooksByPriceRange(float minPrice, float maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<book> getBooksByAuthorAndTitle(String author, String title) {
        return repository.findByAuthorNameAndTitle(author, title);
    }

    public List<book> getBooksByAuthorOrTitle(String author, String title) {
        return repository.findByAuthorNameOrTitle(author, title);
    }

    public List<book> getBooksByAuthorAndPriceRange(String author, float minPrice, float maxPrice) {
        return repository.findByAuthorNameAndPriceBetween(author, minPrice, maxPrice);
    }

    public List<book> getBooksByTitleAndPriceRange(String title, float minPrice, float maxPrice) {
        return repository.findByTitleAndPriceBetween(title, minPrice, maxPrice);
    }

    public List<book> getBooksByAuthorOrTitleAndPriceRange(String author, String title, float minPrice, float maxPrice) {
        return repository.findByAuthorNameOrTitleAndPriceBetween(author, title, minPrice, maxPrice);
    }

    public List<book> getBooksByAuthorAndTitleAndPriceRange(String author, String title, float minPrice, float maxPrice) {
        return repository.findByAuthorNameAndTitleAndPriceBetween(author, title, minPrice, maxPrice);
    }

    public List<book> getBooksByAuthorOrTitleOrPriceRange(String author, String title, float minPrice, float maxPrice) {
        return repository.findByAuthorNameOrTitleOrPriceBetween(author, title, minPrice, maxPrice);
    }

    public List<book> getBooksByAuthorAndTitleOrPriceRange(String author, String title, float minPrice, float maxPrice) {
        return repository.findByAuthorNameAndTitleOrPriceBetween(author, title, minPrice, maxPrice);
    }
}