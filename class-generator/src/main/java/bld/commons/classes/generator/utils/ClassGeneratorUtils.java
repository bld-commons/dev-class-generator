/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.utils.ClassGeneratorUtils.java
 */
package bld.commons.classes.generator.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bld.commons.classes.model.EntityModel;

/**
 * The Class ClassGeneratorUtils.
 */
public class ClassGeneratorUtils {

	private static final String EXTENDS = "extends";

	private static final String PUBLIC = "public";

	private static final String PROTECTED = "protected";

	private static final String PRIVATE = "private";

	/** The Constant ENTITY. */
	private static final String REGEX_ENTITY = "@Entity.*?class";

	private static final String REGEX_SUPER_CLASS = "@MappedSuperclass";

	private static final String REGEX_EXTENDS = "class.*?extends.*?\\{";

	/** The Constant ID. */
	private static final String REGEX_ID = "@(Id|EmbeddedId).*?(private|public|protected).*?(;|\\()";

	/** The Constant IMPORT. */
	private static final String REGEX_IMPORT = "^import .*?;";

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ClassGeneratorUtils.class);

	/** The Constant extension. */
	private final static String extension = ".java";

	/** The Constant PATTERN_ENTITY. */
	private final static Pattern PATTERN_ENTITY = Pattern.compile(REGEX_ENTITY);

	private final static Pattern PATTERN_SUPER_CLASS = Pattern.compile(REGEX_SUPER_CLASS);

	/** The Constant PATTERN_ID. */
	private final static Pattern PATTERN_ID = Pattern.compile(REGEX_ID);

	/** The Constant PATTERN_IMPORT. */
	private final static Pattern PATTERN_IMPORT = Pattern.compile(REGEX_IMPORT);

	private final static Pattern PATTERN_EXTENDS = Pattern.compile(REGEX_EXTENDS);

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

	/**
	 * Adds the elements.
	 *
	 * @param <E>      the element type
	 * @param <L>      the generic type
	 * @param list     the list
	 * @param elements the elements
	 */
	public static <E, L extends Collection<E>> void addElements(L list, E[] elements) {
		if (ArrayUtils.isNotEmpty(elements))
			for (E element : elements)
				list.add(element);

	}

	/**
	 * Entities model.
	 *
	 * @param pathDir    the path dir
	 * @param prjPackage the prj package
	 * @param slash      the slash
	 * @return the sets the
	 * @throws Exception the exception
	 */
	public static Set<EntityModel> entitiesModel(String pathDir, String prjPackage, String slash) throws Exception {
		return entitiesModel(pathDir, prjPackage, slash, new HashSet<>(), null);

	}

	/**
	 * Entities model.
	 *
	 * @param pathDir       the path dir
	 * @param prjPackage    the prj package
	 * @param slash         the slash
	 * @param entitiesModel the entities model
	 * @return the sets the
	 * @throws Exception the exception
	 */
	private static Set<EntityModel> entitiesModel(String pathDir, String prjPackage, String slash, Set<EntityModel> entitiesModel, EntityModel entityModel) throws Exception {
		File dir = new File(pathDir + slash + prjPackage.replace(".", slash));
		if (dir.exists()) {
			for (File file : dir.listFiles()) {
				if (!file.isDirectory() && extension != null && file.getPath().endsWith(extension)) {
					entityModel = getEntityModel(pathDir, prjPackage, slash, entitiesModel, entityModel, file);

				}
			}
		}
		return entitiesModel;
	}

	@SuppressWarnings("resource")
	private static EntityModel getEntityModel(String pathDir, String prjPackage, String slash, Set<EntityModel> entitiesModel, EntityModel entityModel, File file) throws FileNotFoundException, IOException, Exception {
		BufferedReader read = new BufferedReader(new FileReader(file));
		String line = null;
		Map<String, String> mapImport = new HashMap<>();
		StringBuilder str = new StringBuilder("");
		logger.info("File: " + file.getName());
		while ((line = read.readLine()) != null) {
			str.append(line);
			Matcher matcher = PATTERN_IMPORT.matcher(line);
			while (matcher.find()) {
				String imp = matcher.group();
				imp = imp.replace(";", "");
				logger.debug(imp.substring(imp.lastIndexOf(".") + 1) + ", " + imp);
				mapImport.put(imp.substring(imp.lastIndexOf(".") + 1), imp);
			}
		}

		String textFile = str.toString();
		Matcher matcherEntity = PATTERN_ENTITY.matcher(textFile);
		boolean patternEntity = false;
		boolean patternId = false;
		while (matcherEntity.find()) {
			entityModel = new EntityModel();
			entityModel.setName(file.getName().replace(extension, ""));
			entityModel.setPackageName(prjPackage);
			logger.info("Entity: " + entityModel.getClassName());
			patternEntity = true;
			patternId = findTypeId(pathDir, prjPackage, slash, entitiesModel, entityModel, mapImport, textFile, patternId);
			findTypeIdInSuperClass(pathDir, prjPackage, slash, entitiesModel, entityModel, mapImport, textFile, patternId);
			entityModel = null;
		}
		if (!patternEntity && entityModel != null) {
			Matcher matcherSuperClass = PATTERN_SUPER_CLASS.matcher(textFile);
			while (matcherSuperClass.find()) {
				patternId = findTypeId(pathDir, prjPackage, slash, entitiesModel, entityModel, mapImport, textFile, patternId);
				findTypeIdInSuperClass(pathDir, prjPackage, slash, entitiesModel, entityModel, mapImport, textFile, patternId);

			}
		}
		return entityModel;
	}

	private static void findTypeIdInSuperClass(String pathDir, String prjPackage, String slash, Set<EntityModel> entitiesModel, EntityModel entityModel, Map<String, String> mapImport, String textFile, boolean patternId) throws Exception {
		if (!patternId) {
			Matcher matcherExtends = PATTERN_EXTENDS.matcher(textFile);
			while (matcherExtends.find()) {
				String classExtends = matcherExtends.group();
				classExtends = classExtends.substring(classExtends.indexOf(EXTENDS)).replace(EXTENDS, "").trim();
				classExtends = classExtends.substring(0, classExtends.indexOf(" ")).trim();
				logger.info("Class Extends: " + classExtends);
				String imp = getImport(pathDir, prjPackage, slash, mapImport, classExtends);
				String filePath = pathDir + slash + imp.replace(".", slash).replace("import ", "").trim()+extension;
				logger.info("Path: "+filePath);
				File file = new File(filePath);
				if (file.exists())
					getEntityModel(pathDir, imp.substring(0, imp.indexOf("." + classExtends)), slash, entitiesModel, entityModel, file);
				else {
					entityModel.setTypeId("Object");
				}
				

			}
		}
	}

	private static boolean findTypeId(String pathDir, String prjPackage, String slash, Set<EntityModel> entitiesModel, EntityModel entityModel, Map<String, String> mapImport, String textFile, boolean patternId) {
		Matcher matcher = PATTERN_ID.matcher(textFile);
		while (matcher.find()) {
			patternId = true;
			String type = matcher.group();
			if (type.contains(PRIVATE))
				type = type.substring(type.indexOf(PRIVATE)).replace(PRIVATE, "").trim();
			else if (type.contains(PROTECTED))
				type = type.substring(type.indexOf(PROTECTED)).replace(PROTECTED, "").trim();
			else if (type.contains(PUBLIC))
				type = type.substring(type.indexOf(PUBLIC)).replace(PUBLIC, "").trim();
			type = type.substring(0, type.indexOf(" ")).trim();
			String imp = getImport(pathDir, prjPackage, slash, mapImport, type);

			entityModel.setImp(imp);
			entityModel.setTypeId(type);
			entitiesModel.add(entityModel);
			logger.info("Entity: " + entityModel);
		}
		return patternId;
	}

	private static String getImport(String pathDir, String prjPackage, String slash, Map<String, String> mapImport, String type) {
		logger.info("Type: " + type);
		String imp = mapImport.get(type);
		if (StringUtils.isBlank(imp)) {
			File classFile = new File(pathDir + slash + prjPackage.replace(".", slash) + slash + type + extension);
			if (classFile.exists())
				imp = prjPackage + "." + type;
			else
				imp = "";
		}
		return imp;
	}

	/**
	 * Builds the package.
	 *
	 * @param pathDir    the path dir
	 * @param prjPackage the prj package
	 * @param regex      the regex
	 * @param slash      the slash
	 * @param packages   the packages
	 * @param entities   the entities
	 * @return the sets the
	 * @throws Exception the exception
	 */
	public static Set<String> buildPackage(String pathDir, String prjPackage, String regex, String slash, Set<String> packages, Set<String> entities) throws Exception {

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
						Matcher matcherEntity = PATTERN_ENTITY.matcher(line);
						while (matcher.find()) {
							String regexImport = matcher.group();
							logger.debug("regexImport: " + regexImport);
							regexImport = regexImport.replace("import", "");
							regexImport = regexImport.substring(0, regexImport.lastIndexOf(".")).trim().replace(".", slash);
							if (!packages.contains(regexImport))
								compileds.add(regexImport);
						}
						while (matcherEntity.find())
							entities.add(prjPackage.replace(slash, ".") + "." + file.getName().replace(extension, ""));

					}
					if (read != null)
						read.close();
					logger.debug("--------------------------------import: " + compileds.size() + "---------------------------------------------");
					for (String compiled : compileds) {
						logger.debug("compile: " + compiled);
						packages.addAll(buildPackage(pathDir, compiled, regex, slash, packages, entities));
					}
					packages.addAll(compileds);
				}

			}
		}

		return packages;
	}

}
