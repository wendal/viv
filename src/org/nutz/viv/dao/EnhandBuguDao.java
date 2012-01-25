package org.nutz.viv.dao;

import java.util.List;

import com.bugull.mongo.AdvancedDao;
import com.mongodb.DBObject;

@SuppressWarnings("unchecked")
public class EnhandBuguDao<T> extends AdvancedDao {

	public EnhandBuguDao(Class<T> clazz) {
		super(clazz);
	}

	@Override
	public List<T> find(DBObject query) {
		return super.find(query);
	}
	
	@Override
	public List<T> find(DBObject query, DBObject orderBy) {
		return super.find(query, orderBy);
	}

	@Override
	public List<T> find(DBObject query, DBObject orderBy, int pageNum, int pageSize) {
		return super.find(query, orderBy, pageNum, pageSize);
	}

	@Override
	public List<T> find(DBObject query, int pageNum, int pageSize) {
		return super.find(query, pageNum, pageSize);
	}

	@Override
	public List<T> find(DBObject query, String orderBy, int pageNum, int pageSize) {
		return super.find(query, orderBy, pageNum, pageSize);
	}

	@Override
	public List<T> find(DBObject query, String orderBy) {
		return super.find(query, orderBy);
	}

	@Override
	public List<T> find(String key, Object value, int pageNum, int pageSize) {
		return super.find(key, value, pageNum, pageSize);
	}

	@Override
	public List<T> find(String key, Object value, String orderBy, int pageNum,
			int pageSize) {
		return super.find(key, value, orderBy, pageNum, pageSize);
	}

	@Override
	public List<T> find(String key, Object value, String orderBy) {
		return super.find(key, value, orderBy);
	}

	@Override
	public List<T> find(String key, Object value) {
		return super.find(key, value);
	}

	@Override
	public List<T> findAll() {
		return super.findAll();
	}

	@Override
	public List<T> findAll(DBObject orderBy, int pageNum, int pageSize) {
		return super.findAll(orderBy, pageNum, pageSize);
	}

	@Override
	public List<T> findAll(DBObject orderBy) {
		return super.findAll(orderBy);
	}

	@Override
	public List<T> findAll(int pageNum, int pageSize) {
		return super.findAll(pageNum, pageSize);
	}

	@Override
	public List<T> findAll(String orderBy, int pageNum, int pageSize) {
		return super.findAll(orderBy, pageNum, pageSize);
	}

	@Override
	public List<T> findAll(String orderBy) {
		return super.findAll(orderBy);
	}

	@Override
	public List<T> findForLucene(DBObject query) {
		return super.findForLucene(query);
	}

	@Override
	public List<T> findForLucene(int pageNum, int pageSize) {
		return super.findForLucene(pageNum, pageSize);
	}

	@Override
	public T findOne(DBObject query) {
		return (T) super.findOne(query);
	}

	@Override
	public T findOne(String key, Object value) {
		return (T) super.findOne(key, value);
	}

	@Override
	public T findOne(String id) {
		return (T) super.findOne(id);
	}
	
	
}
