package sura.runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/test-results/timeline")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "sura.stepDefinitions")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@InicioSesiónExitoso")
public class CucumberSuite {}
