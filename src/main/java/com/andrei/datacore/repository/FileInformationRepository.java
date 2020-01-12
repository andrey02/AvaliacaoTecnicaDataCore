package com.andrei.datacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrei.datacore.model.FileInformation;

@Repository
public interface FileInformationRepository extends JpaRepository<FileInformation, Long> {

}
