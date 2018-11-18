package com.codeChallange.connectedcities.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import com.codeChallange.connectedcities.interfaces.IReadInput;
import org.springframework.stereotype.Service;

@Service
public class IOService implements IReadInput{

	@Value("${input.file}")
	private String fileName;

	public File loadInputFile() throws IOException{

		File file = File.createTempFile("city", ".txt");
		try {
			//System.out.println(fileName);
			ClassPathResource classPathResource = new ClassPathResource(fileName);
			InputStream in = classPathResource.getInputStream();

			file.deleteOnExit();

			FileOutputStream out = new FileOutputStream(file);
			IOUtils.copy(in, out);

			out.close();
		}catch(IOException ie) {
			ie.printStackTrace();
			throw ie;
		}

		return file;

	}
}
