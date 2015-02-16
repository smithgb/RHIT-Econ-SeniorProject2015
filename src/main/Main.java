package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.lang.Integer;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
	public static int findQuarter(int month){
		if((month == Globals.Month.JANUARY) || (month == Globals.Month.FEBRUARY) || (month == Globals.Month.MARCH))
			return(Globals.Quarter.FIRST);
		else if((month == Globals.Month.APRIL) || (month == Globals.Month.MAY) || (month == Globals.Month.JUNE))
			return(Globals.Quarter.SECOND);
		else if((month == Globals.Month.JULY) || (month == Globals.Month.AUGUST) || (month == Globals.Month.SEPTEMBER))
			return(Globals.Quarter.THIRD);
		else if((month == Globals.Month.OCTOBER) || (month == Globals.Month.NOVEMBER) || (month == Globals.Month.DECEMBER))
			return(Globals.Quarter.FOURTH);
		else
			return(0);
	}

	public static void main(String[] args) throws IOException {
		FileChooser fc = new FileChooser();
		fc.launchFileChooser();
		File infile = fc.getSelectedFile();
		BufferedReader initTable, table;
		Map<Integer, HashMap<Integer, BigDecimal>> totals = new HashMap<Integer, HashMap<Integer, BigDecimal>>();
		Map<Integer, HashMap<Integer, Integer>> counts = new HashMap<Integer, HashMap<Integer, Integer>>();

		initTable = new BufferedReader(new FileReader(infile));

		for(String row; (row = initTable.readLine()) != null; ){
			if(row.contains(Globals.Strings.DATE_STRING))
				continue;

			Integer year = new Integer(Integer.parseInt(row.split(Globals.Strings.COMMA_SEPARATOR)[0].split(Globals.Strings.FORWARD_SLASH)[2]));
			if(!totals.containsKey(year)){
				totals.put(year, new HashMap<Integer, BigDecimal>());
				for (int i = 1; i <= Globals.Integers.QUARTERS_PER_YEAR; i++)
					totals.get(year).put(i, new BigDecimal(0.0));

				counts.put(year, new HashMap<Integer, Integer>());
				for (int i = 1; i <= Globals.Integers.QUARTERS_PER_YEAR; i++)
					counts.get(year).put(i, new Integer(0));
			}
		}

		initTable.close();
		table = new BufferedReader(new FileReader(infile));

		for(String row; (row = table.readLine()) != null; ){
			if(row.contains(Globals.Strings.DATE_STRING))
				continue;

			String[] cells = row.split(Globals.Strings.COMMA_SEPARATOR);

			// get month
			int month = Integer.parseInt(cells[0].split(Globals.Strings.FORWARD_SLASH)[0]);

			// get quarter
			Integer quarter = new Integer(findQuarter(month));

			// get year
			Integer year = new Integer(Integer.parseInt(cells[0].split(Globals.Strings.FORWARD_SLASH)[2]));

			// get stock value
			BigDecimal val = new BigDecimal(Double.parseDouble(cells[1]));

			// update totals
			totals.get(year).put(quarter, totals.get(year).get(quarter).add(val));

			// update count
			counts.get(year).put(quarter, counts.get(year).get(quarter) + 1);
		}

		TreeMap<Integer, HashMap<Integer, BigDecimal>> totalsSorted = new TreeMap<Integer, HashMap<Integer, BigDecimal>>(totals);

		String inPath = infile.getAbsolutePath();
		File outfile = new File(inPath.substring(0, inPath.length() - Globals.Integers.LENGTH_OF_FILE_EXT).concat(Globals.Strings.NEW_FILE_ENDING));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outfile.getAbsoluteFile()));

		bw.write(Globals.Strings.TABLE_HEADER.concat(System.getProperty(Globals.Strings.LINE_SEPARATOR)));

		for(Map.Entry<Integer, HashMap<Integer, BigDecimal>> e : totalsSorted.entrySet()){
			Integer year = e.getKey();
			for(Integer i = new Integer(1); i <= Globals.Integers.QUARTERS_PER_YEAR; i++){
				BigDecimal sum = e.getValue().get(i);
				BigDecimal count = new BigDecimal(counts.get(year).get(i).intValue());
				if(count.intValue() == 0)
					count = new BigDecimal(1);

				BigDecimal average = sum.divide(count, 2, RoundingMode.HALF_UP);
				bw.write(year.toString().concat(Globals.Strings.QUARTER.concat(i.toString())).concat(Globals.Strings.COMMA_SEPARATOR.concat(Double.toString(average.doubleValue()))).concat(System.getProperty(Globals.Strings.LINE_SEPARATOR)));
			}
		}

		bw.close();
		table.close();
	}
}
