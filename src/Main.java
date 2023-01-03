import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Main {

	public static ArrayList<Track> TrackList = new ArrayList<>();


	public static void main(String[] args) throws IOException{
		List<List<String>> list = readValues();
		// System.out.println(list.get(0));  // Prints empty object
		List<Integer> valueList = new ArrayList<Integer>();
		List<Integer> weightList = new ArrayList<Integer>();
		for(int i=1;i<list.size();i++) {
			valueList.add(Integer.parseInt(list.get(i).get(4)));
			weightList.add(Integer.parseInt(list.get(i).get(5)));
		}
		List<List<String>> list1 = readSequential();
		List<ArrayList<Double>> sequential_data = new ArrayList<ArrayList<Double>>();
		for (int i=1;i<list1.size();i++){
			ArrayList<Double> row = new ArrayList<>();
			for (int j=1;j<list1.get(0).size();j++){
				row.add(Double.parseDouble(list1.get(i).get(j)));
			}
			sequential_data.add(row);
		}

		Album topPop = getTrackCombinations(TrackList, 1800000);
		System.out.println(topPop.getAlbumDuration());
		System.out.println(topPop.getAlbumPopularity());

		for (int i = 0; i <topPop.albumSongList.size() ; i++) {
			System.out.print(topPop.albumSongList.get(i).track_id + " ");
		}

	}
	public static List<List<String>> readValues() throws IOException {
		try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = "term_project_value_data.csv";//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			//Reading until we run out of lines
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
			}


			//printing the fetched data
			for (int j = 1; j < data.size() ; j++)
			{
				Track tr = new Track(					//Adding the values to the track object
						Integer.parseInt(data.get(j).get(0)),
						Integer.parseInt(data.get(j).get(5)),
						Integer.parseInt(data.get(j).get(4))
				);
				TrackList.add(tr);
			}
			br.close();
			return data;
		}
		catch(Exception e)
		{
			System.out.print(e);
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			return data;
		}

	}
	public static List<List<String>> readSequential() throws IOException {
		try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = "term_project_sequential_data.csv";//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			//Reading until we run out of lines
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
			}

			//printing the fetched data
			for(List<String> list : data)
			{
				for(String str : list){}
				//	System.out.print(str + " ");
				//System.out.println();
			}
			br.close();
			return data;
		}
		catch(Exception e)
		{
			System.out.print(e);
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			return data;
		}

	}



	public static Album getTrackCombinations(List<Track> trackList, int maxDuration) {

		Album bestCombination = new Album();
		int n = trackList.size();
		int bestPopularity =0;

		// generate all bit masks for the tracks
		for (int i = 0; i < (1 << n); i++) {
			List<Track> combination = new ArrayList<>();	//Temporary list to be compared to others
			int duration = 0;
			int popularity = 0;

			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					// track j is included in this combination
					Track tr = trackList.get(j);
					combination.add(tr);
					duration += tr.getDuration();
					popularity += tr.getIndividualValue();
				}
			}
			popularity -= ((maxDuration - duration)/1000) * 0.02;  	//Reduce 0.02 points for every second
																	//that is less than the desired duration

			if (duration <= maxDuration && popularity >= bestPopularity ) {	// Check if the combination is the best one yet
				bestPopularity = popularity;
				bestCombination.albumDuration = duration;
				bestCombination.albumPopularity = popularity;
				bestCombination.albumSongList = combination;
			}
		}
		return bestCombination;
	}

}