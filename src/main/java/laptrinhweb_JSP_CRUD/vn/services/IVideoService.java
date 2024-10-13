package laptrinhweb_JSP_CRUD.vn.services;

import java.util.List;

import laptrinhweb_JSP_CRUD.vn.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoTitle(String videoTitle);

	List<Video> findAll();

	Video findById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);
	
	List<Video> findByIdCategory(int cateid);
}
