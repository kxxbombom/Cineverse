package kr.spring.shop.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.shop.vo.PBasketVO;
import kr.spring.shop.vo.ProductVO;

public interface ShopService {
	public void registerProduct(ProductVO product);
	public List<ProductVO> productList(Map<String, Object> map);
	public Integer productCount(Map<String, Object> map);
	public ProductVO productDetail(Long p_num);
	public void ProductFav(ProductVO fav);
	public ProductVO selectProductFav(ProductVO fav);
	public void ProductFavDelete(ProductVO fav);
	public Integer productFavCount(Long p_num);
	public List<ProductVO> productFavList(Long mem_num);
	
	public PBasketVO selectProductBasket(PBasketVO basket);
	public void ProductBasket(PBasketVO basket);
	public void ProductBasketDelete(PBasketVO basket);
	public List<ProductVO> productBasketList(Long mem_num);

	/* public List<Integer> basketPrice(Long mem_num); */
	public Integer basketTotalPrice(Long mem_num);
	public Integer basketCount(Long mem_num);
}
