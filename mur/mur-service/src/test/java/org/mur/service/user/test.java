package org.mur.service.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.CaseFormat;

public class test {

	@Test
	public void test() {
		String test = "enumType";
		test = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, test);
		System.out.println(test);
	}

}
