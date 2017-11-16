@echo off
copy E:\SeleniumDemo\Resource\environment.xml E:\Selenium_Cucumber\allure-results
set root=E:\SeleniumDemo\target
CD /D %root%
allure generate ..\allure-results -o allure-report --clean  & allure open



