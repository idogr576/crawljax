package com.crawljax.examples.petclinic;

import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.BrowserOptions;
import com.crawljax.core.configuration.CrawlRules.FormFillMode;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.fragmentation.FragmentRules;

import java.util.concurrent.TimeUnit;

public class PetclinicCrawlingRules {

	/**
	 * List of crawling rules for the Petclinic application.
	 */
	public static void setCrawlingRules(CrawljaxConfigurationBuilder builder) {

		// crawling rules
		builder.crawlRules().clickElementsInRandomOrder(false);
		builder.crawlRules().clickDefaultElements();
		builder.crawlRules().crawlHiddenAnchors(true);
		builder.crawlRules().crawlFrames(false);
		builder.crawlRules().clickOnce(false);
		builder.crawlRules().setFormFillMode(FormFillMode.NORMAL);
		builder.crawlRules().setUsefulFragmentRules(new FragmentRules(50, 50, 2, 4));

		// do not click these

		// set timeout and limits for states and depth
		builder.setMaximumRunTime(30, TimeUnit.MINUTES);
		builder.setUnlimitedCrawlDepth();
		builder.setUnlimitedStates();
		builder.crawlRules().waitAfterReloadUrl(PetclinicRunner.WAIT_TIME_AFTER_RELOAD, TimeUnit.MILLISECONDS);
		builder.crawlRules().waitAfterEvent(PetclinicRunner.WAIT_TIME_AFTER_EVENT, TimeUnit.MILLISECONDS);

		// set browser
		builder.setBrowserConfig(new BrowserConfiguration(EmbeddedBrowser.BrowserType.CHROME, 1,
			new BrowserOptions(2)));

		// set input data specification
		builder.crawlRules().setInputSpec(PetclinicCrawlingRules.petclinicInputSpecification());

	}

	/**
	 * List of inputs to crawl the Phonecat application.
	 */
	static InputSpecification petclinicInputSpecification() {

		InputSpecification inputSpecification = new InputSpecification();
		PetclinicForms.addOwner(inputSpecification);
		PetclinicForms.addPet(inputSpecification);
		PetclinicForms.editOwner(inputSpecification);
		PetclinicForms.addVisit(inputSpecification);
		PetclinicForms.addVet(inputSpecification);
		PetclinicForms.addType(inputSpecification);

		return inputSpecification;
	}

}
