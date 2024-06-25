package com.example.testcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)//junit프레임워크에서 사용
class TestCodeApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void contextLoads() throws Throwable{
        Assert.assertNotNull("the application context should have loaded", this.applicationContext);
    }

    @MockBean
    private UserService userService;

    @MockBean
    private AccountRepository accountRepository;

    private AccountService accountService;

    @Before
    public void before() {
    accountService = new AccountService(accountRepository, userService);
    }

    @Test
    public void getUserAccountsReturnsSingleAccount() throws Exception{
        given(this.accountRepository.findAccountByUsername("user"))
                .willReturn(
                        Collections.singletonList(
                                new Account("user", new AccountNumber("123456789"))
                        )
                );
        given(this.userService.getAuthenticatedUser())
                .willReturn(new User(0L,"user","Json".,"Doe"));
    }

    List<Account> actual = accountService.getAthenticateUser();

    assertThat(actual).size().isEqualto(1);
    assertThat(actual.get(0).getUsername()).isEqual("user");
    assertThat(actual.get(0).getAccountNumber()).isEqual(new AccountNumber("123456789"));

}
