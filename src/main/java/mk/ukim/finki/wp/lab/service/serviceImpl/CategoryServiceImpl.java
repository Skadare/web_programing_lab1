package mk.ukim.finki.wp.lab.service.serviceImpl;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.lab.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    @Override
    public Optional<Category> findbyId(Long id) {
        return categoryRepository.findById(id);
    }
}
