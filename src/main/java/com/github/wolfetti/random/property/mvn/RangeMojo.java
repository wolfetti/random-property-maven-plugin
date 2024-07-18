package com.github.wolfetti.random.property.mvn;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal which insert a random value into property with a given minimum inclusive and a maximum exclusive lenght.
 * 
 * @See {@link RandomStringUtils}
 */
@Mojo(name = "range", defaultPhase = LifecyclePhase.NONE, aggregator = true)
public class RangeMojo extends AbstractRandomPropertyMojo {
    
    /**
     * Minimum inclusive length. It must be at least 3 chars.
     */
    @Parameter(readonly = false, required = true)
    private int minInclusive;
    
    /**
     * Maximum exclusive length. It must be at least 3 chars and > minInclusive
     */
    @Parameter(readonly = false, required = true)
    private int maxExclusive;

    @Override
    protected String getValue() throws MojoExecutionException, MojoFailureException {
        if(minInclusive < 3){
            throw new MojoExecutionException("Min lenght must be at least 3 characters. Current length: " + minInclusive);
        }
        
        
        if(maxExclusive <= minInclusive){
            throw new MojoExecutionException("Max lenght must be > to min length characters. Current length: " + maxExclusive);
        }
        
        getLog().info("Generating random of type '" + type + "' with min length of " + minInclusive + " chars and max length of " + maxExclusive + " chars");
        
        switch (type) {
            case "alphanumeric":
                return RandomStringUtils.randomAlphanumeric(minInclusive, maxExclusive);
            case "numeric":
                return RandomStringUtils.randomNumeric(minInclusive, maxExclusive);
            case "ascii":
                return RandomStringUtils.randomAscii(minInclusive, maxExclusive);
            case "graph":
                return RandomStringUtils.randomGraph(minInclusive, maxExclusive);
            default:
                throw new MojoExecutionException("Type not supported: '" + type + "'");
        }
    }
}
