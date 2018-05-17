package com.chang.springboot.jpa.entityBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;

public class EntityBuilderTest {

    private MockEntityBuilder mockEntityBuilder;
    private MockValidateService mockValidateService;

    @Before
    public void setup() {
        mockEntityBuilder = new MockEntityBuilder();
        mockEntityBuilder.setName("chang");
        mockValidateService = new MockValidateService();
    }

    @Test
    public void builderTest() {
        mockEntityBuilder
                .validate(mockValidateService.existsNameValidate())
                .build(m -> Assert.assertThat(m.getName(), is("chang")));
    }

    @Test(expected = RuntimeException.class)
    public void validateTest() {
        mockEntityBuilder
                .validate(mockValidateService.isChangValidate());
    }

    // @Service
    private class MockValidateService {

        //private MockRepository repository

        public ValidateFunction<MockEntityBuilder> existsNameValidate() {
            return builder -> {
                // DB에저 같은 일므이 있는지 조회
            };
        }

        public ValidateFunction<MockEntityBuilder> isChangValidate() {
            return builder -> {
                if (builder.getName().equals("chang")) {
                    throw new RuntimeException("name이 chang 입니다.");
                }
            };
        }
    }

    private class MockEntityBuilder implements EntityBuilder<MockEntity> {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public MockEntity build() {
            return new MockEntity(name);
        }
    }

    private class MockEntity {

        private String name;

        public String getName() {
            return name;
        }

        public MockEntity(String name) {
            this.name = name;
        }
    }
}
