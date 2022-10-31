package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.response.CategoryResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.mapper.CategoryMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public CategoryResponseDto add(@RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryService.save(categoryMapper.mapToModel(requestDto));
        return categoryMapper.mapToDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto get(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return categoryMapper.mapToDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CategoryRequestDto requestDto) {
        Category category = categoryMapper.mapToModel(requestDto);
        category.setId(id);
        return categoryMapper.mapToDto(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
