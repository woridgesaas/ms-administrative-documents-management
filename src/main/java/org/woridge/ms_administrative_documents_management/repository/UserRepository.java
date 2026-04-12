package org.woridge.ms_administrative_documents_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.woridge.ms_administrative_documents_management.model.DocumentRequest;
import org.woridge.ms_administrative_documents_management.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByCin(String cin);

    boolean existsByCin(String cin);
}