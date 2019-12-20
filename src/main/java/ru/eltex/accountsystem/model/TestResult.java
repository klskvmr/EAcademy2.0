package ru.eltex.accountsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.testsystem.model.TestAnswers;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "testResult")
public class TestResult {
    /** Поле идентификатора */
    @Id
    private String id;

    /** Поле идентификатора студента */
    private String studentId;

    /** Поле идентификатора теста */
    private String testId;

    /** Поле итогового результата */
    private Integer result;

    /** Поле затраченного времени на тест */
    private Integer timeOfTest;

    /** Поле итоговой оценки **/
    private String mark;

    /** Поле с текущими ответами*/
    private TestAnswers testCurrentAnswers;

    /** Поле с итоговыми ответами */
    private TestAnswers testFinishAnswers;

    public TestResult(String studentId, String testId, Integer result, String mark, Integer timeOfTest, TestAnswers testCurrentAnswers, TestAnswers testFinishAnswers) {
        this.studentId = studentId;
        this.testId = testId;
        this.result = result;
        this.mark = mark;
        this.timeOfTest = timeOfTest;
        this.testCurrentAnswers = testCurrentAnswers;
        this.testFinishAnswers = testFinishAnswers;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", testId='" + testId + '\'' +
                ", result=" + result +
                ", timeOfTest=" + timeOfTest +
                ", testCurrentAnswers=" + testCurrentAnswers +
                ", testFinishAnswers=" + testFinishAnswers +
                '}';
    }
}
