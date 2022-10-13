package com.crawljax.examples.addressbook;

import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.browser.EmbeddedBrowser.BrowserType;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.BrowserOptions;
import com.crawljax.core.configuration.CrawlRules.FormFillMode;
import com.crawljax.core.configuration.CrawlRules.FormFillOrder;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.core.state.Identification;
import com.crawljax.core.state.Identification.How;
import com.crawljax.forms.FormInput;
import com.crawljax.forms.FormInput.InputType;
import com.crawljax.fragmentation.FragmentRules;
import com.crawljax.plugins.crawloverview.CrawlOverview;

import java.util.concurrent.TimeUnit;

public class AddressbookCrawlingRules {

	/**
	 * List of crawling rules for the AddressBook application.
	 */
	public static void setCrawlingRules(CrawljaxConfigurationBuilder builder) {

		/* crawling rules. */
		builder.crawlRules().clickElementsInRandomOrder(false);
		builder.crawlRules().clickDefaultElements();
		builder.crawlRules().crawlHiddenAnchors(true);
		builder.crawlRules().crawlFrames(false);
		builder.crawlRules().clickOnce(false);

		builder.crawlRules().setFormFillMode(FormFillMode.NORMAL);
		builder.crawlRules().setFormFillOrder(FormFillOrder.VISUAL);
		builder.crawlRules().avoidDifferentBacktracking(true);
		builder.crawlRules().setRestoreConnectedStates(true);
		builder.crawlRules().skipExploredActions(false, 3);
		builder.crawlRules().setUsefulFragmentRules(new FragmentRules(50, 50, 2, 4));

		// do not click these
		builder.crawlRules().crawlHiddenAnchors(false);
		builder.crawlRules().dontClick("a").withText("php-addressbook");
		builder.crawlRules().dontClick("a").withText("v9.0.0.1");
//		builder.crawlRules().dontClick("a").withText("map");
//		builder.crawlRules().dontClick("a").withText("export");
//		builder.crawlRules().dontClick("a").withText("import");

		// set timeout and limits for states and depth
		builder.setUnlimitedCrawlDepth();
		builder.setMaximumRunTime(30, TimeUnit.MINUTES);
		builder.setUnlimitedStates();
		builder.crawlRules().waitAfterReloadUrl(AddressbookRunner.WAIT_TIME_AFTER_RELOAD, TimeUnit.MILLISECONDS);
		builder.crawlRules().waitAfterEvent(AddressbookRunner.WAIT_TIME_AFTER_EVENT, TimeUnit.MILLISECONDS);

		// set browser
		builder.setBrowserConfig(new BrowserConfiguration(EmbeddedBrowser.BrowserType.CHROME, 1,
			new BrowserOptions(2)));

		// set input data specification
		builder.crawlRules().setInputSpec(AddressbookCrawlingRules.AddressBookInputSpecification());

		builder.addPlugin(new AddressbookCleanup());
	}

	/**
	 * List of inputs to crawl the AddressBook application.
	 */
	static InputSpecification AddressBookInputSpecification() {

		InputSpecification inputAddressBook = new InputSpecification();

		AddressbookForms.login(inputAddressBook);
		AddressbookForms.newEntry(inputAddressBook);
		AddressbookForms.newUser(inputAddressBook);
		AddressbookForms.newGroup(inputAddressBook);
		
		FormInput search = new FormInput(InputType.TEXT, new Identification(How.name, "searchstring" ));
		search.inputValues("andrea");
		inputAddressBook.inputField(search);
		
		return inputAddressBook;
	}

}
