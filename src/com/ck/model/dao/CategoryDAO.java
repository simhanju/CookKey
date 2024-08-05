package com.ck.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.ck.mybatis.SQLMapConfig;

public class CategoryDAO {
	private SqlSession ss;
	
	public CategoryDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	
	public boolean insertCategory(CategoryDTO cdto) {
		return ss.insert("Category.insert",cdto) == 1;
	}
	public boolean deleteCategory(CategoryDTO cdto) {
		return ss.delete("Category.delete",cdto) == 1;
	}
	public CategoryDTO getCategoryByNum(CategoryDTO category) {
	      return ss.selectOne("Category.selectCategorynum", category);
	   }
	public List<CategoryDTO> getCategories(CategoryDTO cdto) {
		return ss.selectList("Category.getCategories", cdto);
	}
	public List<CategoryDTO> getCategoryNum(CategoryDTO cdto) {
		return ss.selectList("Category.getCategoryNum", cdto);
	}

}
