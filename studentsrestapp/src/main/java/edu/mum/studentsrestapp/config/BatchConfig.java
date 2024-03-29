package edu.mum.studentsrestapp.config;



import edu.mum.studentsrestapp.listener.BatchCompletionListener;
import edu.mum.studentsrestapp.model.Student;
import edu.mum.studentsrestapp.model.StudentWithBirthDate;
import edu.mum.studentsrestapp.processor.StudentItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<Student> reader() {
        FlatFileItemReader<Student> reader = new FlatFileItemReader<Student>();
        reader.setResource(new ClassPathResource("Student.csv"));
        reader.setLineMapper(new DefaultLineMapper<Student>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName","age","gpa" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {{
                setTargetType(Student.class);
            }});
        }});
        return reader;
    }

    @Bean
    public StudentItemProcessor processor(){
        return new StudentItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<StudentWithBirthDate> writer() {
        JdbcBatchItemWriter<StudentWithBirthDate> writer = new JdbcBatchItemWriter<StudentWithBirthDate>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<StudentWithBirthDate>());
        writer.setSql("INSERT INTO student (first_name, last_name, birthdate, gpa) VALUES (:firstName, :lastName,:birthdate,:gpa)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(BatchCompletionListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Student, StudentWithBirthDate> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
