package com.example.accessingdatabasemysql;

import com.example.accessingdatabasemysql.controller.MainController;
import com.example.accessingdatabasemysql.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccessingDatabaseMysqlApplicationTests {

	/* host */
	@Value("${access.host}")
	private String accessHost;

	/* random port */
	@LocalServerPort
	private int port;


	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MainController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void getUserInfo() throws Exception {
		User user = new User();
		user.setId(1);
		user.setEmail("myjhaha@qq.com");
		user.setName("hahaha");

		User resUser = this.restTemplate.getForObject(
				"http://"+accessHost+":" + port + "/demo/user?id=" + 1,
				User.class);
		assertThat(resUser.getId()).isEqualTo(user.getId());
		assertThat(resUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(resUser.getName()).isEqualTo(user.getName());
	}

	@Test
	public void getAllUserInfo() throws Exception {
		User[] resUsers = this.restTemplate.getForObject(
				"http://"+accessHost+":" + port + "/demo/user/all" ,
				User[].class);
		assertThat(resUsers.length).isGreaterThan(1);
	}

	@Test
	public void addOneUser() throws Exception{
		User user = new User();
		user.setName("fuck off");
		user.setEmail("Fuckfuck@qq.com");

		// 请求参数
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("email", "Fuckfuck@qq.com");
		postParameters.add("name", "fuck off");
		//设置请求头
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);

		User resUser = this.restTemplate.postForObject(
				"http://"+accessHost+":" + port + "/demo/user/add",
				r,
				User.class);
		assertThat(resUser).isNotNull();
		assertThat(resUser.getId()).isNotNull();
		assertThat(resUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(resUser.getName()).isEqualTo(user.getName());
	}

}
