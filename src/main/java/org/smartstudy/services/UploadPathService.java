package org.smartstudy.services;

import java.io.File;

public interface UploadPathService {

	File getFilePath(String modifiedFileName, String path);

}
