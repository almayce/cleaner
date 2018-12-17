import time

from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException, TimeoutException
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from bin.config import *
from bin.elements import *

driver = webdriver.Chrome()


def open_browser():
    driver.get(baseurl)
    driver.maximize_window()


def close_browser():
    driver.close()


def select():
    ActionChains(driver).send_keys(Keys.ARROW_DOWN) \
        .send_keys(Keys.ENTER) \
        .perform()
    time.sleep(0.5)


def set_reasons():
    if exists(rejection_reason):
        Find.by_xpath(rejection_reason).click()
        select()
    if exists(rejection_Reason):
        Find.by_xpath(rejection_Reason).click()
        select()
    if exists(close_reason):
        Find.by_xpath(close_reason).click()
        select()


def exists(xpath):
    try:
        driver.find_element_by_xpath(xpath)
    except NoSuchElementException:
        return False
    return True


class Find:

    @staticmethod
    def by_xpath(xpath):
        element = driver.find_element_by_xpath(xpath)
        ActionChains(driver).move_to_element(element).perform()
        time.sleep(0.2)
        return element

    @staticmethod
    def button(text):
        print("button " + text)
        global current_button

        target = "//button[contains(text(), '" + text + "')]"
        target_uppercase = "//button[contains(text(), '" + text.upper() + "')]"

        if exists(target):
            current_button = Find.by_xpath(target)
            return True

        if exists(target_uppercase):
            current_button = Find.by_xpath(target_uppercase)
            return True

        return False


class Wait:

    def is_visible(xpath):
        try:
            WebDriverWait(driver, 30).until(expected_conditions.visibility_of_element_located((By.XPATH, xpath)))
            return True
        except TimeoutException:
            return False

    def is_not_visible(xpath):
        try:
            WebDriverWait(driver, 30).until_not(expected_conditions.visibility_of_element_located((By.XPATH, xpath)))
            return True
        except TimeoutException:
            return False


def wait_working():

    track_it = False

    for o in range(5):
        time.sleep(0.05)
        if Find.by_xpath(working).is_displayed():
            track_it = True
            break

    if track_it:
        while Find.by_xpath(working).is_displayed():
            time.sleep(0.2)
            print("working...")


def click():
    current_button.click()
    wait_working()


def set_value(element, text):
    return driver.execute_script(
        "return (function(webelement, text) {" +
        "if (webelement.getAttribute('readonly') != undefined) return 'Cannot change value of readonly element';" +
        "if (webelement.getAttribute('disabled') != undefined) return 'Cannot change value of disabled element';" +
        "webelement.focus();" +
        "var maxlength = webelement.getAttribute('maxlength') == null ? -1 : parseInt(webelement.getAttribute('maxlength'));" +
        "webelement.value = " +
        "maxlength == -1 ? text " +
        ": text.length <= maxlength ? text " +
        ": text.substring(0, maxlength);" +
        "return null;" +
        "})(arguments[0], arguments[1]);",
        element, text)
