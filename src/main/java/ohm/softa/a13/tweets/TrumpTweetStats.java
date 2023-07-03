package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrumpTweetStats {

    public static Map<String, Long> calculateSourceAppStats(Stream<Tweet> tweetStream) {
		return tweetStream.map(Tweet::getSourceApp)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static Map<String, Set<Tweet>> calculateTweetsBySourceApp(Stream<Tweet> tweetStream) {
			return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.toSet()));
    }

    public static Map<String, Integer> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords) {
        return tweetStream
			.map(Tweet::getText)
			.map(line -> line.split("()+"))
			.flatMap(Arrays::stream)
			.map(String::toLowerCase)
			.filter(stopWords::contains)
			.reduce(new HashMap<String, Integer>(), (acc, word) ->{
				if(acc.containsKey(word)){
					acc.put(word, acc.get(word)+1);
				}
				else{
					acc.put(word, 1);
				}
				return acc;
			}, (m1, m2) -> m1)
			.entrySet()
			.stream()
			.filter(e -> e.getValue()<10)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
