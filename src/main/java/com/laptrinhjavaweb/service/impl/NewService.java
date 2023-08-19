package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewsEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService{

	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewConverter newConverter;
	
	@Override
	public NewDTO save(NewDTO newDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewsEntity newsEntity = newConverter.toEntity(newDTO);
		newsEntity.setCategoryId(categoryEntity);
		newsEntity = newRepository.save(newsEntity);
		return newConverter.toDTO(newsEntity);
	}

	@Override
	public NewDTO update(NewDTO newDTO) {
		NewsEntity oldNewEntity = newRepository.findById(newDTO.getId()).orElseGet(null);
		NewsEntity newsEntity = newConverter.toEntity(newDTO, oldNewEntity);
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newsEntity.setCategoryId(categoryEntity);
		newsEntity = newRepository.save(newsEntity);
		return newConverter.toDTO(newsEntity);
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> results = new ArrayList<>();
		List<NewsEntity> entities = newRepository.findAll(pageable).getContent();
		for (NewsEntity item: entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO); 
		}
		return results;
	}

	@Override
	public int totalTtem() {
		
		return (int)newRepository.count();
	}
	
	
}
