package laptrinhweb_JSP_CRUD.vn.services.impl;

import java.util.List;

import laptrinhweb_JSP_CRUD.vn.dao.IVideoDao;
import laptrinhweb_JSP_CRUD.vn.dao.impl.VideoDao;
import laptrinhweb_JSP_CRUD.vn.entity.Video;
import laptrinhweb_JSP_CRUD.vn.services.IVideoService;

public class VideoService implements IVideoService{

	IVideoDao videoDao = new VideoDao();
	
	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByVideoTitle(String videoTitle) {
		return videoDao.findByVideoTitle(videoTitle);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(String videoid) {
		return videoDao.findById(videoid);
	}

	@Override
	public void delete(String videoid) throws Exception {
		videoDao.delete(videoid);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
	}

	@Override
	public List<Video> findByIdCategory(int cateid) {
		return videoDao.findByIdCategory(cateid);
	}

}
