import org.apache.kafka.streams.{KafkaStreams, StreamsConfig, Topology}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes
import java.util.Properties
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes._

object WordCountApp {

  val INPUT_TOPIC = "Stream-input"
  val OUTPUT_TOPIC = "stream-output"

  def main(args: Array[String]) = {
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "new_kafka_strams_demo")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.getClass)

    val topology = getTopology
    val stream = new KafkaStreams(topology, props)
    stream.start()
  }

  def getTopology: Topology = {
    val builder = new StreamsBuilder()
    val source: KStream[String, String] = builder.stream[String, String](INPUT_TOPIC)
    val wordCounter = source
      .flatMapValues(_.split("\\W+"))
      .groupBy((_, word) => word)
      .count
      .mapValues(_.toString)

    wordCounter.toStream.to(OUTPUT_TOPIC)
    builder.build()
  }
}
