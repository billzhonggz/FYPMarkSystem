# FYP Mark System

This is a group project for **Software Development Workshop III** and **Software Engineering** in Semester 2, 2016-2017 Academic Year of BNU-HKBU United International College. 

* If you are looking this repository in Git@OSChina, please refer to the main repository on Github: https://github.com/billzhonggz/FYPMarkSystem
* For executable files and user manual, please check `executable` folder. 

## Features

* All of the related data is store in a standalone SQLite `.db` file.
* Import student information with Excel file.
* Add evaluation items with names and weighting (in %).
* Edit marks of students for each evaluation items (in ABCDF level system).
* Calculate total score base on weighting.
* Export grade report in Excel file.

## Usage

* Import student list: Excel file should be on `/path/to/project(jar)/input/student.xlsx`
* Exported report is on `/path/to/project(jar)/FYPGradeReport.xlsx`

## Issues

* The amount of evaluation items is fixed as 12. 
* SQLite create database is not available at this moment, do not delete the `fyp.db` file.
* User experience can be improved. 

*This application is archived and there won't be any further improvement.*
