package com.bezkoder.springjwt.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.StockData;

public class CSVHelper {
	public static String TYPE1 = "text/csv";
	public static String TYPE2 = "application/vnd.ms-excel";
	static String[] HEADERs = { "Series_reference", "Period", "Data_value", "Suppressed", "STATUS", "UNITS",
			"Magnitude", "Subject", "Group", "Series_title_1", "Series_title_2", "Series_title_3", "Series_title_4",
			"Series_title_5" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
			return true;
		}

		return false;
	}

	public static List<StockData> csvToStockDatas(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<StockData> stockDatas = new ArrayList<StockData>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			long index = 1;
			for (CSVRecord csvRecord : csvRecords) {

				StockData stockData = new StockData(index, csvRecord.get("Series_reference"),
						Float.parseFloat(csvRecord.get("Period")), Float.parseFloat(csvRecord.get("Data_value")),
						csvRecord.get("Suppressed"), csvRecord.get("STATUS"), csvRecord.get("UNITS"),
						Integer.parseInt(csvRecord.get("Magnitude")), csvRecord.get("Subject"), csvRecord.get("Group"),
						csvRecord.get("Series_title_1"), csvRecord.get("Series_title_2"),
						csvRecord.get("Series_title_3"), csvRecord.get("Series_title_4"),
						csvRecord.get("Series_title_5"));
				stockDatas.add(stockData);
				index++;
			}

			return stockDatas;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}
