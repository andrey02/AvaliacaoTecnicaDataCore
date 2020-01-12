package com.andrei.datacore.service;

import com.andrei.datacore.model.FileInformation;

public interface FileInformationService {
	void createFile(int num);

	FileInformation save(FileInformation fileInformation);
}
