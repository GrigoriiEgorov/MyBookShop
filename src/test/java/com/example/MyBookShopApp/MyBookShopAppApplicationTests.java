package com.example.MyBookShopApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
class MyBookShopAppApplicationTests {

	private final MyBookShopAppApplication myBookShopAppApplication;

	@Autowired
	public MyBookShopAppApplicationTests(MyBookShopAppApplication myBookShopAppApplication) {
		this.myBookShopAppApplication = myBookShopAppApplication;
	}




	@Test
	void contextLoads() {
		assertNotNull(myBookShopAppApplication);
	}

}
