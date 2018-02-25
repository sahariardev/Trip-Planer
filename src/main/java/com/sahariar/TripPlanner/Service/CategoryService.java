package com.sahariar.TripPlanner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.TripPlanner.Model.Category;
import com.sahariar.TripPlanner.Repositories.CategoryRepository;
import com.sahariar.TripPlanner.Requests.CategoryRequest;

@Service
public class CategoryService {

	@Autowired
    CategoryRepository cr;
	
	
	public Category Save(CategoryRequest request)
	{
		Category category=new Category();
		category.setName(request.getName());
		category.setDescription(request.getDescription());
		
		category=cr.save(category);
		return category;
	}
	public List<Category> findAll()
	{
		return cr.findAll();
	}
	
	public Category getOne(long id)
	{
		Category c= cr.getOne(id);
		System.out.println("Here aasdasd");
		
		try
		{
			String name=c.getName();
		}
		catch(Exception e)
		{
			System.err.println("here");
			c=null;
		}
		return c;
	}
	
}
