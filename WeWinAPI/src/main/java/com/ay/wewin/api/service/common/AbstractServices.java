package com.ay.wewin.api.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.exception.ResourceNotFoundException;
import com.google.common.collect.Lists;

@Transactional
public abstract class AbstractServices<T extends Serializable> implements IServices<T> {

	@Override
	public T create(T entity) {
		return getDao().save(entity); //Handle Primary Key error Exception
	}

	@Override
	public T update(T entity) {
		return getDao().save(entity); //Handle Primary Key error Exception
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(String id) {
		return getDao().findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", "id", id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return Lists.newArrayList(getDao().findAll());
	}

	@Override
	public Page<T> findPaginated(int page, int size) {
		return getDao().findAll(PageRequest.of(page, size));
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(String id) {
		getDao().deleteById(id);
	}

	protected abstract PagingAndSortingRepository<T, String> getDao();

}
