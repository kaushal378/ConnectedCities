package com.codeChallange.connectedcities.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.codeChallange.connectedcities.interfaces.IReadInput;

@Service
public class IOService implements IReadInput{

	@Value("${input.file}")
	private String fileName;

	public File loadInputFile() throws IOException{

		File file = File.createTempFile("city", ".txt");
		InputStream in = null;
		try {
			in = new FileInputStream(fileName);
		}catch(FileNotFoundException e) {
			ClassPathResource classPathResource = new ClassPathResource(fileName);
			in = classPathResource.getInputStream();
		}
		file.deleteOnExit();

		FileOutputStream out = new FileOutputStream(file);
		IOUtils.copy(in, out);
		in.close();
		out.close();

		return file;

	}
}
