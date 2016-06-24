package com.flyrish.hades.dto;

import java.io.Serializable;

public class MergeCellsDto implements Serializable{
	
	private Integer row;
	
	private Integer col;
	
	private Integer rowspan;
	
	private Integer colspan;

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	
	
	

}
