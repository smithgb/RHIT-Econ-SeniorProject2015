# RHIT-Econ-SeniorProject2015
Code used for aggregating data for my Senior Project in Economics

*This Software Operates Under the GNU Affero GPL v3.0 Licens*

# Table of Contents
  * [Project Description](https://github.com/smithgb/RHIT-Econ-SeniorProject2015/blob/master/README.md#project-description)
  * [Libraries Used](https://github.com/smithgb/RHIT-Econ-SeniorProject2015/blob/master/README.md#libraries-used)
  * [Installation Instructions](https://github.com/smithgb/RHIT-Econ-SeniorProject2015/blob/master/README.md#installation-instructions)
  * [Copyright & License](https://github.com/smithgb/RHIT-Econ-SeniorProject2015/blob/master/README.md#copyright-&-license)
  * [Authors](https://github.com/smithgb/RHIT-Econ-SeniorProject2015/blob/master/README.md#authors)
  
# Project Description
## Backstory
  My Senior project in Economics required me to gather historical data for various macroeconomic variables. I wanted my observations to be in quarterly frequency. Some were easy to obtain via the FRED Database Excel plugin. A couple other variables, particularly the S&P 500 index, could not easily be found in quarters or the data provided by FRED did not go back far enough.
## Purpose
  The program was written to take data that was not in quarterly format and output it to quarterly format.
## What It Does
  Given data in a frequency that is finer than quarterly, this program will convert it into data that is dated quarterly. The average technique that is used is a simple full-sample average. For example, if your data is in monthly frequency, the first three observations of the year (January, February, March) will be average to obtain the value for the 1st Quarter (Q1). However, if your data is in daily frequency, each data point from 1 January to 31 March will be averaged. This program does not look for uneven distribution of data points within a quarter's period. It simply grabs all datapoints within Qi (where i = 1,2,3,4) and calculates an average.

When running the program, a file chooser is presented at start for convenient file input where the user will choose the file containing the data to be aggregated.

The default output setting is to round averages to two decimal points. In order to change this the source code must be altered manually at the end of the Main.java file.

**Look for:**
```java
for(Map.Entry<Integer, ...
  ...
  for(Integer i = ...
    ...
    ... divide(count, 2, RoundingMode.HALF_UP);
```
## How It Works
#### Input Requirements
The input file must be a properly formatted CSV with dates in the format `mm/dd/yyyy`. For each line in the CSV, dates are assumed to come first, followed by the data for that observation.

#### Output Specifications
The output file will be placed in the same directory as the input file with the name `<input file name>ByQuarter.csv`. The CSV will have the headers *Date* and *Average*. Each line of the file will be an observation with the date followed by the average value. Dates will be formatted: `yyyyQi` (i = 1...4).
## Future Improvements

# Libraries Used
### JDK Packages
  - java.io
  - java.util
  - java.lang
  - java.math
  - java.swing

# Installation Instructions
### Jar File
A prebuilt .jar file is included for convenience.
### Eclipse
Import the source code into Eclipse and run.

# Copyright & License
Please see the included LICENSE file.

# Authors & Contact Info
Just me.
TODO
