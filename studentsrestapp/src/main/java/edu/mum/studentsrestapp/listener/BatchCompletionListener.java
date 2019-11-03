package edu.mum.studentsrestapp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BatchCompletionListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(BatchCompletionListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BatchCompletionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {

//            log.info(" JOB FINISHED! ");
            System.out.println("Student list saved successfully");

//            List<StudentWithBirthDate> results = jdbcTemplate.query("SELECT first_name, last_name, birthDate, gpa FROM student",
//                    (rs, row) -> new StudentWithBirthDate(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDouble(4)));
//
//            for (StudentWithBirthDate student : results) {
//                log.info("Found <" + student + "> in the database.");
//            }

        }
    }
}
