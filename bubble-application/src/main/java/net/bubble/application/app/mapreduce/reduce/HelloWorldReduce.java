package net.bubble.application.app.mapreduce.reduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HelloWorldReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable result = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> iterable, Context context) throws IOException, InterruptedException {

		int sum = 0;
		for (IntWritable val : iterable) {
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}

}
