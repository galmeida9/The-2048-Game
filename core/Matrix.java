package core;

public class Matrix {
	private int[][] _matrix;
	private int _size;
	private int _nZeroes;
		
	public Matrix(int size) {
		_matrix = new int[size][size];
		_size = size;
		_nZeroes = size * size;
		for (int l = 0; l < _size; l++)
			for (int c = 0; c < _size; c++)
				_matrix[l][c] = 0;
	}	

	public int getIndice(int line, int column) {return _matrix[line][column];}

	public void setIndice(int line, int column, int value) {
		if (getIndice(line, column) == 0) _nZeroes--;
		if (value == 0) _nZeroes ++;
		_matrix[line][column] = value;
	}

	public int getSize() {return _size;}

	public int getNZeroes() {return _nZeroes;}

	public String printLine(int line) {
		String res = "| ";
		for (int c = 0; c <_size; c++) {
			if (getIndice(line, c) != 0)
				res += _matrix[line][c] + " | ";
			else
				res += "  | ";
		}
		return res;
	}

	@Override
	public String toString() {
		String res = "<html>";
		for (int l = 0; l < _size; l++)
			for (int c = 0; c < _size; c++) {
				if (c == 0) { 
					if (getIndice(l, c) != 0) res += "<br>| " + getIndice(l, c) + " | ";
					else res += "<br>|   | ";
				}
				else {
					if (getIndice(l, c) != 0) res += getIndice(l, c) + " | ";
					else res += "  | ";
				}
			}
		res += "</html>";
		return res;
	}		
}
