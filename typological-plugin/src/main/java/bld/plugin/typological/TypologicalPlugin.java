package bld.plugin.typological;



import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.springframework.context.annotation.ComponentScan;

import bld.commons.classes.generator.ClassesGenerator;
import bld.commons.classes.generator.config.ConfigurationClassGenerator;
import bld.commons.classes.generator.impl.ClassesGeneratorImpl;
import bld.commons.classes.model.ModelClasses;
import bld.commons.yaml.converter.ConvertYaml;
import bld.commons.yaml.converter.impl.ConvertYamlImpl;

@Mojo(name = "typological-generator", defaultPhase = LifecyclePhase.GENERATE_SOURCES,requiresDependencyResolution = ResolutionScope.RUNTIME_PLUS_SYSTEM,requiresDependencyCollection = ResolutionScope.RUNTIME_PLUS_SYSTEM)
@ComponentScan(basePackages = "bld")
public class TypologicalPlugin extends AbstractMojo {

	@Parameter(property = "project", readonly = true)
	private MavenProject project;
	
	
	@Parameter(defaultValue = "${project.basedir}/src/main/resources/yaml-class/")
	private String resourceYamlDirectory;
	
	@Parameter(defaultValue = "/template")
	private String resourceTemplateDirectory;
	
	@Parameter(required = true)
	private String outputDirectory;
	

	public void execute() throws MojoExecutionException, MojoFailureException {
		ConvertYaml convertYaml=new ConvertYamlImpl();
		
		try {
			project.addCompileSourceRoot(new File(outputDirectory).getAbsolutePath());
			getLog().info(resourceYamlDirectory);
			List<ModelClasses> listModelClasses = convertYaml.getListClasses(resourceYamlDirectory);
			ClassesGenerator generatorClass=new ClassesGeneratorImpl(ConfigurationClassGenerator.configClassGenerator(resourceTemplateDirectory)); 
			generatorClass.writeClasses(listModelClasses, outputDirectory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
