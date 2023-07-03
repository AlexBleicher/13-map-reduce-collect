package ohm.softa.a13.tweets;

import com.google.gson.Gson;
import ohm.softa.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TweetStreamGenerator {
	private TweetStreamGenerator(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	private List<Tweet> tweets;

	public Stream<Tweet> getTweetStream() {
		return tweets.stream();
	}

	/**
	 * Read in all tweets from referenced JSON file and
	 * @param path eg. /trump_tweets.json
	 * @return
	 */
	public static TweetStreamGenerator fromJson(String path) {
		// TODO use gson to read in the tweets from the referenced path
		// use the getResourceAsStream method to access files in the `resources` directory
		Reader reader = new InputStreamReader(Objects.requireNonNull(TweetStreamGenerator.class.getResourceAsStream(path)));
		Gson gson = new Gson();
		Tweet[] tweets = gson.fromJson(reader, Tweet[].class);
		return new TweetStreamGenerator(Arrays.stream(tweets).collect(Collectors.toList()));
	}
}
