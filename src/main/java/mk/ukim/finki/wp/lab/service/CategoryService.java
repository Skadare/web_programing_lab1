package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findbyId(Long id);
}
