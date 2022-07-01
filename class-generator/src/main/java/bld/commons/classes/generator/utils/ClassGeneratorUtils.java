/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.utils.ClassGeneratorUtils.java
 */
package bld.commons.classes.generator.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ClassGeneratorUtils.
 */
public class ClassGeneratorUtils {

	private static final String ENTITY = "^@(Entity|Entity\\()$";

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ClassGeneratorUtils.class);

	private final static String extension = ".java";
	
	private final static Pattern PATTERN_ENTITY=Pattern.compile(ENTITY);

	/**
	 * Gets the files.
	 *
	 * @param pathDir   the path dir
	 * @param extension the extension
	 * @return the files
	 */
	public static List<File> getFiles(String pathDir, String extension) {
		logger.debug("Path directory: " + pathDir);
		if (StringUtils.isNotEmpty(extension)) {
			if (extension.charAt(0) != '.')
				extension = "." + extension;
		}
		List<File> files = new ArrayList<>();
		File dir = new File(pathDir);
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				files.addAll(ClassGeneratorUtils.getFiles(file.getPath(), extension));
			else if (extension == null || (extension != null && file.getPath().endsWith(extension)))
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
	public static List<File> getFiles(String pathDir) {
		return ClassGeneratorUtils.getFiles(pathDir, null);
	}

	public static <E, L extends Collection<E>> void addElements(L list, E[] elements) {
		if (ArrayUtils.isNotEmpty(elements))
			for (E element : elements)
				list.add(element);

	}

	public static Set<String> buildPackage(String pathDir, String prjPackage, String regex, String slash, Set<String> packages,Set<String>entities) throws Exception {

		Pattern pattern = Pattern.compile(regex);
		if (packages == null)
			packages = new HashSet<>();
		packages.add(prjPackage.replace(".", slash));
		File dir = new File(pathDir + slash + prjPackage);
		if (dir.exists()) {
			for (File file : dir.listFiles()) {
				logger.debug(file);
				if (!file.isDirectory() && extension != null && file.getPath().endsWith(extension)) {
					BufferedReader read = new BufferedReader(new FileReader(file));
					String line = null;
					Set<String> compileds = new HashSet<>();
					while ((line = read.readLine()) != null) {
						Matcher matcher = pattern.matcher(line);
						Matcher matcherEntity=PATTERN_ENTITY.matcher(line);
						while (matcher.find()) {
							String regexImport = matcher.group();
							logger.debug("regexImport: " + regexImport);
							regexImport = regexImport.replace("import", "");
							regexImport = regexImport.substring(0, regexImport.lastIndexOf(".")).trim().replace(".", slash);
							if (!packages.contains(regexImport))
								compileds.add(regexImport);
						}
						while(matcherEntity.find()) 
							entities.add(prjPackage.replace(slash, ".")+"."+file.getName().replace(extension, ""));
						
							
					}
					if (read != null)
						read.close();
					logger.debug("--------------------------------import: "+compileds.size()+"---------------------------------------------");
					for (String compiled : compileds) {
						logger.debug("compile: " + compiled);
						packages.addAll(buildPackage(pathDir, compiled, regex, slash, packages,entities));
					}
					packages.addAll(compileds);
				}

			}
		}
		
		return packages;
	}

}
