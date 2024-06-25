package com.example.testcode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class UserTest {
    private User user;

    @Autowired
    private JacksonTester<User> json;

    @Before
    public void setUp() throws  Exception {
        User user = new User("user","Jack","Frost","jfrost@example.com");
        user.setId(0L);
        user.setCreateAt(12345L);
        user.setLastModified(123456L);
        this.user = user;
    }

    @Test
    public  void deserializeJson() throws  Exception {
        String content = "{\"username\": \"user\", \"firstName\": \"Jack\", " +
                "\"lastName\": \"Frost\", \"email\": \"jfrost@example.com\",}";
        assertThat(this.json.parse(content)).isEqualTo(new User("user","Jack","Frost","jfrost@example.com"));
        assertThat(this.json.parseObject(content).getUsername()).isEqualTo("user");
    }

    @Test
    public void serializeJson() throws Exception {
        assertThat(this.json.write(user)).isEqualTo("user.json");
        assertThat(this.json.write(user)).isEqualToJson("user.json");
        assertThat(this.json.write(user)).hasJsonPathStringValue("@.username");

        asserJsonPropertyEquals("@.username","user");
        asserJsonPropertyEquals("@.firstName","Jack");
        asserJsonPropertyEquals("@.lastName","Frost");
        asserJsonPropertyEquals("@.email","jfrost@example.com");
    }

    private void asserJsonPropertyEquals(String key, String value) throws java.io.IOException{
        assertThat(this.json.write(user)).extractingJsonPathArrayValue(key).isEqualTo(value);
    }
}
