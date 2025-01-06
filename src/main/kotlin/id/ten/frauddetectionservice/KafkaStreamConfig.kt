package id.ten.frauddetectionservice

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.kstream.Produced
import org.apache.kafka.streams.kstream.TimeWindows
import org.apache.kafka.streams.state.WindowStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerde
import java.time.Duration

@Configuration
class FraudDetectionStreams {

    @Bean
    fun buildFraudDetectionPipeline(builder: StreamsBuilder): KStream<String, Transaction> {
        val transactionSerde = JsonSerde(Transaction::class.java)
        val stringSerde = Serdes.String()

        val transactionStream: KStream<String, Transaction> = builder.stream("transactions", Consumed.with(stringSerde, transactionSerde))

        transactionStream
            .groupByKey()
            .windowedBy(TimeWindows.of(Duration.ofDays(1)))
            .aggregate(
                { 0.0 },
                { _, transaction, total -> total + transaction.amount },
                Materialized.`as`<String, Double, WindowStore<Bytes, ByteArray>>("daily-transaction-sum-store")
                    .withKeySerde(stringSerde)
                    .withValueSerde(Serdes.Double())
            )
            .toStream()
            .filter { _, total -> total > 100000000.0 }
            .map { key, _ -> KeyValue(key.key(), "Fraud detected for user: ${key.key()}") }
            .to("fraud-alerts", Produced.with(stringSerde, stringSerde))

        return transactionStream
    }
}