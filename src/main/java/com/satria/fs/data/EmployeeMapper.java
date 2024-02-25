package com.satria.fs.data;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.satria.fs.model.Employee;

public interface EmployeeMapper {
    @SelectProvider(EmployeeSqlBuilder.class)
    @Results({
        @Result(property = "employeeNumber", column = "employee_number"),
        @Result(property = "phoneNumber", column = "phone_number"),
        @Result(property = "emailAddress", column = "email_address")
    })
    public Employee selectEmployee(Long id, Integer employeeNumber);

    @SelectProvider(EmployeeSqlBuilder.class)
    @Results({
        @Result(property = "employeeNumber", column = "employee_number"),
        @Result(property = "phoneNumber", column = "phone_number"),
        @Result(property = "emailAddress", column = "email_address")
    })
    public List<Employee> selectEmployees(
        Integer employeeNumber,
        String position,
        String name,
        String phoneNumber,
        String email,
        Integer limit,
        Integer offset
    );

    @SelectProvider(EmployeeSqlBuilder.class)
    public int selectCountEmployees(
        Integer employeeNumber,
        String position,
        String name,
        String phoneNumber,
        String email
    );

    @InsertProvider(EmployeeSqlBuilder.class)
    @SelectKey(
        statement = "select nextval('employee_id_seq')",
        keyProperty = "id",
        keyColumn = "id",
        before = true,
        resultType = Long.class
    )
    public int insertEmployee(
        Integer employeeNumber,
        String position,
        String name,
        String phoneNumber,
        String email
    );

    @UpdateProvider(EmployeeSqlBuilder.class)
    public int updateEmployee(
        Long id,
        Integer employeeNumber,
        String position,
        String name,
        String phoneNumber,
        String email
    );

    @UpdateProvider(EmployeeSqlBuilder.class)
    public int deleteEmployee(Long id);
}
