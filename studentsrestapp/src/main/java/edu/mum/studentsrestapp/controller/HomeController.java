package edu.mum.studentsrestapp.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @GetMapping("/start")
    public String showHome(){
        return "students";
    }


    @GetMapping("/save")
    @ResponseBody
    public String saveStudents() throws Exception{

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job, jobParameters);


        return "You succesfully saved students in the Database!";
    }

}
