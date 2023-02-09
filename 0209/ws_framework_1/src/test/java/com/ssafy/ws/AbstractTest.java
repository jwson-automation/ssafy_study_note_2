package com.ssafy.ws;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

// Spring과 연동하기 위해 SprintRunner를 사용한다.
@RunWith(SpringRunner.class)
// 설정 파일의 위치를 지정한다.
@ContextConfiguration(locations = "/application.xml")
public abstract class AbstractTest {
}
