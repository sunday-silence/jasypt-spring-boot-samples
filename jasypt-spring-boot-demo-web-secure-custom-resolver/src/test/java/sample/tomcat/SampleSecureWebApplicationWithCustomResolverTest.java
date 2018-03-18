/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleSecureWebApplicationWithCustomResolver.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleSecureWebApplicationWithCustomResolverTest {

	@Autowired
    TestRestTemplate testRestTemplate;

	@Test
	public void testHome() throws Exception {
		TestRestTemplate secured = testRestTemplate.withBasicAuth("user", "password");
		ResponseEntity<String> entity = secured.getForEntity("/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("Hello, Secret Property: chupacabras", entity.getBody());
	}

}