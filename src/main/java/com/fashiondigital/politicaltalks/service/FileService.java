package com.fashiondigital.politicaltalks.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	public String writeFile(String path, String suffix, String content) throws IOException {
	    File tmpFile = File.createTempFile(path, suffix);
	    FileWriter writer = new FileWriter(tmpFile);
	    writer.write(content);
	    writer.close();
	    return tmpFile.getPath();
	}
}