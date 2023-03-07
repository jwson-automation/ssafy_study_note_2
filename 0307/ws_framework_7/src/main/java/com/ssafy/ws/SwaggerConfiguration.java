package com.ssafy.ws;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
//	Swagger-UI 확인
//	http://localhost:port/{your-app-root}/swagger-ui.html

	@Bean
	public Docket postsApi() {
		final ApiInfo apiInfo = new ApiInfoBuilder().title("SSAFY GuestBook API").description(
				"<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 GuestBook API<br><img src=\"img/ssafy_logo.png\" width=\"150\">")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp").version("1.0").build();

		Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("ssafyApi").apiInfo(apiInfo).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.ws.controller")).build();
//		              .paths(PathSelectors.ant("/admin/**"))
//				.paths(postPaths()).build();
		System.out.println(docket);
		return docket;
	}

	private Predicate<String> postPaths() {
		return regex("/admin/.*");
	}
}
