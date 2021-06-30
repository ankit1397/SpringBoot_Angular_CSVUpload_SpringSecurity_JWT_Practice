package com.bezkoder.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StockData {

	@Id
	private long serialNo;
	private String seriesReference;
	private float period;
	private float dataValue;
	private String suppressed;
	private String status;
	private String units;
	private int magnitude;
	private String subject;
	private String groupCol;
	private String seriesTitleOne;
	private String seriesTitleTwo;
	private String seriesTitleThree;
	private String seriesTitleFour;
	private String seriesTitleFive;

	public StockData() {

	}

	public StockData(long serialNo, String seriesReference, float period, float dataValue, String suppressed,
			String status, String units, int magnitude, String subject, String groupCol, String seriesTitleOne,
			String seriesTitleTwo, String seriesTitleThree, String seriesTitleFour, String seriesTitleFive) {
		super();
		this.serialNo = serialNo;
		this.seriesReference = seriesReference;
		this.period = period;
		this.dataValue = dataValue;
		this.suppressed = suppressed;
		this.status = status;
		this.units = units;
		this.magnitude = magnitude;
		this.subject = subject;
		this.groupCol = groupCol;
		this.seriesTitleOne = seriesTitleOne;
		this.seriesTitleTwo = seriesTitleTwo;
		this.seriesTitleThree = seriesTitleThree;
		this.seriesTitleFour = seriesTitleFour;
		this.seriesTitleFive = seriesTitleFive;
	}

}
