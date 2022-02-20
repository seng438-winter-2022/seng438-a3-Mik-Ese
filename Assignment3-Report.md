**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      | 27 |
| -------------- | --- |
| Michele | Esercitato |
| Dylan | Mah |
| Faisal | Hossain |
| Cheyenne | Goh |

1. [Introduction](#Introduction)
2. [Manual data-flow coverage calculations for X and Y methods](#Manual-data-flow-coverage-calculations-for-X-and-Y-methods)
3. [A detailed description of the testing strategy for the new unit test](#A-detailed-description-of-the-testing-strategy-for-the-new-unit-test)
4. [A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage](#A-high-level-description-of-five-selected-test-cases-you-have-designed-using-coverage-information-and-how-they-have-increased-code-coverage)
5. [A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)](#A-detailed-report-of-the-coverage-achieved-of-each-class-and-method-a-screen-shot-from-the-code-cover-results-in-green-and-red-color-would-suffice)
6. [Pros and Cons of coverage tools used and Metrics you report](#Pros-and-Cons-of-coverage-tools-used-and-Metrics-you-report)
7. [A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.](#A-comparison-on-the-advantages-and-disadvantages-of-requirements-based-test-generation-and-coverage-based-test-generation.)
8. [A discussion on how the team work/effort was divided and managed](#A-discussion-on-how-the-team-work/effort-was-divided-and-managed)
9. [Any difficulties encountered, challenges overcome, and lessons learned from performing the lab](#Any-difficulties-encountered-challenges-overcome-and-lessons-learned-from-performing-the-lab)
10. [Comments/feedback on the lab itself](#Comments/feedback-on-the-lab-itself)


# Introduction
This lab was used to teach students about unit testing designed using white-box testing. Additionally it familiarized the students with Eclipse IDE, for Java development and its EclEmma coverage tool. Before the lab we were aware of the utilization of JUnit 4 to perform unit tests and were familiar with black-box testing. Over the course of the lab, we gained experience with the EclEmma add-on and the inspection of the data-flow for our unit tests. With this we tested certain classes within the JFreeChart library.

# Manual data-flow coverage calculations for X and Y methods
![Data Flow Graph 1](https://github.com/seng438-winter-2022/seng438-a3-Mik-Ese/blob/main/DataGraphs/SENG438%20DFGs-calculateColumnTotal.jpg)

![Data Flow Graph 2](https://github.com/seng438-winter-2022/seng438-a3-Mik-Ese/blob/main/DataGraphs/SENG438%20DFGs-upperBound.jpg)

```
calculateColumnTotal:
def(1) = {data, column, total, rowCount}
use(1) = {}

def(2) = {r}
use(2) = {}

def(3) = {}
use(3) = {n, total}

def(4) = {}
use(4) = {}

def(5) = {}
use(5) = {n, r, column, rowCount}

def(6) = {}
use(6) = {total}


upperBound:
def(1) = {}
use(1) = {lower}
```

```
calculateColumnTotal:
du(1, 1, data) = {1}
du(1, 5, column) = {[1, 2, 3, 5], {1, 2, 4, 5}}
du(1, 6, total) = {[1, 2, 3, 5, 6] , [1, 2, 4, 5, 6]}
du(1, 5, rowCount) = {[1, 2, 3, 5], [1, 2, 4, 5]}
du(2, 3, n) = {2, 3}


upperBound:
du(1, 1, lower) = {1}
```

```
upperBoundShouldBeOne: du(1, 1, lower)
upperBoundShouldNotBeAboveOne: du(1, 1, lower)
upperBoundShouldNotBeBelowOne: du(1, 1, lower)
upperBoundShouldBeTwoBil: du(1, 1, lower)

calculateColumnTotalForFiveValues: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalNoCols: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalNegCol: du(1, 1, data), du(1, 2, column), du(1, 2, total), du(1, 2, rowCount), du(2, 2, n)
calculateColumnTotalColOutOfBounds: du(1, 1, data), du(1, 2, column), du(1, 2, total), du(1, 2, rowCount), du(2, 2, n)
calculateColumnTotalAllPosInt: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalAllNegInt: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalMixedInt: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalAllPosDec: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalAllNegDec: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
calculateColumnTotalMixedDec: du(1, 1, data), du(1, 5, column), du(1, 6, total), du(1, 5, rowCount), du(2, 3, n)
```

```
upperBound: coverage = 1 / 1 * 100% = 100%

calculateColumnTotal - if all columns are filled: coverage = 8/8 * 100% = 100%
```

# A detailed description of the testing strategy for the new unit test
The Unit tests were devised according to the principles of white-box testing.

# A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# Pros and Cons of coverage tools used and Metrics you report

Text…

# A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# A discussion on how the team work/effort was divided and managed

Text…

# Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# Comments/feedback on the lab itself

Text…
