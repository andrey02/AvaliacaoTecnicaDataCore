package com.andrei.datacore.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.andrei.datacore.model.FileInformation;
import com.andrei.datacore.repository.FileInformationRepository;
import com.andrei.datacore.service.FileInformationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@EnableAsync
public class FileInformationServiceImpl implements FileInformationService {

	private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss.SSS";

	@Autowired
	private FileInformationRepository repository;

	@Value("${file.directory}")
	private String fileDirectory;

	@Async
	public void createFile(int num) {

		FileWriter writer = null;

		try {
			String fileName = "file" + num + ".txt";
			File file = new File(fileDirectory + fileName);

			if (file.createNewFile()) {
				Date now = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
				String date = simpleDateFormat.format(now);

				writer = new FileWriter(file);
				writer.write(date);
				writer.close();

				FileInformation savedInformation = save(createFileInformation(fileName, now));

				if (savedInformation.getId() % 2 == 0) {
					log.debug("Id: " + savedInformation.getId() + "; Name: " + savedInformation.getName()
							+ "; CreatedAt: " + savedInformation.getCreatedAt());
				}
			}

		} catch (IOException e) {
			log.debug("Not possible to create file number: " + num + 1);
			e.printStackTrace();
		}

	}

	public FileInformation save(FileInformation fileInformation) {
		return repository.save(fileInformation);
	}

	private FileInformation createFileInformation(String fileName, Date now) {
		FileInformation fileInformation = new FileInformation();
		fileInformation.setCreatedAt(now);
		fileInformation.setName(fileName);
		return fileInformation;
	}

}
