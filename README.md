# Selenium

# TO DO:
1. Currently, test can only work when entering URL "http://www.walmart.com", but when entering URL "http://mobile.walmart.com",the test fails to run. 
Resolution: Set the chrome driver to have capability to run test in mobile emulation mode so that the URl work.

2. Hover over doesn't work on Safari driver, so the last step - Sign Out from account will fail
Resolution: simulate it using JavaScript to fire a mouse hover.

3. Not be able to run on device browser
Resolution: Need to spend some time to install the android and ios webdriver and make the test work

4. Make all files categorized with hierarchy
Resolution:
library: e.g. DriverWait.class
entity: e.g. Customer.class
page: e.g. HeaderPage.class, CheckoutMainPage.class ...
test case: AddAndRemoveItemFromShopCart

5. User has to create different test files for different webdrivers.
Resolution: Use TestNG.xml to pass browser info and also create BrowserFactory.class to include all the settting on different types of webdrivers. So that the user just need to pass the browser name as parameter to the same test.


#Potentail Issue when Running test:
1. When loading the home page "http://www.walmart.com", randomly showing the popup window for sign-up. Since it
happens randomely, the test isn't designed to handle it
Resolution: when loading home page, check if the popup window is showing up, then dismiss it.
2. When deleting item from shopping cart, it shows a section for re-logging. However, the accuont is still logged in and not expired.
Resolution: it doesn't affect the test itself, but could be a potential bug.

#Test Design
1. Page Object Model to make sure more tests share the functionalites on each Page and Also improve maintainability
2. Driver.wait class contains fluent wait to find element which guarantees the element can be found in certain timeout
3. Driver.wait class contains wait for page load to make sure the page is loaded successfully before the upcoming event occurs


