package net.bubble.application.app.mapreduce.map;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HelloWorldMap extends Mapper<Object, Text, Object, IntWritable> {
	
	private static final IntWritable writable = new IntWritable();

	private Text word = new Text();

	@Override
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		StringTokenizer str = new StringTokenizer(value.toString());
		while (str.hasMoreTokens()) {
			word.set(str.nextToken());
			context.write(word, writable);
		}
	}
}
