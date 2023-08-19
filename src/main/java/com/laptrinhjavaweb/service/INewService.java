package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.NewDTO;

public interface INewService {

	NewDTO save(NewDTO newDTO);
	NewDTO update(NewDTO newDTO);
	
	List <NewDTO> findAll(Pageable pageable);
	int totalTtem();
}
