import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Alex on 01/08/2017.
 * Hard to do in scala :( i need a wrapper to be able to do this in scala
 */
public class WordCountApp {
    public static void main(final String[] args) throws Exception {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStreamBuilder builder = new KStreamBuilder();
        KStream<String, String> textLines = builder.stream("tweet");
        final KStream<String, String> filteredTextLines = textLines
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                .filter((key, word) -> !word.replaceAll("/[^A-z]+/g", "").equals(""));
        filteredTextLines.foreach((k, v) -> System.out.println(String.format("key: [%s], value: [%s]", k, v)));
        filteredTextLines
                .groupBy((key, word) -> word)
                .count("Counts")
                .to(Serdes.String(), Serdes.Long(), "word-count");

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
