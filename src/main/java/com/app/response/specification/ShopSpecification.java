package com.app.response.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.Shop;
 
public class ShopSpecification implements Specification<Shop>{
	private static final long serialVersionUID = -5701622378843391000L;
	private final String searchKey;
	private final Integer shopType;
	private final Integer status;
 
	
	public ShopSpecification(String searchKey, Integer status, Integer shopType) {
		this.shopType = shopType;
		this.searchKey = searchKey;
		this.status = status;
	}

	@Override
	public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		
		if(searchKey != null && !searchKey.trim().isEmpty()) {
			String wrapSearch = "%" + searchKey.trim() + "%";
			Predicate cateName = criteriaBuilder.like(root.get("name"), wrapSearch);
			Predicate cateCode = criteriaBuilder.like(root.get("code"), wrapSearch);
			Predicate searchPredicate = criteriaBuilder.or(cateName, cateCode);
			predicates.add(searchPredicate);
		}
		
		if(status != null) {
			Predicate proStatus = criteriaBuilder.equal(root.get("status"), status);
			predicates.add(proStatus);
		}
	 
		if(shopType != null) {
			Predicate proStatus = criteriaBuilder.equal(root.get("type"), shopType);
			predicates.add(proStatus);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
	}

	
	
}
