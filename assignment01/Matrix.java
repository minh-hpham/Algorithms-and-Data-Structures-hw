package assignment01;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
		
	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int data[][])
	{
		numRows = data.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // d[0] is the first 1D array
		}
		this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other)
	{
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		// make sure neither of the matrix is empty
		if (this.numColumns<=0 ||matrix.numColumns<=0 || this.numRows<=0 || matrix.numRows<=0)return false;
		
		// make sure the left and right matrix has the same number of columns and rows
		if (matrix.numColumns!=this.numColumns || matrix.numRows!=this.numRows)return false;
		
		// compare each item of the two matrix, return false if reaches an discrepency
		for (int i=0; i<matrix.numRows; i++)
		{
			for (int j=0; j<matrix.numColumns; j++)
			{
				if (this.data[i][j]!=matrix.data[i][j]) return false;
			}
		}
		// return true if all values are compared and no discrepency found
		return true; 
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		// String variable to contain all values in the Matrix
		String matrixString="";
		
		// Add all vaules in the matrix to the String variable
		for (int i=0; i<this.numRows; i++)
		{
			for (int j=0; j<this.numColumns; j++)
			{
				matrixString+=this.data[i][j]+" ";
				if (j==this.numColumns-1)matrixString+="\n";
			}
		}
		// return a String that represents this matrix
		return matrixString;
	}
	
	public Matrix times(Matrix matrix)
	{
		// make sure neither of the matrix is empty
		if (this.numColumns<=0 ||matrix.numColumns<=0 || this.numRows<=0 || matrix.numRows<=0)return null;
		// check if the two matrices are compatible for multiplication, if not, return null.
		if (this.numColumns != matrix.numRows) return null;
		// determine the dimensions of the resulting matrix
		Matrix newMatrix = new Matrix (new int [this.numRows] [matrix.numColumns]);
		
		// fill in the resulting matrix with the correct values for matrix multiplication		
		for (int i=0; i<newMatrix.numRows;i++) // i is the number of rows of the new matrix and the left matrix
		{
			// j is the number of columns of the new matrix and the right matrix
			for (int j=0; j<newMatrix.numColumns; j++)
			{
				// k is the number of columes of the left matrix and number of rows of the right matrix
				for (int k=0; k<this.numColumns; k++)
				{
					newMatrix.data[i][j]+=this.data[i][k]*matrix.data[k][j];
				}
			}
		}		
		return newMatrix;
	}
	
	public Matrix plus(Matrix matrix)
	{
		// make sure neither of the matrix is empty
		if (this.numColumns<=0 ||matrix.numColumns<=0 || this.numRows<=0 || matrix.numRows<=0)return null;
		// check if the two matrices are compatible for addition, if not, return null.
		if (this.numRows != matrix.numRows || this.numColumns!=matrix.numColumns) return null;
		// create a new matrix with thee correct number of rows and columns
		Matrix newMatrix = new Matrix (new int [this.numRows] [this.numColumns] );
		// fill in the new matrix with the correct values for matrix addition
		for (int i=0; i<this.numRows; i++)
		{
			for (int j=0; j<this.numColumns; j++)
			{
				newMatrix.data[i][j]=this.data[i][j]+matrix.data[i][j];
			}
		}
		return newMatrix; 
	}
}
