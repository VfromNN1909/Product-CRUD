package ru.vlasoff.fullstackcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlasoff.fullstackcrud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
