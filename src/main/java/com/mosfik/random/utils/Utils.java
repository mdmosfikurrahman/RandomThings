package com.mosfik.random.utils;

import com.mosfik.random.repository.TasksRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class Utils {
    private static final Log log = LogFactory.getLog(Utils.class);

    private final TasksRepository tasksRepository;

    @Autowired
    public Utils(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    /**
     * get current date with fixed pattern on dd/MM/yyyy
     *
     * @return
     */
    public static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static Date strToDt(String str, String pattern) {
        Date dt = null;
        try {
            if (!StringUtils.isEmpty(str)) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                dt = sdf.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }
}
