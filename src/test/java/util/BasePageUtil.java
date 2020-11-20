package util;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class BasePageUtil {

    protected final Logger log = Logger.getLogger(BasePageUtil.class);
    private final WaitingActions waitingAction;
    protected WebDriver driver;

    private final JavascriptExecutor jsExec;


    public BasePageUtil(WebDriver driver) {
        this.driver = driver;
        waitingAction = new WaitingActions(driver);

        jsExec = (JavascriptExecutor) driver;
    }


    public static String generateRandomNumericString(int length) {
        String alphabet = "0123456789"; // 9
        int n = alphabet.length(); // 10

        String result = "";
        Random r = new Random(); // 11

        for (int i = 0; i < length; i++) {
            String stringBuilder = result +
                    alphabet.charAt(r.nextInt(n));
            result = stringBuilder; // 13
        }
        return result;
    }


    protected void waitAllRequests() {
        try {
            waitingAction.pageLoadComplete();
            waitingAction.jQueryComplete();
            waitingAction.waitForAngularLoad();
            waitingAction.ajaxComplete();
        } catch (Exception e) {

            log.error(e.getMessage());
        }
    }

    protected void assertTrue(boolean condition, String message) {
        try {

            if (condition) {
                Assert.assertTrue(condition, message + "    is  found");
                log.info("is element present" + " " + message);
            } else {
                Assert.assertTrue(condition, message + "    is  found");
                log.error(condition + message + "    is not found" + getCurrentUrl());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    protected WebElement sendKeysBy(By by, String value) {
        WebElement element = getElementBy(by);
        if (!isElementPresentControl(by)) {
            log.error(element + "Element is null.  do not send");
        } else {
            scrollToElement(element);
            element.clear();
            element.sendKeys(value);
            log.info(value + "  " + "send key");
        }
        return element;

    }


    protected void clickOBJECT(By by, String message) {
        try {

            if (isElementPresentControl(by)) {
                WebElement element = getElementBy(by);
                scrollElementIntoMiddles(by);
                jsExec.executeScript("arguments[0].style.backgroundColor = 'green'", element);
                element.click();
                log.info(message + "  " + "is clicked");
            } else {
                log.error(message + "    is not click");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    protected WebElement getElementBy(By by) {
        WebElement element = null;
        try {
            element = driver.findElement(by);
        } catch (Exception ex) {
            element = null;
        }
        return element;
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl().trim();
    }


    public void constantControl(String value) {
        waitAllRequests();
        for (int a = 0; a < 10; a++) {
            sleep(1000);
            List<WebElement> element = driver.findElements(By.cssSelector("div#search > .s-desktop-content.s-desktop-width-max.sg-row > .sg-col.sg-col-12-of-16.sg-col-16-of-20.sg-col-20-of-24.sg-col-24-of-28.sg-col-28-of-32.sg-col-32-of-36.sg-col-8-of-12 > .sg-col-inner > span:nth-of-type(3) > .s-main-slot.s-result-list.s-search-results.sg-row > div  .celwidget.slot\\=MAIN.template\\=SEARCH_RESULTS.widgetId\\=search-results  .a-section.a-spacing-medium"));
            if (element.size() != 0) {
                a = 9;
                if (element != null) {
                    for (int i = 0; i < element.size(); i++) {
                        WebElement ele = element.get(i);
                        String span = ele.getAttribute("outerText");
                        if (span.contains(value.toLowerCase())) {
                            System.out.println(span.trim() + i);
                        } else {
                            //System.out.println("Search ok" + i );
                        }
                    }
                }
                log.info(element.size() + "  " + " first page");
            }
        }

    }


    protected void sleep(long time) {
        for (int i = 10; i < 13; i++) {
            try {
                long start = System.currentTimeMillis();
                Thread.sleep(time);
                //  log.info("Sleep time in ms = " + (System.currentTimeMillis( ) - start) + "  " + Thread.currentThread( ).getName( ) + "  " + i);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }


    private void scrollElementIntoMiddles(By by) {
        WebElement element = getElementBy(by);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }


    protected boolean isElementPresent(By by) {
        waitAllRequests();
        if (getElementBy(by) == null) {
            for (int i = 0; i < 50; i++) {
                waitAllRequests();
                log.info(i);
                if (getElementBy(by) != null) {
                    waitAllRequests();
                    getElementBy(by);
                    highlightElement2(by);
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }

    protected boolean isElementPresentControl(By by) {

        waitAllRequests();
        if (getElementBy(by) == null) {
            for (int i = 0; i <= 10; i++) {
                if (getElementBy(by) != null) {
                    getElementBy(by);
                    return true;
                }
                log.info(i);
            }
            return false;
        } else {
            getElementBy(by);
            return true;
        }
    }


    private WebElement highlightElement2(By locator) {
        WebElement elem = driver.findElement(locator);

        waitAllRequests();
        try {
            if (elem != null) {
                scrollElementIntoMiddles(locator);
                if (driver instanceof JavascriptExecutor) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid yellow'", elem);
                } else {
                    log.error("error highlightElement2 ");
                }
            } else {
                log.error("element null");
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
        }
        return elem;
    }


    protected void scrollToElement(WebElement element) {
        try {
            if (element != null) {
                waitAllRequests();

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                sleep(200);
                //  log.info(element);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}

