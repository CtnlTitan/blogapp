package com.example.blogapp;

import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

@SpringBootTest
class BlogappApplicationTests {

	public static void main(String[] args) throws Exception {
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try (SSLSocket socket = (SSLSocket) factory.createSocket("ac-bgmwoep-shard-00-00.jnq2xho.mongodb.net", 27017)) {
			socket.startHandshake();
			System.out.println("SSL handshake successful!");
		}
	}
}
