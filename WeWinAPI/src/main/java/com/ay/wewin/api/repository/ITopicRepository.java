package com.ay.wewin.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ay.wewin.api.model.Topic;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, String>{

}
