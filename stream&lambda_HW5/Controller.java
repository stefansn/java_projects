package hmwrk5;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;



public class Controller {

	private static List<MonitoredData> listData;

	public static void readFromFile() {
		listData = new ArrayList<MonitoredData>();

		List<String> list = new ArrayList<>();

		Path path = Paths.get("C:\\Users\\whos\\Desktop\\TP\\hmwrk5\\Activities.txt");

		try (Stream<String> stream = Files.lines(path)) {
			list = stream.flatMap(Pattern.compile("\t+")::splitAsStream).flatMap(Pattern.compile(" ")::splitAsStream)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		int count = 0;
		String startTime = null;
		String startDate = null;
		String endTime = null;
		String endDate = null;
		String activity;
		for (String s : list) {

			count++;
			if (count == 1) {
				startDate = s;
			}
			if (count == 2) {
				startTime = s;
			}
			if (count == 3) {
				endDate = s;
			}
			if (count == 4) {
				endTime = s;
			}
			if (count == 5) {
				count = 0;
				activity = s;
				MonitoredData md = new MonitoredData(startDate, startTime, endDate, endTime, activity);
				listData.add(md);
			}
		}
		
//		for (String s:list) {
//			System.out.println(s);
//		}

//		 for (MonitoredData m : listData) {
//		 System.out.println(m.getStartDate() + " " + m.getStartTime() + " formatted " + " " +
//		 m.getEndDate() + " " + m.getEndTime() + " " + m.getActivity());
//		 }

	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static long countDistinctDays() {

		List<MonitoredData> distinct = listData.stream().filter(distinctByKey(p -> p.getStartDay()))
				.collect(Collectors.toList());
		long count = distinct.stream().map(w -> w.getEndDay()).distinct().count();

		return count;
	}

	public static void activityOccurences() {
		Map<String, Long> hm = new HashMap<String, Long>();
		String name;
		hm = listData.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("activitiesOcc.txt"), "utf-8"))) {
			try {
				Iterator it = hm.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					writer.write(pair.getKey() + " " + pair.getValue() + "\n\t");
					writer.write(System.getProperty("line.separator"));

					it.remove(); // avoids a ConcurrentModificationException
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void activityLogDays() {
		Map<Integer, Map<String, Long>> hm = new HashMap<Integer,Map<String,Long>>();
		
		hm = listData.stream()
				.collect(Collectors.groupingBy(MonitoredData::getStartDay,
						Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));
		
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("everyDayActivityCount.txt"), "utf-8"))) {
			try {
				for (Map.Entry<Integer, Map<String,Long>> entry: hm.entrySet()) {
					Integer p = entry.getKey();
					HashMap<String,Long> mapValues= (HashMap<String, Long>) entry.getValue();
					
					writer.write("Day nr. " + p + " has the following activities: ");
					writer.write(System.getProperty("line.separator"));
					//System.out.println("Day nr. " + p + " has the following activities: ");
					
					for (Map.Entry<String,Long> entryAux: mapValues.entrySet()) {
						String activity = entryAux.getKey();
						Long count = entryAux.getValue();
						
						writer.write(activity + " -> " + count);
						writer.write(System.getProperty("line.separator"));

						//System.out.println(activity + " -> " + count);
					}
					
					
				}
				
				
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		
		
	}
	
	public static void totalDuration() {
		Map<String, Long> hm = new HashMap<String,Long>();
		
		hm = listData.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity,
						Collectors.summingLong(MonitoredData::getDuration)));
		
		Map<String,Long> filtered = hm.entrySet().stream()
				.filter(p->p.getValue()/3600 > 10)
				.collect(Collectors.toMap(p->p.getKey(), p->p.getValue()));
		
		
		
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("activityDuration.txt"), "utf-8"))) {
			try {
				for (Map.Entry<String,Long> entryAux: filtered.entrySet()) {
					String activity = entryAux.getKey();
					Long sum = entryAux.getValue();
					
					writer.write(activity + " has a total time of: " + sum + " seconds");
					writer.write(System.getProperty("line.separator"));
					
					//System.out.println("filtered "+ activity + " " + sum);
				}
				
				
				
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
//		for (Map.Entry<String,Long> entryAux: hm.entrySet()) {
//			String activity = entryAux.getKey();
//			Long sum = entryAux.getValue();
//			
//			
//
//			System.out.println(activity + " " + sum);
//		}
//		
//		for (Map.Entry<String,Long> entryAux: filtered.entrySet()) {
//			String activity = entryAux.getKey();
//			Long sum = entryAux.getValue();
//			
//			
//
//			System.out.println("filtered "+ activity + " " + sum);
//		}
	}
	
	public static void activitiesLessThan5Minutes() {
		List<String> result = new ArrayList<String>();
		final Map<String,Long> activityDuration;
		Map<String,Long> filteredActivities = new HashMap<String,Long>();
		
		Predicate<MonitoredData> byDuration = MonitoredData -> MonitoredData.getDuration()/60 < 5;	
		
		
		activityDuration = listData.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity,Collectors.counting()));
		
		
		filteredActivities = listData.stream()
				.filter(byDuration)
				.collect(Collectors.groupingBy(MonitoredData::getActivity,Collectors.counting()));
		
		result = filteredActivities.entrySet().stream()
				.filter(e->e.getValue() >= 0.9 * activityDuration.get(e.getKey()))
				.map(p->p.getKey())
				.collect(Collectors.toList());
		
		
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("activitiesLessThan5.txt"), "utf-8"))) {
			try {
				for (String s : result) {
					writer.write(s);
					writer.write(System.getProperty("line.separator"));

					//System.out.println(s);
				}
				
				
				
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		for (String s : result) {
//			System.out.println(s);
//		}
		
	}
	
	public static void main(String[] args) {
		readFromFile();
		long nrDistinctDays = countDistinctDays();

		System.out.println("Number of distinct days is: " + nrDistinctDays);
		activityOccurences();
		activityLogDays();
		totalDuration();
		activitiesLessThan5Minutes();
	}

}
