package com.model2.mvc.service.purchase.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddPurchase() throws Exception {
		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();
		user = userService.getUser("user17");
		product = productService.getProduct(10003);
		System.out.println("=========================================");
		System.out.println(product);
		System.out.println(user);
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("tiger");
		purchase.setReceiverPhone("010-1234-1234");
		purchase.setDlvyAddr("역삼");
		purchase.setDlvyRequest("배송요청사항");
		purchase.setTranCode("2");
		purchase.setDlvyDate("20240312");
		
		purchaseService.addPurchase(purchase);
		System.out.println(purchase);
		

	}
	
	@Test
	public void testGetPurchase() throws Exception {
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(10000);
		System.out.println(purchase);
	}
	
	@Test
	public void testGetPurchaseList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(4);
		search.setUserId("user18");
		Map<String, Object> map = purchaseService.getPurchaseList(search);
		List<Object> list = (List<Object>)map.get("list");
		
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	@Test
	public void testUpdatePurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		purchase.setTranNo(10000);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("update");
		purchase.setReceiverPhone("010-9999-9999");
		purchase.setDlvyAddr("updateAddr");
		purchase.setDlvyRequest("update");
		purchase.setDlvyDate("24/03/03");
		
		
		purchaseService.updatePurchase(purchase);
	}
	
	@Test
	public void testUpdateTranCode() throws Exception {
		
		Purchase purchase = new Purchase();
		purchase.setTranNo(10000);
		purchase.setTranCode("2");
		
		System.out.println(purchase);
		
		purchaseService.updateTranCode(purchase);
		
	}
	
	
	
}
