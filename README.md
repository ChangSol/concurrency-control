# 동시성 제어

## 1. 요구사항

## 2. Environment

### 1) 개발 환경
- 기본 사용 포트 : 8080
- 코드컨벤션 : chang_sol_code_style.xml 참고
- JAVA 16
- SpringBoot 2.6.6
- JPA (Spring Data JPA)
- Hibernate 5.6.5.Final (query issue 로 인한 다운그레이드 root build.gradle 참고)

```
configurations.configureEach {
        resolutionStrategy.eachDependency { DependencyResolveDetails details ->
            if (details.requested.group == 'org.hibernate' && details.requested.name == 'hibernate-core') {
                details.useVersion '5.6.5.Final'
                details.because 'hibernate 5.6.7.Final query error issue. 5.6.5.Final downgrade'
            }
        }
    }
```

- H2 DB (module-core yml 참고)

```yaml
  h2:
    console:
      enabled: true  # H2 console 사용
      path: /h2  # console 경로

  #DB설정
  datasource:
    #h2 드라이버 설정
    driver-class-name: org.h2.Driver
    #사용할 DB URL (Connection)
    url: jdbc:h2:mem:concurrency_control
    username: changsol  #ID
    password: changsol^_^3  #PWD
```

- SpringDoc를 통한 OpenAPI(3.0) swagger 

```yaml
#SrpingDoc 설정 API 문서화
springdoc:
  version: 0.0.1
  api-docs:
    path: /api-docs
  #Media Type 기본 값을 application/json
  default-consumes-media-type: application/json;charset=UTF-8
  default-produces-media-type: application/json;charset=UTF-8
  cache:
    disabled: true
  swagger-ui:
    #api 및 태그 정렬 기준을 알파벳 오름차순
    operations-sorter: alpha
    tags-sorter: alpha
    path: /swagger-ui
    #swagger-ui default url인 petstore html 문서 비활성화 여부
    disable-swagger-default-url: true
    display-request-duration: true  # try it out 을 했을 때 request duration 을 추가로 찍어줌
  #OpenAPI 3 로 문서화할 api path 리스트
  paths-to-match:
    - /v1/**
```

```java

@Component
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
		Info info = new Info()
			.title("Changsol 동시성 제어 API")
			.version(appVersion)
			.description("창솔 동시성 제어 오신걸 환영합니다.😁")
			.contact(new Contact().name("changsol-github").url("https://github.com/ChangSol"))
			.license(new License().name("Apache License Version 2.0")
								  .url("http://www.apache.org/licenses/LICENSE-2.0"));

		return new OpenAPI()
			.components(new Components())
			.info(info);
	}
}
```

### 2) 외부 라이브러리
- module 
  - lombok:1.18.2 => 보일러플레이트 처리.
  - mapstruct:1.5.2.Final => Class간 변환을 처리하기 위한 매퍼 클래스. DTO->Entity, Entity->DTO, DTO->DTO 등
  - guava:31.1-jre => Google Core 라이브러리. 주로 collection 초기화에 사용하였음
  - commons-lang3:3.12.0 => Lang 유틸리티로 사용.
  - commons-collections4:4.4 => Colletion 유틸리티로 사용.
  - commons-io:2.7 => Apache IO 라이브러리. DataReader 등 읽기에 사용하였음
- module-search
  - springdoc-openapi-ui:1.6.14 => API Docs를 위한 라이브러리. Swagger 작성 

### 3) 모듈 구조

- module-core : 도메인 모듈

### 4) Domain Model

## 3. 응답 및 오류

## 4. 테스트 케이스