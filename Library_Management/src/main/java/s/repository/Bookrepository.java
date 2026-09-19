package s.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s.model.book;

public interface Bookrepository extends JpaRepository<book, Long> {
	List<book> findByAuthorName(String authorName);
	List<book> findByTitle(String title);
	List<book> findByPriceBetween(float minPrice, float maxPrice);
	List<book> findByAuthorNameAndTitle(String authorName, String title);
	List<book> findByAuthorNameOrTitle(String authorName, String title);
	List<book> findByAuthorNameAndPriceBetween(String authorName, float minPrice, float maxPrice);
	List<book> findByTitleAndPriceBetween(String title, float minPrice, float maxPrice);
	List<book> findByAuthorNameOrTitleAndPriceBetween(String authorName, String title, float minPrice, float maxPrice);
	List<book> findByAuthorNameAndTitleAndPriceBetween(String authorName, String title, float minPrice, float maxPrice);
	List<book> findByAuthorNameOrTitleOrPriceBetween(String authorName, String title, float minPrice, float maxPrice);
	List<book> findByAuthorNameAndTitleOrPriceBetween(String authorName, String title, float minPrice, float maxPrice);
}
