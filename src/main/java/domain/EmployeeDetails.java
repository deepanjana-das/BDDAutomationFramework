package domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDetails {
    private String empId;
    private String empName;
    private String dept;
    private String doj;
}
