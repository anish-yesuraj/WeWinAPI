package com.ay.wewin.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ay.wewin.api.model.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, String>{

}
