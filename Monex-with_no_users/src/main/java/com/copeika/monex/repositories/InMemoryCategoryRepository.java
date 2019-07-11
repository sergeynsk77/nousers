package com.copeika.monex.repositories;


import com.copeika.monex.exception.AlreadyExistException;
import com.copeika.monex.exception.NotFoundException;
import com.copeika.monex.exception.NotValidValue;
import com.copeika.monex.models.Category;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {

    private Map<String, Category> categoryCache = new HashMap<>();

    public InMemoryCategoryRepository(){

    }

    @Override
    public Category addCategory(String name) {
        if (categoryCache.containsKey(name)) {
            throw new AlreadyExistException();
        }
        Category category = new Category();
        category.setName(name);
        category.setMonetaryExpenditures(0);
        categoryCache.put(name, category);
        return category;
    }

    @Override
    public void deleteCategory(String name) {
        if (!categoryCache.containsKey(name)) {
            throw new NotFoundException();
        }
        categoryCache.remove(name);
    }

    @Override
    public Category renameCategory(String name, String after_name) {
        if (!categoryCache.containsKey(name)) {
            throw new NotFoundException();
        }
        Category category = categoryCache.get(name);
        category.setName(after_name);
        categoryCache.remove(name);
        categoryCache.put(after_name, category);
        return category;
    }

    @Override
    public Category fetchCategory(String name) {
        if (!categoryCache.containsKey(name)) {
            throw new NotFoundException();
        }
        return categoryCache.get(name);
    }

    @Override
    public Category setLimit(String name, Integer limit) {
        if(!(limit>=1 && limit <= 10000000)) {
            throw new NotValidValue();
        }
        if (!categoryCache.containsKey(name)) {
            throw new NotFoundException();
        }
        Category category = categoryCache.get(name);
        category.setLimit(limit);
        categoryCache.put(name, category);
        return  category;
    }

    @Override
    public Category addMonetaryExpenditures(String name, Integer money_expenditures) {
        if(!(money_expenditures>=1 && money_expenditures <= 10000000)) {
            throw new NotValidValue();
        }

        if (!categoryCache.containsKey(name)) {
            throw new NotFoundException();
        }
        Category category = categoryCache.get(name);
        category.addMonetaryExpenditures(money_expenditures);
        categoryCache.put(name, category);
        return category;
    }

    @Override
    public Category refreshMonetaryExpenditures(String name) {
        if (!categoryCache.containsKey(name)) {
            throw new NotFoundException();
        }
        Category category = categoryCache.get(name);
        Integer temp = 0;
        category.setMonetaryExpenditures(temp);
        categoryCache.put(name, category);
        return category;
    }

    @Override
    public Collection<Category> getAllBooks() {
        return categoryCache.values();
    }
}
