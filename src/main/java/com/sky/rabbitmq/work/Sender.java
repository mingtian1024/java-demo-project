package com.sky.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sky.rabbitmq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private final static String QUEUE = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        // 1.队列名称  2.是否持久化队列   3.是否排它 4.（连接关闭后是否自动删除队列） 5.参数
        channel.queueDeclare(QUEUE, false, false, false, null);
        // 发送消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", QUEUE, null, ("发送消息" + i).getBytes());
        }

        // 关闭连接
        connection.close();
    }
}
