package dao;

import java.util.List;

import entity.Photo;

public interface PhotoDao {
	List<Photo> searchByGoodsId(int gId);
}
