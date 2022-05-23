import org.apache.kafka.streams.{KeyValue, TestInputTopic, TestOutputTopic, Topology, TopologyTestDriver}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class KafkaTestDemoSPec extends AnyFlatSpec with should.Matchers {

  import org.apache.kafka.streams.scala.serialization.Serdes._

  it should "return topology" in {
    val topology: Topology = WordCountApp.getTopology
    val testDriver = new TopologyTestDriver(topology)
    val inputTopic: TestInputTopic[String, String] = testDriver.createInputTopic(WordCountApp.INPUT_TOPIC, stringSerde.serializer, stringSerde.serializer())
    val outputTopic: TestOutputTopic[String, String] = testDriver.createOutputTopic(WordCountApp.OUTPUT_TOPIC, stringSerde.deserializer, stringSerde.deserializer)

    inputTopic.pipeInput("value value something else")
    outputTopic.readKeyValue() shouldBe KeyValue.pair("value", "1")
  }
}
