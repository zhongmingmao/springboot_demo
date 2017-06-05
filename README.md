# springboot_demo[![Build Status](https://travis-ci.org/zhongmingmao/springboot_demo.svg?branch=master)](https://travis-ci.org/zhongmingmao/springboot_demo)

## 1. Description

Spring Boot Demo Project Integrated With Travis CI (Just For Integrated Test).

## 2. Travis CI Strategy

```
language: java
jdk:
  - oraclejdk8

before_install:
  - "echo skipped"
  
install:
  - "echo skipped"
  
script:
  - mvn test

notifications:
  email:
    recipients:
      - zhongmingmao0625@gmail.com
```