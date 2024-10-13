package laptrinhweb_JSP_CRUD.vn.dao;

import java.util.List;

import laptrinhweb_JSP_CRUD.vn.entity.Category;

public interface ICategoryDAO {
	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> findByCategoryname(String catname);

	List<Category> findAll();

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category category);

	void insert(Category category);
}
