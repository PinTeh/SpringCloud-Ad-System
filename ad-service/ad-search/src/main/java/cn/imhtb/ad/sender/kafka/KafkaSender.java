package cn.imhtb.ad.sender.kafka;

import cn.imhtb.ad.mysql.dto.MySQLRowData;
import cn.imhtb.ad.sender.ISender;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 78
 * @author PinTeh
 * @date 2019/8/13
 */
@Component("kafkaSender")
public class KafkaSender implements ISender {

    @Value("${adconf.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    //发送
    public void sender(MySQLRowData rowData) {

        kafkaTemplate.send(
                topic, JSON.toJSONString(rowData)
        );
    }

    //监听
    @KafkaListener(topics = {"ad-search-mysql-data"}, groupId = "ad-search")
    public void processMysqlRowData(ConsumerRecord<?,?> record){

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            Object message = kafkaMessage.get();
            MySQLRowData rowData = JSON.parseObject(
                    message.toString(),
                    MySQLRowData.class
            );
            System.out.println("kafka processMysqlRowData" +JSON.toJSONString(rowData));
        }
    }
}