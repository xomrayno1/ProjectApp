package com.app.response.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.Drug;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
 
@AllArgsConstructor
@Getter
@Setter
public class DrugSpecification implements Specification<Drug>{
	private final String searchKey;
	private final Integer status;
	private final Integer loaiVatTu;
 
	
	public DrugSpecification(String searchKey, Integer loaiVatTu, Integer status) {		 
		this.searchKey = searchKey;
		this.loaiVatTu = loaiVatTu;
		this.status = status;
	}

	@Override
	public Predicate toPredicate(Root<Drug> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		
		if(searchKey != null && !searchKey.trim().isEmpty()) {
			String wrapSearch = "%" + searchKey.trim() + "%";
			Predicate cateName = criteriaBuilder.like(root.get("name"), wrapSearch);
			Predicate cateCode = criteriaBuilder.like(root.get("code"), wrapSearch);
			Predicate searchPredicate = criteriaBuilder.or(cateName, cateCode);
			predicates.add(searchPredicate);
		}
		
		if(loaiVatTu != null) {
			Predicate preLoaiVatTu = criteriaBuilder.equal(root.get("loaiVatTu"), loaiVatTu);
			predicates.add(preLoaiVatTu);
		}
		
		if(status != null) {
			Predicate proStatus = criteriaBuilder.equal(root.get("status"), status);
			predicates.add(proStatus);
		}
	 
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
	}


	public String getSearchKey() {
		return searchKey;
	}


	public Integer getStatus() {
		return status;
	}


	public Integer getLoaiVatTu() {
		return loaiVatTu;
	}

	
	
}
