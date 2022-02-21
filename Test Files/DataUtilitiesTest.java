package org.jfree.data.test;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTest{
    Mockery mockClass=new Mockery();
    final Values2D table=mockClass.mock(Values2D.class);

    // equal
	@Test
	public void checkTwoArraysEqual() {
		double array[][] = new double[2][2];
		array[0][0] = 1d;
		array[0][1] = 2d;
		array[1][0] = 3d;
		array[1][1] = 4d;
		double doubleArray[][] = {
				{1d,2d},
				{3d,4d}
		};
		assertTrue(DataUtilities.equal(array, doubleArray));
	}
	 
	@Test
	public void checkTwoArraysEqualNull() {
		double array[][] = null;
		assertTrue(DataUtilities.equal(null, array));
	}
	
	@Test
	public void checkTwoArraysEqualNaN() {
		double array[][] = {
				{Math.log(-1), Math.log(-1)}
		};
		double array2[][] = {
				{Math.sqrt(-1), Math.sqrt(-1)}
		};
		assertTrue(DataUtilities.equal(array2, array));
	}
	
	@Test
	public void checkTwoArraysEqualEmpty() {
		double array[][] = {	 
		};
		double array2[][] = {
		};
		assertTrue(DataUtilities.equal(array2, array));
	}
	
	@Test
	public void checkTwoArraysNotEqual() {
		double array[][] = {	
				{1,2,3,4,5}
		};
		double array2[][] = {
				{1,2,3,4},
				{5}
		};
		assertFalse(DataUtilities.equal(array2, array));
	}
    
	@Test
    public void checkTwoArraysEqualBothNull() {
        double array[][] = null;
        double array2[][] = null;
        assertTrue(DataUtilities.equal(array, array2));
    }
	
    @Test
    public void checkTwoArraysEqualOneArrayNull() {
        double array[][] = null;
        double array2[][] = {
                {1,2,3,4},
                {5}
        };
        assertFalse(DataUtilities.equal(array, array2));
    }
    
    @Test
    public void checkTwoArrayEqualSecondArrayNull() {
        double array[][] = {{1,2,3,4}};
        double array2[][] = null;
        assertFalse(DataUtilities.equal(array, array2));
    }
    
    @Test
    public void checkTwoArraysNotEqual2() {
        double array[][] = {    
                {1,2,3,4,5}
        };
        double array2[][] = {
                {4,2,3,4}
        };
        assertFalse(DataUtilities.equal(array2, array));
    }
	
	// calculateColumnTotal 2 parameters
	@Test
    public void calculateColumnTotalNoCols(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(0));
        } });

        assertEquals("calculateColumnTotal should return 0.0", DataUtilities.calculateColumnTotal(table, 0),
                0.0, 0.000000001d);
    }

    @Test
    public void calculateColumnTotalAllPosInt(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(3));

            one(table).getValue(0, 0);
            will(returnValue(1));   // Row 0 column 0 has value 1

            one(table).getValue(1, 0);
            will(returnValue(2));   // Row 1 column 0 has value 2

            one(table).getValue(2, 0);
            will(returnValue(3));   // Row 2 column 0 has value 3
        } });

        assertEquals("calculateColumnTotal should return 6.0", DataUtilities.calculateColumnTotal(table, 0),
                6.0, 0.000000001d);
    }

    @Test
    public void calculateColumnTotalAllNegInt(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(2));

            one(table).getValue(0, 0);
            will(returnValue(-10));   // Row 0 column 0 has value -10

            one(table).getValue(1, 0);
            will(returnValue(-20));   // Row 1 column 0 has value -20
        } });

        assertEquals("calculateColumnTotal should return -30.0", DataUtilities.calculateColumnTotal(table, 0),
                -30.0, 0.000000001d);
    }

    @Test
    public void calculateColumnTotalMixedInt(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(5));

            one(table).getValue(0, 0);
            will(returnValue(-1));   // Row 0 column 0 has value -1

            one(table).getValue(1, 0);
            will(returnValue(-2));   // Row 1 column 0 has value -2

            one(table).getValue(2, 0);
            will(returnValue(3));   // Row 2 column 0 has value 3

            one(table).getValue(3, 0);
            will(returnValue(1));   // Row 3 column 0 has value 1

            one(table).getValue(4, 0);
            will(returnValue(-1));   // Row 4 column 0 has value -1
        } });

        assertEquals("calculateColumnTotal should return 0", DataUtilities.calculateColumnTotal(table, 0),
                0.0, 0.000000001d);
    }

    @Test
    public void calculateColumnTotalAllPosDec(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(4));

            one(table).getValue(0, 0);
            will(returnValue(1.1));   // Row 0 column 0 has value 1.1

            one(table).getValue(1, 0);
            will(returnValue(2.2));   // Row 1 column 0 has value 2.2

            one(table).getValue(2, 0);
            will(returnValue(3.3));   // Row 2 column 0 has value 3.3

            one(table).getValue(3, 0);
            will(returnValue(4.4));   // Row 3 column 0 has value 4.4
        } });

        assertEquals("calculateColumnTotal should return 11.0", DataUtilities.calculateColumnTotal(table, 0),
                11.0, 0.000000001d);
    }

    @Test
    public void calculateColumnTotalAllNegDec(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(3));

            one(table).getValue(0, 0);
            will(returnValue(-1.6));   // Row 0 column 0 has value -1.6

            one(table).getValue(1, 0);
            will(returnValue(-0.5));   // Row 1 column 0 has value -0.5

            one(table).getValue(2, 0);
            will(returnValue(-3.55));   // Row 2 column 0 has value -3.55
        } });

        assertEquals("calculateColumnTotal should return -5.65", DataUtilities.calculateColumnTotal(table, 0),
                -5.65, 0.000000001d);
    }

    @Test
    public void calculateColumnTotalMixedDec(){
        mockClass.checking(new Expectations(){ {
            one(table).getRowCount();
            will(returnValue(4));

            one(table).getValue(0, 0);
            will(returnValue(-0.77));   // Row 0 column 0 has value -1.6

            one(table).getValue(1, 0);
            will(returnValue(2.4));   // Row 1 column 0 has value -0.5

            one(table).getValue(2, 0);
            will(returnValue(8.8));   // Row 2 column 0 has value -3.55

            one(table).getValue(3, 0);
            will(returnValue(-6.4));   // Row 3 column 0 has value -6.4
        } });

        assertEquals("calculateColumnTotal should return 4.03", DataUtilities.calculateColumnTotal(table, 0),
                4.03, 0.000000001d);
    }
    
    @Test
	public void calculateColumnTotalDataContainsNullValue() {
	    mockClass.checking(new Expectations(){ {
		    one(table).getRowCount();
		    will(returnValue(1));
		
		    one(table).getValue(0, 0);
		    will(returnValue(null));   // Row 0 column 0 has null
	    } });
	    
	    final int column = 0;
	    
	    assertEquals("calculateColumnTotal(Values2D data, int column) should return 0.0",
	    		0.0, DataUtilities.calculateColumnTotal(table, column), 0.000000001d);
	}
	
	// calculateRowTotal 2 parameters
    @Test
    public void calculateRowTotalNoRows(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(0));
        } });

        assertEquals("calculateRowTotal should return 0.0", DataUtilities.calculateRowTotal(table, 0),
                0.0, 0.000000001d);
    }

    @Test
    public void calculateRowTotalAllPosInt(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(2));

            one(table).getValue(0, 0);
            will(returnValue(55));   // Row 0 column 0 has value 55

            one(table).getValue(0, 1);
            will(returnValue(55));   // Row 0 column 1 has value 55
        } });

        assertEquals("calculateRowTotal should return 110", DataUtilities.calculateRowTotal(table, 0),
                110.0, 0.000000001d);
    }

    @Test
    public void calculateRowTotalAllNegInt(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(3));

            one(table).getValue(0, 0);
            will(returnValue(-3));   // Row 0 column 0 has value -3

            one(table).getValue(0, 1);
            will(returnValue(-6));   // Row 0 column 1 has value -6

            one(table).getValue(0, 2);
            will(returnValue(-9));   // Row 0 column 2 has value -9
        } });

        assertEquals("calculateRowTotal should return -18", DataUtilities.calculateRowTotal(table, 0),
                -18.0, 0.000000001d);
    }

    @Test
    public void calculateRowTotalMixedInt(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(4));

            one(table).getValue(0, 0);
            will(returnValue(-6));   // Row 0 column 0 has value -6

            one(table).getValue(0, 1);
            will(returnValue(-7));   // Row 0 column 1 has value -7

            one(table).getValue(0, 2);
            will(returnValue(13));   // Row 0 column 2 has value 13

            one(table).getValue(0, 3);
            will(returnValue(2));   // Row 0 column 3 has value 2
        } });

        assertEquals("calculateRowTotal should return 2", DataUtilities.calculateRowTotal(table, 0),
                2.0, 0.000000001d);
    }

    @Test
    public void calculateRowTotalAllPosDec(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(3));

            one(table).getValue(0, 0);
            will(returnValue(5.5));   // Row 0 column 0 has value 5.5

            one(table).getValue(0, 1);
            will(returnValue(8.8));   // Row 0 column 1 has value 8.8

            one(table).getValue(0, 2);
            will(returnValue(4.3));   // Row 0 column 2 has value 4.3
        } });

        assertEquals("calculateRowTotal should return 18.6", DataUtilities.calculateRowTotal(table, 0),
                18.6, 0.000000001d);
    }

    @Test
    public void calculateRowTotalAllNegDec(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(4));

            one(table).getValue(0, 0);
            will(returnValue(-0.3));   // Row 0 column 0 has value -0.3

            one(table).getValue(0, 1);
            will(returnValue(-10.6));   // Row 0 column 1 has value -10.6

            one(table).getValue(0, 2);
            will(returnValue(-4.5));   // Row 0 column 2 has value -4.5

            one(table).getValue(0, 3);
            will(returnValue(-0.22));   // Row 0 column 3 has value -0.22
        } });

        assertEquals("calculateRowTotal should return -15.62", DataUtilities.calculateRowTotal(table, 0),
                -15.62, 0.000000001d);
    }

    @Test
    public void calculateRowTotalMixedDec(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(5));

            one(table).getValue(0, 0);
            will(returnValue(1.5));   // Row 0 column 0 has value 1.5

            one(table).getValue(0, 1);
            will(returnValue(-2.5));   // Row 0 column 1 has value -2.5

            one(table).getValue(0, 2);
            will(returnValue(-3.3));   // Row 0 column 2 has value -3.3

            one(table).getValue(0, 3);
            will(returnValue(0.44));   // Row 0 column 3 has value 0.44

            one(table).getValue(0, 4);
            will(returnValue(-10.7));   // Row 0 column 4 has value -10.7
        } });

        assertEquals("calculateRowTotal should return -14.56", DataUtilities.calculateRowTotal(table, 0),
                -14.56, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotalShouldReturnOne(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(1));
            
            one(table).getValue(0, 0);
            will(returnValue(1.0));
        } });

        assertEquals("calculateRowTotal should return 1.0", DataUtilities.calculateRowTotal(table, 0),
                1.0, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotalShouldReturnZero(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(1));
            
            one(table).getValue(0, 0);
            will(returnValue(null));
        } });

        assertEquals("calculateRowTotal should return 0.0", DataUtilities.calculateRowTotal(table, 0),
                0.0, 0.000000001d);
    }
	
    // calculateRowsTotal 3 parameters
    @Test
    public void calculateRowTotalShouldReturnTwo(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(1));
            
            one(table).getValue(0, 0);
            will(returnValue(2));
        } });
        
        int[] validRows = {0};

        assertEquals("calculateRowTotal should return 2.0", DataUtilities.calculateRowTotal(table, 0, validRows),
                2.0, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotalWithNullValShouldReturnZero(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(1));
            
            one(table).getValue(0, 0);
            will(returnValue(null));
        } });
        
        int[] validRows = {0};

        assertEquals("calculateRowTotal with only a null value should return 0.0", DataUtilities.calculateRowTotal(table, 0, validRows),
                0.0, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotalWithoutValidColsShouldReturnZero(){
        mockClass.checking(new Expectations(){ {
            one(table).getColumnCount();
            will(returnValue(1));
            
            one(table).getValue(0, 0);
            will(returnValue(1));
        } });
        
        int[] validRows = {1};

        assertEquals("calculateRowTotal without valid cols should return 0.0", DataUtilities.calculateRowTotal(table, 0, validRows),
                0.0, 0.000000001d);
    }
    
    // createNumberArray
    @Test
	public void createNumberArrayTest() {
		Number array[] = new Number[2];
		double double1 = 1;
		double double2 = 2;
		array[0] = double1;
		array[1] = double2;
		double doubleArray[] = {1,2};
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
    
	@Test
	public void createNumberArraysNotEqual() {
		Number array[] = new Number[2];
		int double1 = 1;
		double double2 = 2;
		array[0] = double1;
		array[1] = double2;
		double doubleArray[] = {1,2};
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertNotEquals(array[0], resultArray[0]);
	}
	
	@Test
	public void createNumberArraysEqualBig() {
		Number array[] = new Number[2];
		double double1 = 10000000000000000000d;
		double double2 = 20000000000000000000d;
		array[0] = double1;
		array[1] = double2;
		double doubleArray[] = {10000000000000000000d,20000000000000000000d};
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
	
	@Test
	public void createNumberArraysEqualBigNeg() {
		Number array[] = new Number[2];
		double double1 = -10000000;
		double double2 = 20000000;
		array[0] = double1;
		array[1] = double2;
		double doubleArray[] = {-10000000,20000000};
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
	
	@Test
	public void createNumberArraysEqualDecimal() {
		Number array[] = new Number[2];
		double double1 = -10000000.0987;
		double double2 = 20000000.8755;
		array[0] = double1;
		array[1] = double2;
		double doubleArray[] = {-10000000.0987,20000000.8755};
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
	
	@Test
	public void createBigNumberArrays() {
		Number array[] = new Number[20000];
		for(int i = 0; i<20000; i++) {
			array[i] = 1.1d;
		}
		double doubleArray[] = new double[20000];
		for(int i = 0; i<20000; i++) {
			doubleArray[i] = 1.1d;
		}
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createNumberArraysNullException() {
		double doubleArray[] = null;
		DataUtilities.createNumberArray(doubleArray);
	}
	
	@Test
	public void createNumberArrayBiggestDoubleValues() {
		Number array[] = new Number[3];
		for(int i = 0; i<3; i++) {
			array[i] = Double.MAX_VALUE;
		}
		double doubleArray[] = new double[3];
		for(int i = 0; i<3; i++) {
			doubleArray[i] = Double.MAX_VALUE;
		}
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
	
	@Test
	public void createNumberArrayLowestDoubleValues() {
		Number array[] = new Number[3];
		for(int i = 0; i<3; i++) {
			array[i] = Double.MIN_VALUE;
		}
		double doubleArray[] = new double[3];
		for(int i = 0; i<3; i++) {
			doubleArray[i] = Double.MIN_VALUE;
		}
		Number[] resultArray = DataUtilities.createNumberArray(doubleArray);
		assertArrayEquals(array, resultArray);
	}
	
    // getCumulativePercentages
	@Test
	public void cumulativePercentageAtZeroShouldBeTenPercent() {
		
		Mockery keyedValuesMock = new Mockery();
		final KeyedValues data = keyedValuesMock.mock(KeyedValues.class);
		
		keyedValuesMock.checking(new Expectations() {{
			atLeast(1).of(data).getItemCount();
			will(returnValue((2)));
			
			atLeast(1).of(data).getValue(0);
			will(returnValue(1));
			
			atLeast(1).of(data).getValue(1);
			will(returnValue(9));
			
			atLeast(1).of(data).getKey(0);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(1);
			will(returnValue(22));
			
		}});
		
		KeyedValues j = DataUtilities.getCumulativePercentages(data);
		assertEquals("The value at index 0 should be 0.1", 0.1, j.getValue(0).doubleValue(), .000000001d);
	}
	
	@Test
	public void cumulativePercentageAtZeroShouldBeTenPercentWithNullValue() {
		
		Mockery keyedValuesMock = new Mockery();
		final KeyedValues data = keyedValuesMock.mock(KeyedValues.class);
		
		keyedValuesMock.checking(new Expectations() {{
			atLeast(1).of(data).getItemCount();
			will(returnValue((3)));
			
			atLeast(1).of(data).getValue(0);
			will(returnValue(1));
			
			atLeast(1).of(data).getValue(1);
			will(returnValue(9));
			
			atLeast(1).of(data).getValue(2);
			will(returnValue(null));
			
			atLeast(1).of(data).getKey(0);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(1);
			will(returnValue(22));
			
			atLeast(1).of(data).getKey(2);
			will(returnValue(33));
			
		}});
		
		KeyedValues j = DataUtilities.getCumulativePercentages(data);
		assertEquals("The value at index 0 should be 0.1", 0.1, j.getValue(0).doubleValue(), .000000001d);
	}
	
	@Test
	public void cumulativePercentageAtOneShouldBeOneHundredPercent() {
		
		Mockery keyedValuesMock = new Mockery();
		final KeyedValues data = keyedValuesMock.mock(KeyedValues.class);
		
		keyedValuesMock.checking(new Expectations() {{
			atLeast(1).of(data).getItemCount();
			will(returnValue((2)));
			
			atLeast(1).of(data).getValue(0);
			will(returnValue(1));
			
			atLeast(1).of(data).getValue(1);
			will(returnValue(9));
			
			atLeast(1).of(data).getKey(0);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(1);
			will(returnValue(22));
			
		}});
		
		KeyedValues j = DataUtilities.getCumulativePercentages(data);
		assertEquals("The value at index 1 should be 1.0", 1, j.getValue(1).doubleValue(), .000000001d);
	}
	
	@Test
	public void keyAtZeroShouldBeEleven() {
		
		Mockery keyedValuesMock = new Mockery();
		final KeyedValues data = keyedValuesMock.mock(KeyedValues.class);
		
		keyedValuesMock.checking(new Expectations() {{
			atLeast(1).of(data).getItemCount();
			will(returnValue((2)));
			
			atLeast(1).of(data).getValue(0);
			will(returnValue(1));
			
			atLeast(1).of(data).getValue(1);
			will(returnValue(9));
			
			atLeast(1).of(data).getKey(0);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(1);
			will(returnValue(22));
			
		}});
		
		KeyedValues j = DataUtilities.getCumulativePercentages(data);
		assertEquals("The key at index 0 should be 11", 11, j.getKey(0));
	}
	
	@Test
	public void keyAtOneShouldBeTwentyTwo() {
		
		Mockery keyedValuesMock = new Mockery();
		final KeyedValues data = keyedValuesMock.mock(KeyedValues.class);
		
		keyedValuesMock.checking(new Expectations() {{
			atLeast(1).of(data).getItemCount();
			will(returnValue((2)));
			
			atLeast(1).of(data).getValue(0);
			will(returnValue(1));
			
			atLeast(1).of(data).getValue(1);
			will(returnValue(9));
			
			atLeast(1).of(data).getKey(0);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(1);
			will(returnValue(22));
			
		}});
		
		KeyedValues j = DataUtilities.getCumulativePercentages(data);
		assertEquals("The key at index 1 should be 22", 22, j.getKey(1));
	}
	
	@Test
	public void cumulativePercentageAtZeroShouldBeNegTenPercent() {
		
		Mockery keyedValuesMock = new Mockery();
		final KeyedValues data = keyedValuesMock.mock(KeyedValues.class);
		
		keyedValuesMock.checking(new Expectations() {{
			atLeast(1).of(data).getItemCount();
			will(returnValue((2)));
			
			atLeast(1).of(data).getValue(0);
			will(returnValue(-1));
			
			atLeast(1).of(data).getValue(1);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(0);
			will(returnValue(11));
			
			atLeast(1).of(data).getKey(1);
			will(returnValue(22));
			
		}});
		
		KeyedValues j = DataUtilities.getCumulativePercentages(data);
		assertEquals("The value at index 0 should be -0.1", -0.1, j.getValue(0).doubleValue(), .000000001d);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullValuesShouldRaiseIllegalArgumentException() {
		final KeyedValues data = null;
		DataUtilities.getCumulativePercentages(data);
	}
	
	// calculateColumnTotal(Values2D data, int column, int[] validRows)
	@Test(expected = IllegalArgumentException.class)
	public void calculateColumnTotalThreeParamsIllegalArgs() {
		final Values2D data = null;
		final int column = 0;
		final int[] validRows = {0};
		DataUtilities.calculateColumnTotal(data, column, validRows);
	}
	
	@Test
	public void calculateColumnTotalThreeParamsDataContainsNullValue() {
	    mockClass.checking(new Expectations(){ {
		    one(table).getRowCount();
		    will(returnValue(1));
		
		    one(table).getValue(0, 0);
		    will(returnValue(null));   // Row 0 column 0 has null
	    } });
	    
	    final int column = 0;
		final int[] validRows = {0};
	    
	    assertEquals("calculateColumnTotal(Values2D data, int column, int[] validRows) should return 0.0",
	    		0.0, DataUtilities.calculateColumnTotal(table, column, validRows), 0.000000001d);
	}
	
	@Test
	public void calculateColumnTotalThreeParamsOfFirstAndThirdRow() {
	    mockClass.checking(new Expectations(){ {
		    one(table).getRowCount();
		    will(returnValue(3));
		
		    one(table).getValue(0, 0);
		    will(returnValue(-1));   // Row 0 column 0 has value -1
		    
		    one(table).getValue(1, 0);
		    will(returnValue(2));   // Row 1 column 0 has value 2
		    
		    one(table).getValue(2, 0);
		    will(returnValue(5));   // Row 2 column 0 has value 5
	    } });
	    
	    final int column = 0;
		final int[] validRows = {0, 2};	// first and third rows only
	    
	    assertEquals("calculateColumnTotal(Values2D data, int column, int[] validRows) should return 4.0",
	    		4.0, DataUtilities.calculateColumnTotal(table, column, validRows), 0.000000001d);
	}
	
	@Test
	public void calculateColumnTotalThreeParamsNonExistentValidRowsEntry() {
	    mockClass.checking(new Expectations(){ {
		    one(table).getRowCount();
		    will(returnValue(3));
		
		    one(table).getValue(0, 0);
		    will(returnValue(-10.5));   // Row 0 column 0 has value -10.5
		    
		    one(table).getValue(1, 0);
		    will(returnValue(2));   	// Row 1 column 0 has value 2
		    
		    one(table).getValue(2, 0);
		    will(returnValue(5.5));		// Row 2 column 0 has value 5.5
	    } });
	    
	    final int column = 0;
		final int[] validRows = {0, 2, 5};	// first, third and sixth(non-existent)
	    
	    assertEquals("calculateColumnTotal(Values2D data, int column, int[] validRows) should return -5.0",
	    		-5.0, DataUtilities.calculateColumnTotal(table, column, validRows), 0.000000001d);
	}
    
	// clone
	@Test
    public void cloneTest(){
        double[][] array={{1.0}, {2.0}, null};
        double[][] clonedArray=DataUtilities.clone(array);

        assertArrayEquals("The arrays should be equal", array, clonedArray);
    }
	
	
    // createNumberArray2D
    @Test
    public void create2DnumberArray() {
        double array[][] = {
                {1,2,3,4},
                {1,2,3,4}
        };
        Number numberArray[][] = {
                {1.0,2.0,3.0,4.0},
                {1.0,2.0,3.0,4.0}
        };
        assertTrue(Arrays.deepEquals(numberArray, DataUtilities.createNumberArray2D(array)));
    }
	
}