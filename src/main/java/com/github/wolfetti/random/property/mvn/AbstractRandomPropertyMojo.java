package com.github.wolfetti.random.property.mvn;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class AbstractRandomPropertyMojo extends AbstractMojo {
    
    /**
     * Maven Project
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    protected MavenProject project;
    
    /**
     * Name of the property to set
     */
    @Parameter(readonly = false, required = true)
    protected String propertyName;
    
    /**
     * Type of random. It must be one of graph, numeric, alphanumeric, ascii.
     */
    @Parameter(defaultValue = "alphanumeric", readonly = false, required = true)
    protected String type;
    
    protected abstract String getValue() throws MojoExecutionException, MojoFailureException;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if(!StringUtils.equalsAny(type, "graph", "numeric", "alphanumeric", "ascii")){
            throw new MojoExecutionException("Type not supported: '" + type + "'");
        }
        
        String value = getValue();
        project.getProperties().setProperty(propertyName, value);
        getLog().info("Generated random in property named '" + propertyName + "' with value: '" + value + "'");
    }
}
