package edu.mum.studentsrestapp.processor;



import edu.mum.studentsrestapp.model.Student;
import edu.mum.studentsrestapp.model.StudentWithBirthDate;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;
import java.time.LocalDate;

public class StudentItemProcessor implements ItemProcessor<Student, StudentWithBirthDate> {

    @Override
    public StudentWithBirthDate process(final Student student) throws Exception {

        final int age = student.getAge();
        final LocalDate birthDate = LocalDate.of(LocalDate.now().getYear() - age, 01, 01);
        final Date bdate = Date.valueOf(birthDate);

        final StudentWithBirthDate transformedStudent = new StudentWithBirthDate(student.getFirstName(), student.getLastName(),
                bdate, student.getGpa());

        return transformedStudent;
    }

}
