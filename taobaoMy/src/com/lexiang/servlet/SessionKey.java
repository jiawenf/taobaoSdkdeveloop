package com.lexiang.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SessionKey {
	public static void main(String[] args) {
		String str = getResult(
				"http://container.api.taobao.com/container?appkey=21660292", "");
		System.out.println(str);

	}

	public static String getResult(String urlStr, String content) {

		URL url = null;

		HttpURLConnection connection = null;

		try {

			url = new URL(urlStr);

			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.connect();

			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());

			out.write(content.getBytes("utf-8"));

			out.flush();

			out.close();

			BufferedReader reader =

			new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));

			StringBuffer buffer = new StringBuffer();

			String line = "";

			while ((line = reader.readLine()) != null) {

				buffer.append(line);

			}

			reader.close();

			return buffer.toString();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (connection != null) {

				connection.disconnect();

			}

		}

		return null;

	}
}
