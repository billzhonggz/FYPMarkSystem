## Usage of FYP Mark System

* Group **Goodness**
* Written by **Junru Zhong**

## Necessary Files

You are now in the *executable* folder, which contents the files required by the system. Here is a list and descriptions for these files. 

* `FYPMarkSystem.jar` Main executable file of the system. Requires **Java Running Environment**.
* `fyp.db` The database file. Do not delete it. 
* `FYPGradeReport.xlsx` The sample exported grade report. New report will cover the old one. 
* `input/student.xlsx` The sample student list for import. Please **DO NOT** change the header of this file. 

## First Start

* When you execute the application, please enter the default password `111111`. 
* Since the database is empty, you will be led to the `Add Item` page. Please enter exactly 12 evaluation items and their weighting in percentage. Please be sure that their sum equals to 100. 
* Then you will be led to the `Import Student List` page. You can click `Import` to import a student list in Excel format. Please prepare the file `input/student.xlsx` before you enter the button. 
* Then you will be led to the `Enter Mark` page. Select a `Evaluation Group` from the top-right drop down list, select a student from the table on the left, and input his/her grades on the right-hand-side list. Click `Save Current Student` button to save the grade of current student before proceed to the next. 
* Click `Export Grade Report` to sorting and exporting the grade list. The grade report will be exported to the current folder right with the jar file. 

## Normal Start

* When you execute the application, please enter the default password `111111`. 
* Since the database is not empty, you will be directed lead to the `Enter Mark` page. You can do everything exactly as same as before. 
* If you want to add a new group for evaluation (including independent *evaluation items* and *student list*), click `Add Group` button. Then everything works as same as above. 