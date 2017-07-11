package com.maven.webdriver.AssignmentPD;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.http.ContentType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.*;

//import org.testng.annotations.BeforeTest;

public class TestAPI {
	Properties prop = new Properties();
	InputStream input = null;
	static String url1;
	static String url2;

	@BeforeTest
	public void beforeTest() throws IOException {
		input = new FileInputStream("config.properties");
		prop.load(input);
		url1 = prop.getProperty("url");
	}

	@Test
	public void f() {
		given().when().get(url1).then().assertThat().statusCode(200).and().contentType(ContentType.HTML);
	}

	@Test
	public void test1() {
		url2 = get("http://computer-database.gatling.io/computers?f=zzz").body().htmlPath()
				.getString("html.body.section.table.tbody.tr.td[0].a.@href");
		String str = url2.split("/")[2];
		url2 = "/" + str;
	}

	@Test
	public void test2() {
		url1 = url1.concat(url2);
		System.out.println(url1);
		String Name = get(url1).body().htmlPath().getString("html.body.section.form.fieldset.div.div[0].input.@value");
		Assert.assertEquals(Name, prop.getProperty("name"));
		String iDate = get(url1).body().htmlPath().getString("html.body.section.form.fieldset.div.div[1].input.@value");
		//System.out.println("iDate");
		Assert.assertEquals(iDate, prop.getProperty("idate"));
		String dDate = get(url1).body().htmlPath().getString("html.body.section.form.fieldset.div.div[2].input.@value");
		Assert.assertEquals(dDate, prop.getProperty("ddate"));
		String Company = get(url1).body().htmlPath()
				.getString("html.body.section.form.fieldset.div.div.select.option[3]");
		Assert.assertEquals(Company, prop.getProperty("company"));
	}
}
