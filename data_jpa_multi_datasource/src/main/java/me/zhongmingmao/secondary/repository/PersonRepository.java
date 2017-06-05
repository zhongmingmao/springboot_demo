package me.zhongmingmao.secondary.repository;

import me.zhongmingmao.secondary.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
