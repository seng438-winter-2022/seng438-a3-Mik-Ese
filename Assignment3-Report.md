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
This lab was used to teach students about unit testing designed using white-box testing. Additionally it familiarized the students with Eclipse IDE, for Java development and its EclEmma coverage tool. Before the lab we were aware of the utilization of JUnit 4 to perform unit tests and were familiar with black-box testing. Over the course of the lab, we gained experience with the EclEmma add-on and the inspection of the data-flow for our unit tests. With this we tested certain classes within the JFreeChart library. We used this [test plan](https://github.com/seng438-winter-2022/seng438-a3-Mik-Ese/blob/main/SENG%20438%20A3%20Test%20Plan.pdf) to develop unit tests.

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
We developed unit tests while viewing the source code for the method we were testing. After which we used EclEmma to view the Instruction, Branches, and Methods Coverage, and reviewed the source code and its tests, we continued this cycle until we were satisfied with the coverage achieved (ideally greater than 95%).

# A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

`Range` - `combineIgnoringNaN(Range, Range)`
- We started by writing test cases that explored each outcome of the three outer if statements:\
`if (range1 == null)`\
`if (range2 == null)`\
`if (Double.isNaN(l) && Double.isNaN(u))`
- We then explored the following nested if statements and added unit tests that considered the alternative branch.\
`if (range2 != null && range2.isNaNRange())`\
`if (range1.isNaNRange())`
- Finally we checked the coverage with EclEmma and made sure that the coverage metrics were up to our standards

`Range` - `expand(Range, double, double)`
- We started by writing test cases that explored each outcome of the paramChecks and if statement:\
`ParamChecks.nullNotPermitted(range, "range")`\
`if (lower > upper)`
- We then checked the coverage with EclEmma and made sure that the coverage metrics were up to our standards

`Range` - `constrain(double)`
- We started by writing test cases that explored each outcome of the outer if statement:\
`if (!contains(value))`
- We then explored the following nested if statements and added unit tests that considered the alternative branch.\
`if (value > this.upper)`\
`else if (value < this.lower)`\
and the implied `else` statement
- Finally we checked the coverage with EclEmma and made sure that the coverage metrics were up to our standards

`DataUtilities` - `getCumulativePercentages(KeyedValues)`
- We started by writing test cases that explored each outcome of the paramChecks and the `for` loops following:\
`ParamChecks.nullNotPermitted(data, "data")`\
`for (int i = 0; i < data.getItemCount(); i++)` (twice)

- We then explored the following nested if statements and added unit tests that considered the alternative branch.\
`if (v != null)` (twice)
- Finally we checked the coverage with EclEmma and made sure that the coverage metrics were up to our standards

`DataUtilities` - `calculateRowTotal(Values2D, int, int[])`
- We started by writing test cases that explored each outcome of the paramChecks and the `for` loop following:\
`ParamChecks.nullNotPermitted(data, "data")`\
`for (int v = 0; v < validCols.length; v++)`
- We then explored the following nested if statements and added unit tests that considered the alternative branch.\
`if (col < colCount)`
- The step above was repeated for the further nested if statement:\
`if (n != null)`
- Finally we checked the coverage with EclEmma and made sure that the coverage metrics were up to our standards

# A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# Pros and Cons of coverage tools used and Metrics you report
Tool Used: EclEmma addon for Eclipse IDE
### Pros:
- The tool embedded with the IDE used (Eclipse) no additional 3rd party software needed
- varity of coverage metrics (Instructions, Branches, Lines, Methods, and Complexity coverage)
- Color Coding illustrating the specific parts of the method completely, partially or not at all covered in the test cases 
### Cons:
- In the case of multiple conditions in a single if/else statement, the branch covered and the one not covered are not distinguished.

# A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.
### advantages of requirements-based test generation:
- simplicity of test creation based on requirements boundaries
- promotes a more creative approach to testing the methods
- Do not need to explore the intricacies of the source code to develop tests
### disadvantages of requirements-based test generation:
- limits tests to superficial understanding of the requirements of a method
- lack of confirmation in regards to the extensiveness of the tests

### advantages of coverage-based test generation:
- extensiveness of error checking
- more targeted unit tests
### disadvantages of coverage-based test generation:
- requires deep understanding of the source code being tested
- Achieveing full coverage, does not guarantee the fulfillment of all technical requirements
- time commitment required to achieve full coverage on larger projects

# A discussion on how the team work/effort was divided and managed
Everyone worked together to write up the Test Plan and decide the pairs.

The tests were developed in 2 pairs:
- Michele Esercitato and Dylan Mah 
- Faisal Hossain and Cheyenne Goh

The first pair covered the test cases for the `Range` class and the second pair the `DataUtilities` class. After which, the pairs switched their target classes and reviewed the test cases built.

# Any difficulties encountered, challenges overcome, and lessons learned from performing the lab
### Difficulties Encountered
- Developing unit tests to get higher code coverage metrics

### Challenges Overcome
- Understanding the source code to better develop unit tests 

### Lessons Learned
- How to use EclEmma
- How to create JUnit tests to effectively explore all possible paths of a program

# Comments/feedback on the lab itself
Interesting lab to learn how to use code coverage tools and more testing techniques
