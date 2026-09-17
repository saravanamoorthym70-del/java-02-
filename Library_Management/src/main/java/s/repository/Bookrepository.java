package s.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s.model.book;

public interface Bookrepository extends JpaRepository<book, Long> {
	List<book> findByAuthor(String author);
	List<book> findByTitle(String title);
	List<book> findByPriceBetween(float minPrice, float maxPrice);
	List<book> findByAuthorAndTitle(String author, String title);
	List<book> findByAuthorOrTitle(String author, String title);
	List<book> findByAuthorAndPriceBetween(String author, float minPrice, float maxPrice);
	List<book> findByTitleAndPriceBetween(String title, float minPrice, float maxPrice);
	List<book> findByAuthorOrTitleAndPriceBetween(String author, String title, float minPrice, float maxPrice);
	List<book> findByAuthorAndTitleAndPriceBetween(String author, String title, float minPrice, float maxPrice);
	List<book> findByAuthorOrTitleOrPriceBetween(String author, String title, float minPrice, float maxPrice);
	List<book> findByAuthorAndTitleOrPriceBetween(String author, String title, float minPrice, float maxPrice);
}
