package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range exampleRange2;
    private Range exampleRange3;
    private Range exampleRange4;
    private Range exampleRange5;
    private Range exampleRange6;
    private Range exampleRange7;
    
//    @BeforeClass public static void setUpBeforeClass() throws Exception {
//    }

    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);	// 0 central value
    	exampleRange2 = new Range(1, 2);	// 1.5 central value
    	exampleRange3 = new Range(-2, -1);	// -1.5 central value
    	exampleRange4 = new Range(-1000000000, 2000000000);	// 500000000 central value
    	exampleRange5 = new Range(Double.NaN, 1);
    	exampleRange6 = new Range(-1, Double.NaN);
    	exampleRange7 = new Range(Double.NaN, Double.NaN);
    }

    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldBeOnePointFive() {
        assertEquals("The central value of 1 and 2 should be 1.5",
        1.5, exampleRange2.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldBeNegOnePointFive() {
        assertEquals("The central value of -2 and -1 should be -1.5",
        -1.5, exampleRange3.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldNotBeAboveZero() {
        assertNotEquals("The central value of -1 and 1 should not be above 0",
        0.000001, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldNotBeBelowZero() {
        assertNotEquals("The central value of -1 and 1 should not be below 0",
        -0.000001, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldBeFiveHundMil() {
        assertEquals("The central value of -1000000000 and 2000000000 should be 500000000",
        500000000, exampleRange4.getCentralValue(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldBeOne() {
    	assertEquals("The upper bound value of -1 and 1 should be 1",
    	1, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldNotBeAboveOne() {
    	assertNotEquals("The upper bound value of -1 and 1 should not be above 1",
    	1.000001, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldNotBeBelowOne() {
    	assertNotEquals("The upper bound value of -1 and 1 should not be below 1",
    	0.999999, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    public void upperBoundShouldBeTwoBil() {
        assertEquals("The upper bound value of -1000000000 and 2000000000 should be 2000000000",
        2000000000, exampleRange4.getUpperBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldBeNegOne() {
    	assertEquals("The lower bound value of -1 and 1 should be -1",
    	-1, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldNotBeAboveNegOne() {
    	assertNotEquals("The lower bound value of -1 and 1 should be above -1",
    	-0.999999, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldNotBeBelowNegOne() {
    	assertNotEquals("The lower bound value of -1 and 1 should be below -1",
    	-1.000001, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldBeNegOneBil() {
        assertEquals("The lower bound value of -1000000000 and 2000000000 should be -1000000000",
        -1000000000, exampleRange4.getLowerBound(), .000000001d);
    }
    
    @Test
    public void rangeShouldContainZeroPointNine() {
        assertTrue("The range of -1 and 1 should contain 0.9",
        exampleRange.contains(0.9));
    }
    
    @Test
    public void rangeShouldContainNegZeroPointNine() {
        assertTrue("The range of -1 and 1 should contain -0.9",
        exampleRange.contains(-0.9));
    }
    
    @Test
    public void rangeShouldNotContainValHigherThanOne() {
        assertFalse("The range of -1 and 1 should not contain 1.000001",
        exampleRange.contains(1.000001));
    }
    
    @Test
    public void rangeShouldNotContainValLowerThanNegOne() {
        assertFalse("The range of -1 and 1 should not contain -1.000001",
        exampleRange.contains(-1.000001));
    }
    
    @Test
    public void neitherBoudryIsNaN() {
        assertFalse("The range of -1 and 1 should should not be NaN",
        exampleRange.isNaNRange());
    }
    
    @Test
    public void lowerBoundryIsNotNaNUpperBoudryIsNaN() {
        assertFalse("The range of Double.NaN and 1 should should not be NaN",
        exampleRange5.isNaNRange());
    }
    
    @Test
    public void lowerBoundryIsNaNUpperBoudryIsNotNaN() {
        assertFalse("The range of -1 and Double.NaN should should not be NaN",
        exampleRange6.isNaNRange());
    }
    
    @Test
    public void rangeBoundriesAreNaN() {
        assertTrue("The range of Double.NaN and Double.NaN should should be NaN",
        exampleRange7.isNaNRange());
    }
    
    // combine
    @Test
    public void combineRangesFirstRangeIsNull() {
    	assertEquals("The resulting range by combining null and [1, 2] should be [1, 2]",
    	exampleRange2, Range.combine(null, exampleRange2));
    }
    
    @Test
    public void combineRangesSecondRangeIsNull() {
    	assertEquals("The resulting range by combining [1, 2] and null should be [1, 2]",
    	exampleRange2, Range.combine(exampleRange2, null));
    }
    
    @Test
    public void lowerBoundryOfCombinedRangesIsNegOne() {
    	assertEquals("The lower boundry of the resulting range by combining [-1, 1] and [1, 2] is -1",
    	-1, Range.combine(exampleRange, exampleRange2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void upperBoundryOfCombinedRangesIsTwo() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, 1] and [1, 2] is 2",
    	2, Range.combine(exampleRange, exampleRange2).getUpperBound(), .000000001d);
    }
    
    // combineIgnoringNaN
    @Test
    public void combineRangesIgnoringNaNFirstRangeIsNull() {
    	assertEquals("The resulting range by combining null and [1, 2] ignoring NaN should be [1, 2]",
    	exampleRange2, Range.combineIgnoringNaN(null, exampleRange2));
    }
    
    @Test
    public void combineRangesIgnoringNaNFirstRangeIsNullSecondRangeIsNaN() {
    	assertNull("The resulting range by combining null and NaN range ignoring NaN should be null",
    	Range.combineIgnoringNaN(null, exampleRange7));
    }
    
    @Test
    public void combineRangesIgnoringNaNSecondRangeIsNull() {
    	assertEquals("The resulting range by combining [1, 2] and null ignoring NaN should be [1, 2]",
    	exampleRange2, Range.combineIgnoringNaN(exampleRange2, null));
    }
    
    @Test
    public void combineRangesIgnoringNaNFirstRangeIsNaNSecondRangeIsNull() {
    	assertNull("The resulting range by combining NaN range and null ignoring NaN should be null",
    	Range.combineIgnoringNaN(exampleRange7, null));
    }
    
    @Test
    public void combineRangesIgnoringNaNBothRangesAreNaN() {
    	assertNull("The resulting range by combining NaN range and NaN range ignoring NaN should be null",
    	Range.combineIgnoringNaN(exampleRange7, exampleRange7));
    }
    
    @Test
    public void combineRangesIgnoringNaNBothRangesAreNull() {
    	assertNull("The resulting range by combining 2 null ranges ignoring NaN should be null",
    	Range.combineIgnoringNaN(null, null));
    }
    
    @Test
    public void lowerBoundryOfCombinedRangesIgnoringNaNIsNegOne() {
    	assertEquals("The lower boundry of the resulting range by combining [-1, 1] and [1, 2] ignoring NaN is -1",
    	-1, Range.combineIgnoringNaN(exampleRange, exampleRange2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void upperBoundryOfCombinedRangesIgnoringNaNIsTwo() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, 1] and [1, 2] ignoring NaN is 2",
    	2, Range.combineIgnoringNaN(exampleRange, exampleRange2).getUpperBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundaryofCombineRangesIgnoringNaNAndBothLowerBoundariesAreNaN() {
    	assertEquals("The lower boundry of the resulting range by combining [NaN, 1] and [NaN, 1] ignoring NaN should be NaN",
    	Double.NaN, Range.combineIgnoringNaN(exampleRange5, exampleRange5).getLowerBound(), .000000001d);
    }
    
    @Test
    public void upperBoundaryofCombineRangesIgnoringNaNAndBothUpperBoundariesAreNaN() {
    	assertEquals("The upper boundry of the resulting range by combining [-1, NaN] and [-1, NaN] ignoring NaN should be NaN",
    	Double.NaN, Range.combineIgnoringNaN(exampleRange6, exampleRange6).getUpperBound(), .000000001d);
    }
    
    // equals
    @Test
    public void doubleNotEqualRange() {
    	assertFalse("A Double should not be equal to a Range",
    	exampleRange.equals((Double) 1.5d));
    }
    
    @Test
    public void lowerBoundaryMismatch() {
        assertFalse("The lower boundary of [-1, 1] does not match the range [NaN, 1]",
        exampleRange.equals(exampleRange5));
    }
    
    @Test
    public void upperBoundaryMismatch() {
        assertFalse("The upper boundary of [-1, 1] does not match the range [-1, NaN]",
        exampleRange.equals(exampleRange6));
    }
    
    @Test
    public void bothBoundariesMismatch() {
        assertFalse("The boundaries of [-1, 1] should not match the boundaries of the range [-1000000000, 2000000000]",
        exampleRange.equals(exampleRange4));
    }
    
    @Test
    public void rangeEqualsItself() {
    	assertTrue("The range [-1, 1] should equal the range [-1, 1]",
    	exampleRange.equals(new Range(-1, 1)));
    }
    
    // toString
    @Test
    public void rangeToString() {
    	assertEquals("The range [-1, 1] converted to a string should result in \"Range[-1.0,1.0]\"",
    	"Range[-1.0,1.0]", exampleRange.toString());
    }
    
    @Test
    public void rangeToStringWithNaN() {
    	assertEquals("The range [NaN, 1] converted to a string should result in \"Range[NaN,1.0]\"",
    	"Range[NaN,1.0]", exampleRange5.toString());
    }
    
 // 2 tests for expand() and getLength()
    @Test
    public void lowerBoundShoulBeZero() {
        Range testRange = Range.expand(exampleRange, -2.0, -2.0);
        assertEquals("The range expanded by margins of -2.0 should have a lower boundary of 0", 0, testRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShoulBeNegFive() {
        Range testRange = Range.expand(exampleRange, 2.0, 2.0);
        assertEquals("The range expanded by margins of 2.0 should have a lower boundary of -5.0", -5.0, testRange.getLowerBound(), .000000001d);
    }
    
    // 4 tests for expandToInclude()
    @Test
    public void lowerBoundShouldBeOne() {
        Range testRange = Range.expandToInclude(null, 1.0);
        assertEquals("The null range expanded to include 1.0 should have a lower boundary of 1.0", 1.0, testRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void lowerBoundShouldBeNegTwo() {
        Range testRange = Range.expandToInclude(exampleRange, -2.0);
        assertEquals("The range expanded to include -2.0 should have a lower boundary of -2.0", -2.0, testRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldBeThree() {
        Range testRange = Range.expandToInclude(exampleRange, 3.0);
        assertEquals("The range expanded to include 3.0 should have a upper boundary of 3.0", 3.0, testRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void upperBoundShouldBeTwo() {
        Range testRange = Range.expandToInclude(exampleRange2, 1.5);
        assertEquals("The range expanded to include 1.5 should have a upper boundary of 2.0", 2.0, testRange.getUpperBound(), .000000001d);
    }
    
    // hashCode
    @Test
    public void range1HashCodeTest(){
        assertEquals("The hashcode of -1 and 1 should be -31457280", -31457280,
                exampleRange.hashCode(), 0.000000001d);
    }
    
    // intersects Range parameter
    @Test
    public void range1And2IntersectsTest(){
        assertFalse("The range of (-1, 1) and (1, 2) should not intersect",
                exampleRange.intersects(exampleRange2));
    }
    
    // intersects doubles parameters
    @Test
    public void intersectsWithUpperHalfTest(){
        assertTrue("The range of (-5, 5) should not intersect with the range of (-1, 1)",
                exampleRange.intersects(0, 5));
    }

    @Test
    public void intersectsWithLowerHalfTest(){
        assertTrue("The range of (-5, 5) should not intersect with the range of (-1, 1)",
                exampleRange.intersects(-5, 0));
    }
    
    @Test
    public void intersectsNotWithLowerHalfTest(){
        assertFalse("The range of (-2, 1) should not intersect with the range of (-1, 1)",
                exampleRange.intersects(-2, -1));
    }

    @Test
    public void intersectsNotWithUpperHalfTest(){
        assertFalse("The range of (1, 1) should not intersect with the range of (-1, 1)",
                exampleRange.intersects(1, 2));
    }
    
    @Test
    public void intersectsNotWithUpperHalfTest2(){
        assertFalse("The range of (0, -1) should not intersect with the range of (-1, 1)",
                exampleRange.intersects(0, -1));
    }
    
    // constrain
    @Test
    public void constrainShouldReturnOne() {
        assertEquals("The range closest to 2.0 should be 1.0", 1.0, exampleRange.constrain(2.0), .000000001d);
    }
    
    @Test
    public void constrainShouldReturnNegOne() {
        assertEquals("The range closest to -2.0 should be -1.0", -1.0, exampleRange.constrain(-2.0), .000000001d);
    }
    
    @Test
    public void constrainShouldReturnZero() {
        assertEquals("The range closest to 0 should be 0", 0, exampleRange.constrain(0), .000000001d);
    }
    
    @Test
    public void constrainShouldReturnNaN() {
        assertEquals("The range closest to NaN should be NaN", Double.NaN, exampleRange.constrain(Double.NaN), .000000001d);
    }
    
    // scale
    @Test
    public void scaleIncreasesRange() {
        Range compareRange = new Range(-2,2);
        assertEquals(compareRange, Range.scale(exampleRange, 2));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void scaleNegativeFactor() {
        Range.scale(exampleRange, -1);
    }
    
    // shift 2 parameters
    @Test
    public void shiftRange() {
        Range resultRange = new Range(0, 2);
        assertEquals(resultRange, Range.shift(exampleRange, 1));
        
    }
    
    @Test
    public void shiftRange2() {
        Range resultRange = new Range(2, 2);
        Range exRange = new Range(0,0);
        assertEquals(resultRange, Range.shift(exRange, 2));
        
    }
    
    // shift 3 parameters
    @Test
    public void shiftRangeAllowZero() {
        Range resultRange = new Range(1, 3);
        assertEquals(resultRange, Range.shift(exampleRange, 2, true));
        
    }
    
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//    }
}