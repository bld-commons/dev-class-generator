/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.utils.ClassGeneratorUtils.java
 */
package bld.commons.classes.generator.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ClassGeneratorUtils.
 */
public class ClassGeneratorUtils {

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ClassGeneratorUtils.class);

	/**
	 * Gets the files.
	 *
	 * @param pathDir   the path dir
	 * @param extension the extension
	 * @return the files
	 */
	public static List<File> getFiles(String pathDir, String extension) {
		logger.debug(pathDir);
		if (StringUtils.isNotEmpty(extension)) {
			if (extension.charAt(0) != '.')
				extension = "." + extension;
		}
		List<File> files = new ArrayList<>();
		File dir = new File(pathDir);
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) 
				files.addAll(ClassGeneratorUtils.getFiles(file.getPath(), extension));
			else 
				if (extension == null || (extension != null && file.getPath().endsWith(extension)))
					files.add(file);
			
		}
		return files;
	}

	/**
	 * Gets the files.
	 *
	 * @param pathDir the path dir
	 * @return the files
	 */
	public static List<File> getFiles(String pathDir){
		return ClassGeneratorUtils.getFiles(pathDir, null);
	}
	
}
