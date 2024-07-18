package com.github.wolfetti.random.property.mvn;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal which insert a random value into property with a given lenght.
 * 
 * @See {@link RandomStringUtils}
 */
@Mojo(name = "length", defaultPhase = LifecyclePhase.NONE, aggregator = true)
public class LengthMojo extends AbstractRandomPropertyMojo {
    
    /**
     * Random length
     */
    @Parameter(readonly = false, required = true, alias = "length", defaultValue = "12")
    private int length;

    @Override
    protected String getValue() throws MojoExecutionException, MojoFailureException {
        if(length < 3){
            throw new MojoExecutionException("Lenght must be at least 3 characters. Current length: " + length);
        }
        
        getLog().info("Generating random of type '" + type + "' with length of " + length + " chars");
        
        switch (type) {
            case "alphanumeric":
                return RandomStringUtils.randomAlphanumeric(length);
            case "numeric":
                return RandomStringUtils.randomNumeric(length);
            case "ascii":
                return RandomStringUtils.randomAscii(length);
            case "graph":
                return RandomStringUtils.randomGraph(length);
            default:
                throw new MojoExecutionException("Type not supported: '" + type + "'");
        }
    }
}
