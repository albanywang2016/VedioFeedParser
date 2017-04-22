package com.davidwang.feed.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {



	public static String formatTime(LocalDateTime now) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(Const.DATE_PATTERN_YMDHM);
		return now.format(format);
	}

}
