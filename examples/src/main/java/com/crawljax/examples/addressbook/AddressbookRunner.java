package com.crawljax.examples.addressbook;

import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.plugins.crawloverview.CrawlOverview;
import com.crawljax.plugins.testcasegenerator.TestConfiguration;
import com.crawljax.plugins.testcasegenerator.TestSuiteGenerator;
import com.crawljax.stateabstractions.hybrid.HybridStateVertexFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Crawljax runner for the AddressBook web application.
 * 
 */
public final class AddressbookRunner {

	static final long WAIT_TIME_AFTER_EVENT = 500;
	static final long WAIT_TIME_AFTER_RELOAD = 500;
	public static final String URL = "http://localhost:3000/addressbook/";
	private static final boolean VISUAL_DATA = false;

	/**
	 * Run this method to start the crawl.
	 * 
	 * @throws IOException
	 *             when the output folder cannot be created or emptied.
	 */
	public static void main(String[] args) throws IOException {

		CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor(URL);
		
		AddressbookCrawlingRules.setCrawlingRules(builder);

		builder = CrawljaxConfiguration.builderFor(URL);
		AddressbookCrawlingRules.setCrawlingRules(builder);
		if(args.length == 1) {
			builder.setMaximumRunTime(Long.parseLong(args[0]), TimeUnit.MINUTES);
		}

		// add plugins
		builder.addPlugin(new CrawlOverview());
		builder.addPlugin(new TestSuiteGenerator(new TestConfiguration(TestConfiguration.StateEquivalenceAssertionMode.HYBRID)));
		builder.addPlugin(new AddressbookCleanup());

		builder.setStateVertexFactory(new HybridStateVertexFactory(0, builder, VISUAL_DATA));
		CrawljaxRunner crawljaxRunner = new CrawljaxRunner(builder.build());
		crawljaxRunner.call();
		crawljaxRunner.stop();
	}

}
