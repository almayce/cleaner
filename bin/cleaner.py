from bin.actions import *
from bin.elements import *

open_browser()
set_value(Find.by_xpath(login_input), login)
set_value(Find.by_xpath(pass_input), password)
Find.by_xpath(sign_in).click()

while Wait.is_visible(tasks) and "(" in Find.by_xpath(tasks).get_attribute("text"):
    Find.by_xpath(tasks).click()
    Wait.is_visible(task_link)
    Find.by_xpath(task_link).click()
    Wait.is_not_visible(task_link)
    Wait.is_visible(h1)
    while not exists(task_link):
        if Find.button("Accept"): click()
        if Find.button("Yes"): click()
        if Find.button("Cancel"):
            click()
            if Find.button("Yes"): click()
        if Find.button("Close"):
            set_reasons()
            click()
            if Find.button("Yes"): click()
        if Find.button("Return To Operation Maker"):
            set_reasons()
            click()
        if Find.button("Return to Branch Maker"):
            set_reasons()
            click()
        if Find.button("Skip"): click()
        if Find.button("Go Back"): click()
        if Find.button("Approve"): click()
        if Find.button("Reject"): click()
        if Find.button("Reject Container"):
            set_reasons()
            click()
            if Find.button("Yes"): click()
        if Find.button("Cancel All Lines"):
            click()
            if Find.button("Yes"): click()
        if Find.button("Submit"): click()
        if Find.button("Complete"): click()
        if Find.button("Done"): click()
        if Find.button("Confirm"): click()
        if "(" not in Find.by_xpath(tasks).get_attribute("text"): break

close_browser()
