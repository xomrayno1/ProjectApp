package com.app.response.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.Product;
import com.app.utils.Constant;
 
public class ProductSpecification implements Specification<Product>{

	private   String searchKey;
	private Integer status;

	public ProductSpecification(String searchKey, Integer status ) {
		 
		this.searchKey = searchKey;
		this.status = status;
	 
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		
		if(searchKey != null && !searchKey.trim().isEmpty()) {
			String wrapSearch = "%" + searchKey.trim() +"%";
			Predicate proName = criteriaBuilder.like(root.get("name"), wrapSearch);
			predicates.add(proName);
		}
		if(status != null) {
			Predicate proStatus = criteriaBuilder.equal(root.get("status"), status);
			predicates.add(proStatus);
		}
 
		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
 
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
