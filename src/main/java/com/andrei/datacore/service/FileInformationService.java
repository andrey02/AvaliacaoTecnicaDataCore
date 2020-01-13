package com.andrei.datacore.service;

import java.util.Date;

import com.andrei.datacore.model.FileInformation;

public interface FileInformationService {
	void createFile(int num);

	FileInformation save(FileInformation fileInformation);
	FileInformation createFileInformation(String fileName, Date now);
}
