package com.github.wolfetti;

import com.github.wolfetti.random.property.mvn.LengthMojo;
import com.github.wolfetti.random.property.mvn.RangeMojo;
import java.io.File;
import org.apache.maven.plugin.testing.MojoRule;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

public class RandomPropertyMojoTest {

    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {
        }

        @Override
        protected void after() {
        }
    };

    /**
     * @throws Exception if any
     */
    @Test
    public void testLength() throws Exception {
        executeLength("test-length-a");
        executeLength("test-length-b");
        executeLength("test-length-c");
        executeLength("test-length-d");
    }

    /**
     * @throws Exception if any
     */
    @Test
    public void testRange() throws Exception {
        executeRange("test-range-a");
        executeRange("test-range-b");
        executeRange("test-range-c");
        executeRange("test-range-d");
    }
    
    private void executeLength(String folderName) throws Exception {
        File pom = new File(RandomPropertyMojoTest.class.getResource("/" + folderName).toURI());
        assertNotNull(pom);
        assertTrue(pom.exists());

        LengthMojo mojo = (LengthMojo) rule.lookupConfiguredMojo(pom, "length");
        assertNotNull(mojo);
        
        mojo.execute();
    }
    
    private void executeRange(String folderName) throws Exception {
        File pom = new File(RandomPropertyMojoTest.class.getResource("/" + folderName).toURI());
        assertNotNull(pom);
        assertTrue(pom.exists());

        RangeMojo mojo = (RangeMojo) rule.lookupConfiguredMojo(pom, "range");
        assertNotNull(mojo);
        
        mojo.execute();
    }

}
